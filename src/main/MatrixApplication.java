package main;

public class MatrixApplication {	
	
	public static void main (String[] agr){


		Matrix matrix = new Matrix(new double[][]{{1,7,3},{-4,9,4},{0,3,2}});
		Matrix matrix1 = RandomMatrixReader.readMatrix(3,2);
//		Matrix matrix = RandomMatrixReader.readMatrix();
		

		try {
//Fill and print matrix	
			System.out.println("Matrix matrix:");	
			System.out.println(matrix);
	
//Fill and print matrix1	
			System.out.println("Matrix matrix1:");
			System.out.println(matrix1);	
	
//Transpose and print matrix
			System.out.println("Transposed matrix:");		
			System.out.println(matrix.transpose());
	
//Rotate and print matrix
			System.out.println("Rotated matrix:");		
			System.out.println(matrix.rotate());
	
			
//Multiply two matrices and print the result
			System.out.println("Multiplied matrices:");
			System.out.println(matrix.multiply(matrix1));
		
			
//Find a matrix determinant
			System.out.println("Determinant: " + matrix.determinant());
		
//Find a return matrix 	
			System.out.println("Inverse matrix:");
			System.out.println(matrix.inverse());	
			
//Multiply matrix and it's inverse matrix
			System.out.println("Matrix and inverse matrix multiplied (should be E matrix):");
			System.out.println(matrix.multiply(matrix.inverse()));	

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("End");
	}
	
	
}

	

