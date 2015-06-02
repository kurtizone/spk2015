import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.*;
import java.util.Scanner;


public class Traveler extends
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
private TextField ctrlPopulationSize;

/**
* Текстове поле з відсотком мутації.
*/
private TextField ctrlMutationPercent;

/**
* Формує смугу для кнопки та текстових полів.
*/
private Panel ctrlButtons;


  /**
   * Стан.
   */
  private String status = "";

private Scanner input;
private Scanner input_2;


  public void init()
  {
    setLayout(new BorderLayout());

    //
    ctrlButtons = new Panel();
    ctrlStart = new Button("Start");
    ctrlButtons.add(ctrlStart);
   
    ctrlButtons.add(new Label("Population:"));
   ctrlButtons.add(ctrlPopulationSize = new TextField(5));
    ctrlButtons.add(new Label("Mutation:"));
    ctrlButtons.add(ctrlMutationPercent = new TextField(5));
    this.add(ctrlButtons, BorderLayout.SOUTH);

    // 
    ctrlPopulationSize.setText("500");
    ctrlMutationPercent.setText("0.10");
  

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
		  populationSize = Integer.parseInt(ctrlPopulationSize.getText());
	  }
	  catch(NumberFormatException e)
	  {
		  populationSize = 500;
	  }

	  try
	  {
		  mutationPercent = new Double(ctrlMutationPercent.getText()).doubleValue();
	  }
	  catch(NumberFormatException e)
	  {
		  mutationPercent = 0.10;
	  }
	  FontMetrics fm = getGraphics().getFontMetrics();
	  int bottom = ctrlButtons.getBounds().y - fm.getHeight()-2;

	  matingPopulationSize = populationSize/2;
	  favoredPopulationSize = matingPopulationSize/2;
	  cutLength = cityCount/5;

/*	  	//	 help!

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

      }*/
	 
			  	try {
			  			input_2 = new Scanner(new File("count.txt"));
			  			while (input_2.hasNextLine()) {
			  				//System.out.println(input_2.nextInt());
			  				cityCount = (int)input_2.nextInt();
			  				System.out.println(cityCount);
			  			}
				       
			  		} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			  		}
			  		input_2.close();
		     
	        
				try {
					input = new Scanner(new File("cities.txt"));
					String[] mass;
					int i=0;
					cities = new City[cityCount];
					       while (input.hasNextLine()) {
				
				        	mass=input.nextLine().split(" ");
				        	System.out.println(Integer.parseInt(mass[0])+" "+ Integer.parseInt(mass[1]));
				           	cities[i] = new City(Integer.parseInt(mass[0]), Integer.parseInt(mass[1]));
				        	i++;

				                      }
				       
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
	           input.close();
	          
	          
	//
	    chromosomes = new Chromosome[populationSize];
	    for ( int i=0;i<populationSize;i++ ) {
	      chromosomes[i] = new Chromosome(cities);
	      chromosomes[i].setCut(cutLength);
	      chromosomes[i].setMutation(mutationPercent);
	    }
	    Chromosome.sortChromosomes(chromosomes,populationSize);

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
    	    g.setColor(Color.blue);//---------------------------------------------------------------------------------------
    	    for ( int i=0;i<cityCount;i++ ) {
    	      int xpos = cities[i].getx();
    	      int ypos = cities[i].gety();
    	      g.fillOval(xpos-5,ypos-5,10,10);
    	    }

    	    g.setColor(Color.yellow);//---------------------------------------------------------------------------------------
    	    for ( int i=0;i<cityCount;i++ ) {
    	      int icity = chromosomes[0].getCity(i);
    	      if ( i!=0 ) {
    	        int last = chromosomes[0].getCity(i-1);
    	        g.drawLine(
    	                  cities[icity].getx(),
    	                  cities[icity].gety(),
    	                  cities[last].getx(),
    	                  cities[last].gety());
    	      }
    	    }

    }


    g.drawString(status, 20, bottom-5);

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

    double thisCost = 500.0;
    double oldCost = 0.0;
    int countSame = 0;

    update();

    while(countSame<100) {

      generation++;

      int ioffset = matingPopulationSize;
      int mutated = 0;


      // Схрестити партнера з улюбленого списку з іншими партнерами списку на розмноження
      for ( int i=0;i<favoredPopulationSize;i++ ) {
        Chromosome cmother = chromosomes[i];
        // Вибір партнер з загального списку на розмноження
        int father = (int) ( 0.999999*Math.random()*(double)matingPopulationSize);
        Chromosome cfather = chromosomes[father];

        mutated += cmother.mate(cfather,chromosomes[ioffset],chromosomes[ioffset+1]);
        ioffset += 2;
      }

      // Переведення нового покоління в зону для сортування
      for ( int i=0;i<matingPopulationSize;i++ ) {
        chromosomes[i] = chromosomes[i+matingPopulationSize];
        chromosomes[i].calculateCost(cities);
      }


      // Сортування
      Chromosome.sortChromosomes(chromosomes,matingPopulationSize);

      double cost = chromosomes[0].getCost();
      thisCost = cost;
      double mutationRate = 100.0 * (double) mutated / (double) matingPopulationSize;

      NumberFormat nf = NumberFormat.getInstance();
      nf.setMinimumFractionDigits(2);
      nf.setMinimumFractionDigits(2);

     
      setStatus("Generation "+generation+" Length "+(int)thisCost);


      if ( (int)thisCost == (int)oldCost ) {
        countSame++; 
      } else {
        countSame = 0;
        oldCost = thisCost;
      }
      update();


    }
    setStatus("Number of generations: "+generation+"."+" Length "+(int)thisCost);
    update();
  }

  public void paint(Graphics g)
  {
	  update();
  }
}
