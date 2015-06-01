
class City {

//class for counting distance between cities
  int xpos;

 
  int ypos;


  public City(int x, int y) {
    xpos = x;
    ypos = y;
  }

//gets coordinates of cities
  public int getx() {
    return xpos;
  }

 
  public int gety() {
    return ypos;
  }


  public int proximity(City cother) {
    return proximity(cother.getx(),cother.gety());
  }

//calculates distance between cities using pythagoras theorem
  public int proximity(int x, int y) {
    int xdiff = xpos - x;//gets a neutral x coordinate between 2 cities
    int ydiff = ypos - y;//gets a neutral y coordinate between 2 cities
    return(int)Math.sqrt( xdiff*xdiff + ydiff*ydiff );
  }
}
