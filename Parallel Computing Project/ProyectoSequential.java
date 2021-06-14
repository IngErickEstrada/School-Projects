/*
	Erick Antonio Estrada Vargas 17110079 7°M
	Computacion Paralela
	Proyecto Tercer Parcial
*/

public class ProyectoSequential{
	private int row, rows, column, columns, z;
	private double[][] result;

	public double[][] multiplicationSequential(double[][] m1, double[][] m2){
		//Conditionals to be met
		assert m1 != null;	
    	assert m2 != null;	
    	assert m1.length > 0;
    	assert m1[0].length > 0;
    	assert m2.length > 0;
    	assert m2[0].length > 0;
    	assert m1.length == m2[0].length;

    	rows = m1.length;
    	columns = m2[0].length;
    	result = new double[rows][columns];

    	for (row = 0; row < result.length; row++){
            for (column = 0; column < result[0].length; column++){
            	for(z = 0; z < columns; z++)
                	result[row][column] = result[row][column] + m1[row][z] * m2[z][column];
            }
		}

    	return result;
	}
}