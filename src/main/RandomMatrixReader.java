package main;

import java.util.Random;

public class RandomMatrixReader{


	public static Matrix readMatrix() {
		Random r = new Random(System.currentTimeMillis());
		return readMatrix(r.nextInt(5) + 1, r.nextInt(5) + 1);
	}


	public static Matrix readMatrix(int rowsNum, int columnsNum) {
		Random r = new Random(System.currentTimeMillis());
		Matrix matrix = new Matrix(rowsNum, columnsNum);
		for (int i = 0; i < rowsNum; i++) {
			for (int j = 0; j < columnsNum; j++) {
				matrix.set(i, j, r.nextInt(10));
			}
		}
		return matrix;
	}

}
