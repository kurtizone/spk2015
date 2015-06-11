package tsp;

public class GA {

    /* ГА параметри */
    private static final double mutationRate = 0.015;//ймовірність мутації
    private static final int truncationSize = 5;//кількість проходів для турнірної селекції


    // Розвивається населенням більше одного покоління
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        int elitismOffset = 1;

        // кросовер населення
     // Цикл розміром нового населення і створення осіб поточного населення
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Виберір батьків
            Tour parent1 = truncationSelection(pop);
            Tour parent2 = truncationSelection(pop);
            // кросовер батьків
            Tour child = crossover(parent1, parent2);
            // добавити нащадка до нової популяції
            newPopulation.saveTour(i, child);
        }

        // Провести мутацію нової популяції
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTour(i));
        }

        return newPopulation;
    }

    //двохточкове впорядковуюче
    public static Tour crossover(Tour parent1, Tour parent2) {
        // створити новий прохід нащадка
        Tour child = new Tour();
        int p1 = (int) (Math.random() * parent1.tourSize());
        int p2 = (int) (Math.random() * parent1.tourSize());
        for(int i = 0; i < child.tourSize(); i++)
        {
        	if(i>=p1 && i<=p2) child.setCity(i, null);
        	else child.setCity(i, parent1.getCity(i));
        }
        int n = 0;
        for ( int j = 0; j < parent2.tourSize(); j++)
        {
        	boolean t = false;
        	for ( int k = 0; k < child.tourSize(); k++)
        	{
        		if(parent2.getCity(j) == child.getCity(k)) {
        			t = true;
        			break;
        		}
        	}
        	if (t== false){
        		child.setCity(p1+n, parent2.getCity(j));
        		n = n+1;
        	}
        }
        
        return child;
    }

    //класичне інвертування
    private static void mutate(Tour tour) {
    	
    	int tourPos1 = (int) (tour.tourSize() * Math.random());
    	int tourPos2 = (int) (tour.tourSize() * Math.random());
    	
    	if (tourPos2 < tourPos1) {
    		int q = tourPos1;
    		tourPos1 = tourPos2;
    		tourPos2 = q;
    	}
    	
    	for(int i = 0; i <= (tourPos2 - tourPos1 +1)/2 ; i++)
    	{
    		City q = tour.getCity(tourPos1+i);
    		tour.setCity(tourPos1+i, tour.getCity(tourPos2-i));
    		tour.setCity(tourPos2-i, q);
    		
    	}
    	
    	
    }

    // Вибрати кандидатів проходу для кросинговера
    private static Tour truncationSelection(Population pop) {
        // селекція усканням
        Population truncation = new Population(truncationSize, false);
        // Для кожного міста в турнірі отримати рандомного кандидата проходу і добавити його
        for (int i = 0; i < truncationSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            
            truncation.saveTour(i, pop.getTour(randomId));
        }
        //взяти найкращий прохід
        Tour fittest = truncation.getFittest();
        return fittest;
    }
}