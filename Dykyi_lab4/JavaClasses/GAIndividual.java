import java.util.Random;

public class GAIndividual {
	private static Random randg = new Random(); // Рандомний генератор
	public int genome_size;
	public float[] genome;
	public float fitness;

	public GAIndividual(int gsize, float[] min_range, float[] max_range) {
		// створити випадкового індивідума довжиною gsize
		// і-й ген повине бути в діапазоні між min_range[i] і max_range[i]
		genome_size = gsize;
		genome = new float[genome_size];
		for (int i = 0; i < genome_size; i++) {
			genome[i] = randg.nextFloat() * (max_range[i] - min_range[i])
					+ min_range[i];
		}
		evalFitness();// оцінити придатність цього нового індивідума
	}

	public GAIndividual(float d[]) {
		// Створити індивідума, що його ген є таки же як d []
		genome_size = d.length;
		genome = new float[genome_size];
		for (int i = 0; i < genome_size; i++) {
			genome[i] = d[i];
		}
		evalFitness();// оцінити придатність цієї нового індивідума
	}

	public GAIndividual mutate(float[] min_range, float[] max_range) {
		// rate це шанс кожного гена мутувати
		float rate = 1.0f / (float) genome_size;
		float[] result = new float[genome_size];
		for (int i = 0; i < genome_size; i++)
			result[i] = genome[i];
		// застосування точкової мутації
		for (int i = 0; i < genome_size; i++)
			if ((float) Math.random() < rate)
				result[i] = randg.nextFloat() * (max_range[i] - min_range[i])
						+ min_range[i];

		return new GAIndividual(result);
	}

	public static GAIndividual xover1p(GAIndividual f, GAIndividual m) {
		// одноточковий кросинговер
		Random rng = new Random();
		int xpoint = 1 + rng.nextInt(1);
		float[] child = new float[f.genome_size];
		for (int i = 0; i < xpoint; i++) {
			child[i] = f.genome[i];
		}
		for (int i = xpoint; i < f.genome_size; i++) {
			child[i] = m.genome[i];
		}
		return new GAIndividual(child);
	}

	public String toString() {
		String s = "[";
		s += genome[0] + "]";
		s += " fitness = " + fitness;
		return s;
	}

	private void evalFitness() {
		int a = 30;
		int b = -50;
		int c = -55;
		int d = 3;
		// цільова функція
		fitness = (a + b * genome[0] + c * genome[0] * genome[0] + d
				* genome[0] * genome[0] * genome[0]);
	}

}
