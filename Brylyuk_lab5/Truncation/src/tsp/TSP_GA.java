package tsp;

import java.util.Date;


public class TSP_GA {

    public static void main(String[] args) {

    	int [] x = {7, 13, 4, 5, 12, 8, 6, 13, 1, 11, 16, 20, 16, 6, 13, 11, 13, 7, 16, 19};
    	int [] y = {20, 8, 13, 18, 15, 15, 7, 17, 13, 6, 3, 12, 12, 19, 5, 10, 12, 3, 3, 2}; 
   	
       
    	//Створення і додавання міста
    	for(int i = 0; i < x.length; i++){
    		TourManager.addCity(new City(x[i],y[i]));
    	}

       
    	//Ініціалізація популяції
        Population pop = new Population(200, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());
        Date currentTimeBefore = new Date();
        long timeBefore = currentTimeBefore.getTime();
        //System.out.println("Time: " + timeBefore);
        // Розвинення населеня на 100 поколінь
        //
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }
        Date currentTimeAfter = new Date();
        long timeAfter= currentTimeAfter.getTime();;
        //System.out.println("Time: " + timeAfter);
        long time = timeAfter-timeBefore;
     
        //Вивід результатів
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Time: " + time + "мс");
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
        
    }
}