using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab4
{
    class GAIndividual
    {
        private static Random randg = new Random(); // Рандомний генератор
	public int genome_size;
	public double[] genome;
	public double fitness;

	public GAIndividual(int gsize, double[] min_range, double[] max_range) {
		// створити випадкового індивідума довжиною gsize
		// і-й ген повине бути в діапазоні між min_range[i] і max_range[i]
		genome_size = gsize;
		genome = new double[genome_size];
		for (int i = 0; i < genome_size; i++) {
            genome[i] = (double)randg.NextDouble() * (max_range[i] - min_range[i]) + min_range[i];
		}
	
					
		evalFitness();// оцінити придатність цього нового індивідума
	}

    public GAIndividual(double[] d)
    {
		// Створити індивідума, що його ген є таки же як d []
		
		genome_size = d.Length;
		genome = new double[genome_size];
		for (int i = 0; i < genome_size; i++) {
			genome[i] = d[i];
		}
		evalFitness();// оцінити придатність цієї нового індивідума
	}

	public GAIndividual mutate(double[] min_range, double[] max_range) {
		// rate це шанс кожного гена мутувати
		double rate = 1.0f / (double) genome_size;
		double[] result = new double[genome_size];
		for (int i = 0; i < genome_size; i++)
			result[i] = genome[i];
		// застосування точкової мутації
		for (int i = 0; i < genome_size; i++)

            result[i] = randg.NextDouble() * (max_range[i] - min_range[i]) + min_range[i];

		return new GAIndividual(result);
	}

	public static GAIndividual xover1p(GAIndividual f, GAIndividual m) {
		// одноточковий кросинговер
		Random rng = new Random();
		int xpoint = 1 + rng.Next(1);
		double[] child = new double[f.genome_size];
		for (int i = 0; i < xpoint; i++) {
			child[i] = f.genome[i];
		}
		for (int i = xpoint; i < f.genome_size; i++) {
			child[i] = m.genome[i];
		}
		return new GAIndividual(child);
	}

	public override String ToString() {
     
     	String s = "[";
        s += Math.Round(genome[0],5) + "]";
        s += " fitness = " + Math.Round(fitness, 5);
		return s;
	}

	private void evalFitness() {

        fitness = -(23 - 80 * genome[0] - 64 * genome[0] * genome[0] + 5 *genome[0]*genome[0]*genome[0]);
            
           

	}
    }
}
