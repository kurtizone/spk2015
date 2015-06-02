
class City {

  /**
   * Координата x міста.
   */
  int xpos;

  /**
   * Координата y міста.
   */
  int ypos;

  /**
   * Конструктор міста.
   * 
   * @param x Координата x міста.
   * @param y Координата y міста.
   */
  public City(int x, int y) {
    xpos = x;
    ypos = y;
  }

  /**
   * Повертає координату x
   */
  public int getx() {
    return xpos;
  }

  /**
   *Повертає координату y .
   */
  public int gety() {
    return ypos;
  }

  /**
   * Повертає відстань до іншого міста.
   * 
   * @param cother Інше місто.
   * @return A відстань.
   */
  public int proximity(City cother) {
    return proximity(cother.getx(),cother.gety());
  }

  /**
   * Повертає відстань до конкретної точки використовуючи теорему піфагора.
   * @param x Координата x .
   * @param y Координата y .
   * @return Відстань.
   */
  public int proximity(int x, int y) {
    int xdiff = xpos - x;
    int ydiff = ypos - y;
    return(int)Math.sqrt( xdiff*xdiff + ydiff*ydiff );
  }
}
