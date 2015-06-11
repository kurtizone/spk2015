

public class GAEvolve { 
	public float[] best_fitness;//best_fitness[i] є найкращим значенням для і-го покоління даної еволюції
	public GAIndividual best_ind; //найкращий індивідум(х) даної еволюції
	public GAEvolve(int generations, int pop_size, int genome_size, int xrate,int mrate, float[] min_range, float[] max_range){ 
		// xrate:ймовірність схрещування 
		// mrate: ймовірність мутації
		best_fitness = new float[generations];  
		GAPopulation gap = new GAPopulation(pop_size,genome_size,min_range,max_range); 
		best_fitness[0] = gap.ind[gap.best_index].fitness; 

		for(int i=1; i < generations; i++){ 
			gap = gap.generate(gap,xrate,mrate,min_range,max_range); 
			best_fitness[i] = gap.ind[gap.best_index].fitness; 

			System.out.println("Найкраще значення покоління " + best_fitness[i]);
			} 
		best_ind = gap.ind[gap.best_index];
		} 
	
	public static void main(String[] args) {
			//для пошуку мінімуму
			 float[] min = new float[]{0f}; 
			 float[] max = new float[]{53f};
			 //для пошуку максимуму
			 //float[] min = new float[]{0f}; 
			 //float[] max = new float[]{10f};
			 GAEvolve gae = new GAEvolve(10,30,1,70,20,min,max);
			 System.out.println("Результат:");
			 System.out.println(gae.best_ind);

	
			
			} 
		}
	

