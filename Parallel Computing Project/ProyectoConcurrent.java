import java.util.LinkedList;

public class ProyectoConcurrent{
	private LinkedList<Thread> threads = new LinkedList<Thread>(); 
	private int row, rows, column, columns;
	private double[][] result;
	private Thread thread;

	public double[][] multiplicationConcurrent (double[][] m1, double[][] m2){
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

    	for (row = 0; row < rows; row++)
        	for (column = 0; column < columns; column++){
            	thread = new Thread(new multiplicatorThread(m1, m2, result, row, column));
            	threads.add(thread);
            	thread.start();
        	}
		
        	// Se espera que terminen todos los hilos
        	for (Thread thread: threads)
            	try {
            	    thread.join();
            	} catch (InterruptedException e) {
                	e.printStackTrace();
            	}

		return result;
	}
}

class multiplicatorThread implements Runnable{
	private double[][] m1;
    private double[][] m2;
    private double[][] result;
    private int row;
    private int column;
	
    public multiplicatorThread (double[][] m1, double[][] m2, double[][] result, int row, int column){
        this.m1 = m1;
		this.m2 = m2;
		this.result = result;
		this.row = row;
		this.column = column;
    }

    public void run(){
        result[row][column] = 0.0;
		for (int i = 0; i < m2.length; i++)
            result[row][column] += m1[row][i] * m2[i][column];
    }
}
