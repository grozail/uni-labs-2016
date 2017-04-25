package grozail.lab4;

import java.util.Scanner;

/**
 * Created by grozail on 12.10.16.
 *
 */
public class Lab4 {
	public Lab4() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter n:");
		int n = scanner.nextInt();
		CircularMatrix cm = new CircularMatrix(n);
		cm.fill();
		System.out.println(cm.toString());
	}
}
