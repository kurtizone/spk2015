using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab4
{
    class GAEvolve
    {
        public double[] best_fitness;//є найкращим значенням для і-го покоління даної еволюції
        public GAIndividual best_ind; //найкращий індивідум(х) даної еволюції
        public GAEvolve(int generations, int pop_size, int genome_size, int xrate, int mrate, double[] min_range, double[] max_range){ 
		
		best_fitness = new double[generations];  
		GAPopulation gap = new GAPopulation(pop_size,genome_size,min_range,max_range); 
		best_fitness[0] = gap.ind[gap.best_index].fitness; 

		for(int i=1; i < generations; i++){ 
			gap = gap.generate(gap,xrate,mrate,min_range,max_range); 
			best_fitness[i] = gap.ind[gap.best_index].fitness; 
			
            System.Console.WriteLine("Найкраще значення поколiння " +  best_fitness[i]);
			} 
		best_ind = gap.ind[gap.best_index];
		} 

        static void Main(string[] args)
        {

            double[] min = new double[] { -10f };
            double[] max = new double[] { 53 };
            GAEvolve gae = new GAEvolve(10, 5, 1, 70, 20, min, max);
			Console.WriteLine("Результат:");
            Console.WriteLine( gae.best_ind);
            Console.ReadKey();

        }
    }
}
