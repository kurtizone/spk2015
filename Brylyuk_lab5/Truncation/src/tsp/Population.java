package tsp;

public class Population {

    //Зберігання популяції турів
    Tour[] tours;

    // Створити популцію
    public Population(int populationSize, boolean initialise) {
        tours = new Tour[populationSize];
        if (initialise) {
            // Створення індивідумів
            for (int i = 0; i < populationSize(); i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }
    
    // Зберегти прохід
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }
    
    // Отримати прохід з популяції
    public Tour getTour(int index) {
        return tours[index];
    }

    // Отримати кращий прохід в популяції
    public Tour getFittest() {
        Tour fittest = tours[0];
        // Переглянути індивідуми щоб знайти найкращий
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    // Отримати розмір популяції
    public int populationSize() {
        return tours.length;
    }
}
