import java.util.Random;

public class GAPopulation {
	private static Random randg = new Random();// Рандомний генератор
	public int pop_size;
	public GAIndividual[] ind;
	public int best_index; // індекс найкращого індивідума в масиві ind

	// best_fitness = ind[best_index].fitness
	public GAPopulation(int psize, int gsize, float[] min_range,
			float[] max_range) {
		// створення рандомної популяції кількісью pop_size
		// psize: довжина популяції
		// gsize: довжина геному
		pop_size = psize;
		ind = new GAIndividual[pop_size];
		System.out.println("Початкова популяція");
		for (int i = 0; i < pop_size; i++)
			{ind[i] = new GAIndividual(gsize, min_range, max_range);
		System.out.println(i+ " "+ ind[i]+" ");}
		//System.out.println();
		
		evaluate();
	}

	public GAPopulation(GAIndividual[] p) {
		// Створення популяції з такимиж індивідумами як в p
		pop_size = p.length;
		ind = new GAIndividual[pop_size];
		System.out.println("Нова популяція");
		for (int i = 0; i < pop_size; i++){
			ind[i] = p[i];
			System.out.println(i+ " "+ ind[i]+" ");}
		evaluate();
	}

	public GAPopulation generate(GAPopulation p, int xrate, int mrate,
			float[] min_range, float[] max_range) {
		//Створення нової популяції з р, xrate відсотків індивідумів нового населення є
		//схрещування, mrate відсотків з них створюються в результаті мутації, а інші по відтворення.
		if (xrate < 0 || xrate > 100 || mrate < 0 || mrate > 100
				|| xrate + mrate > 100)
			System.err.println("error: xrate і/чи mrate неправилно встановлені");
		GAIndividual[] newg = new GAIndividual[p.pop_size];
		
		
		
		int newg_index = 0;
		int xn = xrate * p.pop_size / 100;
		//xn: Кількість нащадків, які будуть схрешення
		int mn = mrate * p.pop_size / 100;
		// mn: кількість нащадків які будуть створенні мутацією
		// схрещування:
		for (int i = 0; i < xn; i++) {

			int p1 = p.tr_select();
			int p2 = p.tr_select();
			newg[newg_index++] = GAIndividual.uniform(p.ind[p1], p.ind[p2]);
		}
		// мутація:
		for (int i = 0; i < mn; i++){
			int n = (int)(Math.random() * p.pop_size);
			newg[newg_index++] = p.ind[p.tr_select()].mutate(p.ind[n],max_range);
		}
		// відтворення:
		for (int i = newg_index; i < p.pop_size; i++)
			newg[i] = p.ind[p.tr_select()];
		
		return new GAPopulation(newg);
	}

	public int tr_select() {
		//турнірна вибірка розміром pop_size/10
		//вона повертає індекс вибраного особи в ind []
		int s_index = randg.nextInt(pop_size);
		// індекс вибраного індивідума
		float s_fitness = ind[s_index].fitness;
		int tr_size = Math.min(10, pop_size);
		for (int i = 1; i < tr_size; i++) {
			int tmp = randg.nextInt(pop_size);
			if (ind[tmp].fitness < s_fitness) {//< для min//>для max
				s_index = tmp;
				s_fitness = ind[tmp].fitness;
			}
		}
		return s_index;
	}

	private void evaluate() {
		//оцінювання
		int best = 0;
		// індекс найкрощого індивідума
		float best_fitness = ind[0].fitness;
		float sum = ind[0].fitness;
		// сума придатності особин даної популяції
		for (int i = 1; i < pop_size; i++) {
			sum += ind[i].fitness;
			if (ind[i].fitness < best_fitness) {//< для min//>для max
				best = i;
				best_fitness = ind[i].fitness;
			}
		}
		best_index = best;
	}

	public String toString() {
		String s = "best individual = " + ind[best_index];

		return s;
	}

}
