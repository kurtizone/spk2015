package tsp;

import java.util.ArrayList;

public class TourManager {

   
	//Зберігання відстаней міст
    private static ArrayList destinationCities = new ArrayList<City>();

  
    //Додавання відстанні міста
    public static void addCity(City city) {
        destinationCities.add(city);
    }
    
    // Отримання міста
    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }
    
  
    //Отримання к-сті міст та населених пунктів
    public static int numberOfCities(){
        return destinationCities.size();
    }
}