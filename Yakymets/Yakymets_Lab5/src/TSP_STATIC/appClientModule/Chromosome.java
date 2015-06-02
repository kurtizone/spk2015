class Chromosome {


  protected int [] cityList;


  protected double cost;

  protected double mutationPercent;

 
  protected int cutLength;
//creates a chromosome from set of cities
  Chromosome(City [] cities) {
    boolean taken[] = new boolean[cities.length];
    cityList = new int[cities.length];
    cost = 0.0;
    for ( int i=0;i<cityList.length;i++ ) taken[i] = false;
    for ( int i=0;i<cityList.length-1;i++ ) {
      int icandidate;
      do {
        icandidate = (int) ( 0.999999* Math.random() * 
                             (double) cityList.length );
      } while ( taken[icandidate] );
      cityList[i] = icandidate;
      taken[icandidate] = true;
      if ( i == cityList.length-2 ) {
        icandidate = 0;
        while ( taken[icandidate] ) icandidate++;
        cityList[i+1] = icandidate;
      }
    }
    calculateCost(cities);
    cutLength = 1;
  }

 
  void calculateCost(City [] cities) {
    cost=0;
    for ( int i=0;i<cityList.length-1;i++ ) {
      double dist = cities[cityList[i]].proximity(cities[cityList[i+1]]);
      cost += dist;
    }
  }


  
  double getCost() {
    return cost;
  }

  
  int getCity(int i) {
    return cityList[i];
  }


  void setCities(int [] list) {
    for ( int i=0;i<cityList.length;i++ ) {
      cityList[i] = list[i];
    }
  }


  void setCity(int index, int value) {
    cityList[index] = value;
  }

  void setCut(int cut) {
    cutLength = cut;
  }


  void setMutation(double prob) {
    mutationPercent = prob;
  }

//mating season is on 
  int mate(Chromosome father, Chromosome offspring1, Chromosome offspring2) {
    int cutpoint1 = (int) (0.999999*Math.random()*(double)(cityList.length-cutLength));
    int cutpoint2 = cutpoint1 + cutLength;

    boolean taken1 [] = new boolean[cityList.length];
    boolean taken2 [] = new boolean[cityList.length];
    int off1 [] = new int[cityList.length];
    int off2 [] = new int[cityList.length];

    for ( int i=0;i<cityList.length;i++ ) {
      taken1[i] = false;
      taken2[i] = false;
    }

    for ( int i=0;i<cityList.length;i++ ) {
      if ( i<cutpoint1 || i>= cutpoint2 ) {
        off1[i] = -1;
        off2[i] = -1;
      } else {
        int imother = cityList[i];
        int ifather = father.getCity(i);
        off1[i] = ifather;
        off2[i] = imother;
        taken1[ifather] = true;
        taken2[imother] = true;
      }
    }
//cutting point
    for ( int i=0;i<cutpoint1;i++ ) {
      if ( off1[i] == -1 ) {
        for ( int j=0;j<cityList.length;j++ ) {
          int imother = cityList[j];
          if ( !taken1[imother] ) {
            off1[i] = imother;
            taken1[imother] = true;
            break;
          }
        }
      }
      if ( off2[i] == -1 ) {
        for ( int j=0;j<cityList.length;j++ ) {
          int ifather = father.getCity(j);
          if ( !taken2[ifather] ) {
            off2[i] = ifather;
            taken2[ifather] = true;
            break;
          }
        }
      }
    }
    for ( int i=cityList.length-1;i>=cutpoint2;i-- ) {
      if ( off1[i] == -1 ) {
        for ( int j=cityList.length-1;j>=0;j-- ) {
          int imother = cityList[j];
          if ( !taken1[imother] ) {
            off1[i] = imother;
            taken1[imother] = true;
            break;
          }
        }
      }
      if ( off2[i] == -1 ) {
        for ( int j=cityList.length-1;j>=0;j-- ) {
          int ifather = father.getCity(j);
          if ( !taken2[ifather] ) {
            off2[i] = ifather;
            taken2[ifather] = true;
            break;
          }
        }
      }
    }

    offspring1.setCities(off1);
    offspring2.setCities(off2);

    int mutate = 0;
    if ( Math.random() < mutationPercent ) {
      int iswap1 = (int) (0.999999*Math.random()*(double)(cityList.length));
      int iswap2 = (int) (0.999999*Math.random()*(double)cityList.length);
      int i = off1[iswap1];
      off1[iswap1] = off1[iswap2];
      off1[iswap2] = i;
      mutate++;
    }
    if ( Math.random() < mutationPercent ) {
      int iswap1 = (int) (0.999999*Math.random()*(double)(cityList.length));
      int iswap2 = (int) (0.999999*Math.random()*(double)cityList.length);
      int i = off2[iswap1];
      off2[iswap1] = off2[iswap2];
      off2[iswap2] = i;
      mutate++;
    }
    return mutate;
  }

 //sorts chromosomes in ascending order based on length

  public static void sortChromosomes(Chromosome chromosomes[],int num) {
    Chromosome ctemp;
    boolean swapped = true;
    while ( swapped ) {
      swapped = false;
      for ( int i=0;i<num-1;i++ ) {
        if ( chromosomes[i].getCost() > chromosomes[i+1].getCost() ) {
          ctemp = chromosomes[i];
          chromosomes[i] = chromosomes[i+1];
          chromosomes[i+1] = ctemp;
          swapped = true;
        }
      }
    }
  }

}
