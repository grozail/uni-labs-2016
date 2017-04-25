package grozail.lab12.model;

import java.util.Random;

/**
 * Created by grozail
 * on 10.12.16.
 */
public class MedicineFactory {
	private static final String[] colors = {
		"черный",
		"белый",
		"красный",
		"зеленый",
		"синий"
	};
	private static final String[] bodies = {
			"жидкий",
			"порошкообразный",
			"твердый"
	};
	private static final String[] indications = {
			"респираторные заболевания",
			"расстройства организма",
			"психические заболевания",
			"общеукрепляющее",
	};
	private static final Random random = new Random();

	public static Medicine createFromName(String name) {
		double price = Math.abs(random.nextInt() % 100 / 100. + Math.abs(random.nextInt() % 100));
		double dosage = Math.abs(random.nextInt() % 100 / 100.);
		String color = colors[Math.abs(random.nextInt() % colors.length)];
		String body = bodies[Math.abs(random.nextInt() % bodies.length)];
		String indication = indications[Math.abs(random.nextInt() % indications.length)];
		return new Medicine(name, price, dosage, new Visual(color, body, indication));
	}

	private static String createName() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append((char)('\u0041' + Math.abs(random.nextInt() % 25)));
		for (int i = 0; i < Math.abs(random.nextInt()) % 18 + 1; i++) {
			stringBuilder.append((char)('\u0061' + Math.abs(random.nextInt() % 25)));
		}
		return stringBuilder.toString();
	}

	public static Medicine create() {
		return createFromName(createName());
	}
}