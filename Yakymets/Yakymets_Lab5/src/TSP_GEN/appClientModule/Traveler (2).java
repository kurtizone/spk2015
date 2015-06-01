
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;
import java.util.Scanner;


public class Traveler2 extends
	Applet
  implements Runnable {

  /**
   * Кількість міст.
   */
	protected int cityCount;

  /**
   * 
   * Кількість хромосом.
   */
  protected int populationSize;

  /**
   * Кількість новонароджених дял мутації.
   */
  protected double mutationPercent;

  /**
   * Частина населення здатна на розмноження.
   */
  protected int matingPopulationSize;

  /**
   * Улюбленці для розмноження.
   */
  protected int favoredPopulationSize;

  /**
   * Скільки брати матеріалу дле схрещування.
   */
  protected int cutLength;

  /**
   * сучана популяція.
   */
  protected int generation;

  /**
   * струна заднього плану.
   */
  protected Thread worker = null;

  /**
   * перевірка на початок струни.
   */
  protected boolean started = false;

  /**
   * Список міст.
   */
  protected City [] cities;

  /**
   * Список хромосом.
   */
  protected Chromosome [] chromosomes;

  /**
   * Кнопка пуск.
   */
  private Button ctrlStart;

  /**
   * Текстове поле з кількістю міст.
   */
  private TextField ctrlCities;

  /**
   * Текстове поле з населенням.
   */
  
  private Panel ctrlButtons;

  /**
   * Стан.
   */
 private String status = "";

private Scanner input;


  public void init()
  {
    setLayout(new BorderLayout());

    //
    ctrlButtons = new Panel();
   
    ctrlButtons.add(new Label("# of cities"));
    ctrlButtons.add(ctrlCities = new TextField(1));
    ctrlStart = new Button("Start");
    ctrlButtons.add(ctrlStart);
  
    this.add(ctrlButtons, BorderLayout.SOUTH);

    ctrlCities.setText("50");
    // 
    ctrlStart.addActionListener(new ActionListener()
    {

      public void actionPerformed(ActionEvent arg0)
      {
        startThread();
      }
    });

    started = false;
    update();
  }


  /**
   * Запустити струну.
   */
  public void startThread() {

	  try
	  {
		  cityCount = Integer.parseInt(ctrlCities.getText());
	  }
	  catch(NumberFormatException e)
	  {
		  cityCount = 50;
	  }

	 
	  FontMetrics fm = getGraphics().getFontMetrics();
	  int bottom = ctrlButtons.getBounds().y - fm.getHeight()-2;

	 
	  	//	 help!

	    cities = new City[cityCount];
	    for ( int i=0;i<cityCount;i++ ) 
	    {
	      cities[i] = new City(
	        (int)(Math.random()*(getBounds().width-20)),
	        (int)(Math.random()*(bottom-20)));
	    }
	    ///////////////////////////////////////////////
	  

	  try {
          FileWriter fileWriter =
              new FileWriter("cities.txt");

          BufferedWriter bufferedWriter =
              new BufferedWriter(fileWriter);
          for ( int i=0;i<cityCount;i++ ) 
		    {
          bufferedWriter.write((int)cities[i].xpos+" "+(int)cities[i].ypos);

          bufferedWriter.newLine();
		    }
          bufferedWriter.close();
      }
      catch(IOException ex) {
          System.out.println(
              "Error writing to file '"
              + "cities.txt" + "'");

      }
	  
	  try {
          FileWriter count = new FileWriter("count.txt");
          System.out.println((int)cityCount);
          count.write(new Integer(cityCount).toString());
          count.close();
	  		}
	  
      catch(IOException ex) {
          System.out.println(
              "Error writing to file '"
              + "count.txt" + "'");

      }
	        
// 
    started = true;

    generation = 0;

    if ( worker != null )
        worker = null;
    worker = new Thread(this);
    worker.setPriority(Thread.MIN_PRIORITY);
    worker.start();
  }


  /**
   * Обновити екран
   */
  public void update()
  {
    Image img = createImage(getBounds().width, getBounds().height);
    Graphics g = img.getGraphics();
    FontMetrics fm = g.getFontMetrics();

    int width = getBounds().width;
    int bottom = ctrlButtons.getBounds().y - fm.getHeight()-2;

    g.setColor(Color.black);//---------------------------------------------------------------------------------------
    g.fillRect(0, 0, width, bottom);

    if( started && (cities != null) )
    {
    	    g.setColor(Color.red);//---------------------------------------------------------------------------------------
    	    for ( int i=0;i<cityCount;i++ ) {
    	      int xpos = cities[i].getx();
    	      int ypos = cities[i].gety();
    	      g.fillOval(xpos-5,ypos-5,10,10);
    	    }
  	   

    }


   getGraphics().drawImage(img, 0, 0, this);

  }

  /**
   * Обновити стан
   *
   * @param status стан.
   */
  public void setStatus(String status)
  {
    this.status = status;
  }

  /**
   * Основний цикл.
   */
  public void run() {

       
    update();
  }

  public void paint(Graphics g)
  {
	  update();
  }
}
