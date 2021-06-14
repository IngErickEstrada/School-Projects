/*
    Erick Antonio Estrada Vargas 17110079 7°M
    Computacion Paralela
    Proyecto Tercer Parcial
*/

import java.io.*;
import javax.swing.JTextArea;

public class ProyectoParallel {
	private int row, rows, column, columns, z;
	private double[][] result;

    public double[][] multiplicationParallel(double[][] m1, double[][] m2){   
    	rows = m1.length;
    	columns = m2[0].length;
    	result = new double[rows][columns];
        
        for (int x=0; x < result.length; x++) {
            for (int y=0; y < result[x].length; y++) {
                for (int z=0; z<rows; z++) {
                    result [x][y] += m1[x][z] * m2[z][y];
                }            
            }
        }

        return result;
    } 
}