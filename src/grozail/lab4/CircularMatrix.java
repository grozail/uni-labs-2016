package grozail.lab4;


/**
 * Created by grozail on 12.10.16.
 *
 */
public class CircularMatrix {
	Integer[][] matrix;

	public CircularMatrix(int n) {
		matrix = new Integer[n][n];
	}

	public void fill() {
		int filler = 1;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix.length - i; j++) {
				matrix[i][j] = filler++;
			}
			for (int j = i+1; j < matrix.length - i; j++) {
				matrix[j][matrix.length-i-1] = filler++;
			}
			for (int j = matrix.length - i - 2; j >= i ; j--) {
				matrix[matrix.length - i - 1][j] = filler++;
			}
			for (int j = matrix.length - i - 2; j > i ; j--) {
				matrix[j][i] = filler++;
			}
		}
	}


	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				stringBuilder.append(matrix[i][j]);
				stringBuilder.append("\t");
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
