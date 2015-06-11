package tsp;

public class City {
    int x;
    int y;
    
    // Створює випадково місто
    public City(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }
    
    // Створює місто в обраних х, у координатах
    public City(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Отримує координату х міста
    public int getX(){
        return this.x;
    }
    
    // Отримує координату у міста
    public int getY(){
        return this.y;
    }
    
    // Отримує відстань до даного міста
    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return distance;
    }
    
    
    public String toString(){
        return getX()+", "+getY();
    }
}