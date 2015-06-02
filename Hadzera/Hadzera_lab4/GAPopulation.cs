using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab4
{
    class GAPopulation
    {
        private static Random randg = new Random();// Рандомний генератор
        public int pop_size;
        public GAIndividual[] ind;
        public int best_index; // індекс найкращого індивідума в масиві ind

       
        public GAPopulation(int psize, int gsize, double[] min_range,
                double[] max_range) {
		// створення рандомної популяції кількісью pop_size
		
		pop_size = psize;
		ind = new GAIndividual[pop_size];
		 System.Console.WriteLine("Початкова популяцiя");
		for (int i = 0; i < pop_size; i++)
			{ind[i] = new GAIndividual(gsize, min_range, max_range);
            System.Console.WriteLine(i + " " +ind[i] + " ");
            }
		
		
		evaluate();
	}

        public GAPopulation(GAIndividual[] p) {
		// Створення популяції з такимиж індивідумами як в p
            pop_size = p.Length;
		ind = new GAIndividual[pop_size];
		 System.Console.WriteLine("Нова популяцiя");
		for (int i = 0; i < pop_size; i++){
			ind[i] = p[i];
            System.Console.WriteLine(i + " " + ind[i] + " ");
        }
		evaluate();
	}

        public GAPopulation generate(GAPopulation p, int xrate, int mrate,
                double[] min_range, double[] max_range)
        {
            //Створення нової популяції з р, xrate відсотків індивідумів нового населення є
            //схрещування, mrate відсотків з них створюються в результаті мутації, а інші по відтворення.
            if (xrate < 0 || xrate > 100 || mrate < 0 || mrate > 100
                    || xrate + mrate > 100)
                System.Console.WriteLine("error: xrate i/чи mrate неправильно встановленi");
            GAIndividual[] newg = new GAIndividual[p.pop_size];



            int newg_index = 0;
            int xn = xrate * p.pop_size / 100;
            //xn: Кількість нащадків, які будуть схрешення
            int mn = mrate * p.pop_size / 100;
            // mn: кількість нащадків які будуть створенні мутацією
            // схрещування:
            for (int i = 0; i < xn; i++)
            {
                // select to parents for cross-over:
                int p1 = p.tr_select();
                int p2 = p.tr_select();
              
                newg[newg_index++] = GAIndividual.xover1p(p.ind[p1], p.ind[p2]);
            }
            // мутація:
            for (int i = 0; i < mn; i++)
                newg[newg_index++] = p.ind[p.tr_select()].mutate(min_range,
                        max_range);
            // відтворення:
            for (int i = newg_index; i < p.pop_size; i++)
                newg[i] = p.ind[p.tr_select()];

         
            return new GAPopulation(newg);
        }

        public int tr_select()
        {
            //турнірна вибірка розміром pop_size/10
            //вона повертає індекс вибраного особи в ind []
            int s_index = randg.Next(pop_size);
            // індекс вибраного індивідума
            double s_fitness = ind[s_index].fitness;
            int tr_size = Math.Min(10, pop_size);
            for (int i = 1; i < tr_size; i++)
            {
                int tmp = randg.Next(pop_size);
                if (ind[tmp].fitness > s_fitness)
                {
                    s_index = tmp;
                    s_fitness = ind[tmp].fitness;
                }
            }
            return s_index;
        }

        private void evaluate()
        {
            //оцінювання
            int best = 0;
            // індекс найкрощого індивідума
            double best_fitness = ind[0].fitness;
            double sum = ind[0].fitness;
            // сума придатності особин даної популяції
            for (int i = 1; i < pop_size; i++)
            {
                sum += ind[i].fitness;
                if (ind[i].fitness > best_fitness)
                {
                    best = i;
                    best_fitness = ind[i].fitness;
                }
            }
            best_index = best;
        }

        public String toString()
        {
            String s = "best individual = " + ind[best_index];

            return s;
        }
    }
}
