public class ProyectoCrearMatriz{
	public double[][] createdMatriz(double[][] m){
		for(int x = 0; x < m.length; x++){
			for(int y = 0; y < m[x].length; y++){
				m[x][y] = (double) (Math.random() * 100);
				//System.out.println(m1[x][y]);
			}
		}

    	return m;
	}
}
