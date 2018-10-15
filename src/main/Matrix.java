package main;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Matrix {
	double [][] matrix=null;
	
	public Matrix (int rows, int columns){
		matrix = new double [rows][columns];
	}
	
	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}
	
	public int getRowsNum(){
		return matrix.length;
	}
	
	public int getColumnsNum() {
		return matrix[0].length;
	}
	public void set(int i, int j, double value){
		matrix[i][j]=value;
	}
	public double get(int i, int j) {
		return matrix[i][j];
	}
	
	@Override
	public String toString() {
		StringWriter result = new StringWriter();
		PrintWriter out = new PrintWriter(result, true);
		
		out.println("{");
		for (int i = 0; i < matrix.length; i++) {
			out.print(" {");
			for (int j = 0; j < matrix[i].length; j++) {
				out.print(String.format("%1$4s", matrix[i][j]));
				if (j < matrix.length - 1) {
					out.print(",");
				}
			}
			out.println("}");
		}
		out.print("}");
		
		return result.toString();
	}
	
	public Matrix transpose(){
		Matrix matrix = this;
		Matrix result = new Matrix (matrix.getColumnsNum(), matrix.getRowsNum());
		
		for (int i=0; i<matrix.getColumnsNum(); i++){
			for (int j=0; j<matrix.getRowsNum();j++){
				result.set(i, j, matrix.get(j, i));
			}
		}
		return result;
	}
	
	public Matrix rotate(){
		Matrix matrix = this;
		Matrix result = new Matrix (matrix.getRowsNum(),matrix.getColumnsNum());
		
		for (int i=0; i<matrix.getColumnsNum(); i++){
			for (int j=0; j<matrix.getRowsNum();j++){
				result.set(i, j, matrix.get(matrix.getRowsNum()-1-j, i));

			}
		}
		return result;	
	}	
	
	public Matrix multiply(Matrix matrix1) {
		Matrix matrix = this;
		
		if (matrix.getColumnsNum()!=matrix1.getRowsNum()){
			System.out.println("These matrices cannot be multiplied");
			return null;

		} else {
			Matrix result = new Matrix (matrix.getRowsNum(),matrix1.getColumnsNum());
			
			for (int i=0;i<matrix.getRowsNum();i++){
				for (int j=0;j<matrix1.getColumnsNum();j++){
					for (int k=0;k<matrix.getColumnsNum();k++){
						result.set(i, j, result.get(i, j)+matrix.get(i, k)*matrix1.get(k, j));
					}
				}
			}		
			return result;
		}		
	}
	
	public double determinant(){		
		double result = 0;
		
		if (1==matrix.length) {
			result = matrix[0][0];
		} else {
			if (2==matrix.length) {
				result = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
			} else {
				int sign = 1;
				for (int i = 0; i < matrix.length; i++) {
					Matrix subMatrix = new Matrix(matrix.length - 1, matrix.length - 1);
					for (int i1 = 0; i1 < matrix.length - 1; i1++) {
						for (int j1 = 0; j1 < matrix.length - 1; j1++) {
							subMatrix.set(i1, j1, matrix[i1 >= i? i1+1:i1][j1 + 1]);
						}
					}
					
					result += sign * matrix[i][0] * subMatrix.determinant();
					sign *= -1;
				}
			}
		}
		return result;	
	}
	
	public Matrix minors(){	
		Matrix matrix = this;
		
		if (matrix.getRowsNum()!=matrix.getColumnsNum()){
			System.out.println("No matrix of minors could be created");
			return null;

		} else {
			Matrix result = new Matrix(matrix.getRowsNum(), matrix.getColumnsNum());
			int sign = 1;	
			
				for (int i = 0; i < matrix.getRowsNum(); i++) {
					Matrix subMatrix = new Matrix(matrix.getRowsNum() - 1, matrix.getColumnsNum() - 1);
					for (int j = 0; j < matrix.getColumnsNum(); j++){					
						for (int i1 = 0; i1 < matrix.getRowsNum() - 1; i1++) {
							for (int j1 = 0; j1 < matrix.getColumnsNum() - 1; j1++) {
								subMatrix.set(i1, j1, matrix.get(i1>=i? i1+1:i1,j1 >= j? j1+1:j1));								
							}
						}
						result.set(i,j,sign * subMatrix.determinant());
						sign *= -1;
					}				
				}

			return result;			
		}
	}
	
	public Matrix inverse(){
		Matrix matrix = this;
		Matrix result = new Matrix (matrix.getRowsNum(),matrix.getColumnsNum());
		
		if ((0==matrix.determinant())||(matrix.getRowsNum()!=matrix.getColumnsNum())){
			System.out.println("The matrix does not have inverse matrix");
			return null;
		} else{
			if (1==matrix.getRowsNum()){			
				result.set(0, 0, (1/matrix.get(0, 0)));			
			} else {				
				result=matrix.minors();
				result=result.transpose();
				for (int i=0; i< matrix.getRowsNum(); i++){
					for (int j=0; j<matrix.getColumnsNum(); j++){
						result.set(i,j, result.get(i, j)/matrix.determinant());
					}
				}
				
			}				
			return result;
		}				
	}
	

}
