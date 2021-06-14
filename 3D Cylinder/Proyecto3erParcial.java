/*
	Erick Antonio Estrada Vargas 17110079 6°M
	Graficas por Computadora 2D y 3D
	Proyecto 3er Parcial: Cilindro Animado (Perspectiva en Paralelo)
*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import static java.lang.Math.PI;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Proyecto3erParcial extends JFrame implements KeyListener, Runnable{
	private BufferedImage buffer;
	private Graphics pixel;
	private int x1, y1, x2, y2, x, y, condicion;
	private int A, B, p, dx, dy;
	private int xglobal, yglobal;
	private double xaumento = 0, yaumento = 0, zaumento = 0, raumento = 0, caumento = 0;
	private double xaumento2 = 0, yaumento2 = 0, zaumento2 = 0;
	private double xaumento3 = 0, yaumento3 = 0, zaumento3 = 0;
	private float m;
	private Thread thread;

	public Proyecto3erParcial(){
		this.setTitle("Proeycto 3er Parcial");
		this.setSize(1350, 800);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		pixel = (Graphics2D) buffer.createGraphics();
		
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

		thread = new Thread(this);
	    thread.start();
	}

	public void drawCylinder3D(Graphics bu){
		int xcentro=750;
	    int ycentro=900;
	    int zcentro=500;
		int variablep = 3;
		int tempx1=0;
	    int tempy1=0;
	    int tempx2=0;
	    int tempy2=0;
	    int tempx3;
	    int tempx4;
		int yy = 0;
		double radio1 = 70; 
		double radio2, radio3;

		for(double r = 0 - raumento; r < (2 * PI - raumento); r = r + .2){
			caumento=(Math.cos(r)+1)*100;
	        radio2=radio1+((Math.cos(r)+1)*100);
	        radio3=radio1+((Math.cos(r+.2)+1)*100);
	        yy=yy-20;
	        
	        for(double u = 0; u < 2; u = u + 2){ 
	            for(double z = 0; z <= 3.11; z = z + .1){
		            tempx1 = getVertice1((int)(xcentro+radio2*Math.cos(z+u)), variablep, (int)(zcentro+radio2*Math.sin(z+u)), variablep, (int)(yy+ycentro), variablep);
		            tempy1 = getVertice2((int)(yy+ycentro), variablep, (int)((zcentro+radio2*Math.sin(z+u))), variablep, (int)(xcentro+radio2*Math.cos(z+u)), variablep);
		            tempx2 = getVertice1((int)(xcentro+radio2*Math.cos(z+.1)), variablep, (int)(zcentro+radio2*Math.sin(z+.1)), variablep, (int)(yy+ycentro), variablep);
		            tempy2 = getVertice2((int)(yy+ycentro), variablep, (int)((zcentro+radio2*Math.sin(z+.1))), variablep, (int)(xcentro+radio2*Math.cos(z+.1)), variablep);
		            tempx3 = getVertice1((int)(xcentro+radio3*Math.cos(z+u)), variablep, (int)(zcentro+radio3*Math.sin(z+u)), variablep, (int)(yy-20+ycentro), variablep);
		            tempx4 = getVertice2((int)(yy-20+ycentro), variablep, (int)((zcentro+radio3*Math.sin(z+u))), variablep, (int)(xcentro+radio3*Math.cos(z+u)), variablep);
		            drawLine3D((int)tempx1, tempy1, (int)tempx2, tempy2, bu);

		            if(r < 6.1 - raumento){
		            	drawLine3D((int)tempx1, tempy1, (int)tempx3, tempx4, bu);          
		            }

		            tempx1 = getVertice1((int)(xcentro+radio2*Math.cos(z+u)*-1), variablep, (int)(zcentro+radio2*Math.sin(z+u)*-1), variablep, (int)(yy+ycentro), variablep);
		            tempy1 = getVertice2((int)(yy+ycentro), variablep, (int)((zcentro+radio2*Math.sin(z+u)*-1)), variablep, (int)(xcentro+radio2*Math.cos(z+u)*-1), variablep);
		            tempx2 = getVertice1((int)(xcentro+radio2*Math.cos(z+.1)*-1), variablep, (int)(zcentro+radio2*Math.sin(z+.1)*-1), variablep, (int)(yy+ycentro), variablep);
		            tempy2 = getVertice2((int)(yy+ycentro), variablep, (int)((zcentro+radio2*Math.sin(z+.1)*-1)), variablep, (int)(xcentro+radio2*Math.cos(z+.1)*-1), variablep);
		            tempx3 = getVertice1((int)(xcentro+radio3*Math.cos(z+u)*-1), variablep, (int)(zcentro+radio3*Math.sin(z+u)*-1), variablep, (int)(yy-20+ycentro), variablep);
		            tempx4 = getVertice2((int)(yy-20+ycentro), variablep, (int)((zcentro+radio3*Math.sin(z+u)*-1)), variablep, (int)(xcentro+radio3*Math.cos(z+u)*-1), variablep);
		            drawLine3D((int)tempx1, tempy1, (int)tempx2, tempy2, bu);   

		            if( r < 6.1 - raumento){
		            	drawLine3D((int)tempx1, tempy1, (int)tempx3, tempx4, bu);  
		            }
	            }    
	        }
		}
	}

	public void drawCubo3D(Graphics bu){
		int xCentro = 900;
		int yCentro = 475;
		int zCentro = 1100;

		int diametro = (100 / 2);
		int variableP = 3;
		int tempx1 = 0;
		int tempy1 = 0;
		int tempx2 = 0;
		int tempy2 = 0;

		//Dibujar el lado inferior del vertice de atras de X
		tempx1 = getVertice3((xCentro+diametro), variableP, (zCentro+diametro), variableP, (yCentro+diametro), variableP);
		tempy1 = getVertice4((yCentro+diametro), variableP, (zCentro+diametro), variableP, (xCentro+diametro), variableP);
		tempx2 = getVertice3((xCentro-diametro), variableP, (zCentro+diametro), variableP, (yCentro+diametro), variableP);
		tempy2 = getVertice4((yCentro+diametro), variableP, (zCentro+diametro), variableP, (xCentro-diametro), variableP);
		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);
		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);
		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

		//Dibujar el lado lateral derecho del vertice de atras Y
		tempx2 = getVertice3((xCentro+diametro), variableP, (zCentro+diametro), variableP, (yCentro-diametro), variableP);
		tempy2 = getVertice4((yCentro-diametro), variableP, (zCentro+diametro), variableP, (xCentro+diametro), variableP);
		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

		//Dibujar el lado inferior del vertice lateral derecho en X
		tempx2 = getVertice3((xCentro+diametro), variableP, (zCentro-diametro), variableP, (yCentro+diametro), variableP);
    	tempy2 = getVertice4((yCentro+diametro), variableP, (zCentro-diametro), variableP, (xCentro+diametro), variableP);
    	drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

		//Dibujar el lado superior del vertice de atras X
		tempx1 = getVertice3((xCentro-diametro), variableP, (zCentro+diametro), variableP, (yCentro-diametro), variableP);
	    tempy1 = getVertice4((yCentro-diametro), variableP, (zCentro+diametro), variableP, (xCentro-diametro), variableP);
	    tempx2 = getVertice3((xCentro+diametro), variableP, (zCentro+diametro), variableP, (yCentro-diametro), variableP);
	    tempy2 = getVertice4((yCentro-diametro), variableP, (zCentro+diametro), variableP, (xCentro+diametro), variableP); 
	    drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

	    //Dibujar el lado lateral izquierdo del vertice de atras Y 
    	tempx2 = getVertice3((xCentro-diametro), variableP, (zCentro+diametro), variableP, (yCentro+diametro), variableP);
    	tempy2 = getVertice4((yCentro+diametro), variableP, (zCentro+diametro), variableP, (xCentro-diametro), variableP);
   		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

    	//Dibujar el lado lateral izquierdo del vertice superior en Y
   		tempx2 = getVertice3((xCentro-diametro), variableP, (zCentro-diametro), variableP, (yCentro-diametro), variableP);
    	tempy2 = getVertice4((yCentro-diametro), variableP, (zCentro-diametro), variableP, (xCentro-diametro), variableP);    
   		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

   		//Dibujar el lado inferior del vertice superior en X
   		tempx1 = getVertice3((xCentro+diametro), variableP, (zCentro-diametro), variableP, (yCentro-diametro), variableP);
	    tempy1 = getVertice4((yCentro-diametro), variableP, (zCentro-diametro), variableP, (xCentro+diametro), variableP);
	    tempx2 = getVertice3((xCentro-diametro), variableP, (zCentro-diametro), variableP, (yCentro-diametro), variableP);
	    tempy2 = getVertice4((yCentro-diametro), variableP, (zCentro-diametro), variableP, (xCentro-diametro), variableP);
		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

		//Dibujar el lado lateral derecho del vertice superior en Y
		tempx2 = getVertice3((xCentro+diametro), variableP, (zCentro+diametro), variableP, (yCentro-diametro), variableP);
    	tempy2 = getVertice4((yCentro-diametro), variableP, (zCentro+diametro), variableP, (xCentro+diametro), variableP);
    	drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

    	//Dibujar el lado vertical derecho del vertice lateral derecho en Y
    	tempx2 = getVertice3((xCentro+diametro), variableP, (zCentro-diametro), variableP, (yCentro+diametro), variableP);
    	tempy2 = getVertice4((yCentro+diametro), variableP, (zCentro-diametro), variableP, (xCentro+diametro), variableP);
    	drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

    	//Dibujar el lado vertical izquierdo del vertice frontal en Y
    	tempx1 = getVertice3((xCentro-diametro), variableP, (zCentro-diametro), variableP, (yCentro+diametro), variableP);
    	tempy1 = getVertice4((yCentro+diametro), variableP, (zCentro-diametro), variableP, (xCentro-diametro), variableP);
    	tempx2 = getVertice3((xCentro-diametro), variableP, (zCentro-diametro), variableP, (yCentro-diametro), variableP);
    	tempy2 = getVertice4((yCentro-diametro), variableP, (zCentro-diametro), variableP, (xCentro-diametro), variableP);
   		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

   		//Dibujar el lado inferior del vertice frontal de en X
   		tempx2 = getVertice3((xCentro+diametro), variableP, (zCentro-diametro), variableP, (yCentro+diametro), variableP);
    	tempy2 = getVertice4((yCentro+diametro), variableP, (zCentro-diametro), variableP, (xCentro+diametro), variableP);
    	drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

    	//Dibujar el lado inferior del vertice lateral izquierdo en X
    	tempx2 = getVertice3((xCentro-diametro), variableP, (zCentro+diametro), variableP, (yCentro+diametro), variableP);
    	tempy2 = getVertice4((yCentro+diametro), variableP, (zCentro+diametro), variableP, (xCentro-diametro), variableP);
    	drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);
	}

	public void drawCubo23D(Graphics bu){
		int xCentro = 900;
		int yCentro = 925;
		int zCentro = 1100;

		int diametro = (100 / 2);
		int variableP = 3;
		int tempx1 = 0;
		int tempy1 = 0;
		int tempx2 = 0;
		int tempy2 = 0;

		//Dibujar el lado inferior del vertice de atras de X
		tempx1 = getVertice5((xCentro+diametro), variableP, (zCentro+diametro), variableP, (yCentro+diametro), variableP);
		tempy1 = getVertice6((yCentro+diametro), variableP, (zCentro+diametro), variableP, (xCentro+diametro), variableP);
		tempx2 = getVertice5((xCentro-diametro), variableP, (zCentro+diametro), variableP, (yCentro+diametro), variableP);
		tempy2 = getVertice6((yCentro+diametro), variableP, (zCentro+diametro), variableP, (xCentro-diametro), variableP);
		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);
		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);
		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

		//Dibujar el lado lateral derecho del vertice de atras Y
		tempx2 = getVertice5((xCentro+diametro), variableP, (zCentro+diametro), variableP, (yCentro-diametro), variableP);
		tempy2 = getVertice6((yCentro-diametro), variableP, (zCentro+diametro), variableP, (xCentro+diametro), variableP);
		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

		//Dibujar el lado inferior del vertice lateral derecho en X
		tempx2 = getVertice5((xCentro+diametro), variableP, (zCentro-diametro), variableP, (yCentro+diametro), variableP);
    	tempy2 = getVertice6((yCentro+diametro), variableP, (zCentro-diametro), variableP, (xCentro+diametro), variableP);
    	drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

		//Dibujar el lado superior del vertice de atras X
		tempx1 = getVertice5((xCentro-diametro), variableP, (zCentro+diametro), variableP, (yCentro-diametro), variableP);
	    tempy1 = getVertice6((yCentro-diametro), variableP, (zCentro+diametro), variableP, (xCentro-diametro), variableP);
	    tempx2 = getVertice5((xCentro+diametro), variableP, (zCentro+diametro), variableP, (yCentro-diametro), variableP);
	    tempy2 = getVertice6((yCentro-diametro), variableP, (zCentro+diametro), variableP, (xCentro+diametro), variableP); 
	    drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

	    //Dibujar el lado lateral izquierdo del vertice de atras Y 
    	tempx2 = getVertice5((xCentro-diametro), variableP, (zCentro+diametro), variableP, (yCentro+diametro), variableP);
    	tempy2 = getVertice6((yCentro+diametro), variableP, (zCentro+diametro), variableP, (xCentro-diametro), variableP);
   		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

    	//Dibujar el lado lateral izquierdo del vertice superior en Y
   		tempx2 = getVertice5((xCentro-diametro), variableP, (zCentro-diametro), variableP, (yCentro-diametro), variableP);
    	tempy2 = getVertice6((yCentro-diametro), variableP, (zCentro-diametro), variableP, (xCentro-diametro), variableP);    
   		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

   		//Dibujar el lado inferior del vertice superior en X
   		tempx1 = getVertice5((xCentro+diametro), variableP, (zCentro-diametro), variableP, (yCentro-diametro), variableP);
	    tempy1 = getVertice6((yCentro-diametro), variableP, (zCentro-diametro), variableP, (xCentro+diametro), variableP);
	    tempx2 = getVertice5((xCentro-diametro), variableP, (zCentro-diametro), variableP, (yCentro-diametro), variableP);
	    tempy2 = getVertice6((yCentro-diametro), variableP, (zCentro-diametro), variableP, (xCentro-diametro), variableP);
		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

		//Dibujar el lado lateral derecho del vertice superior en Y
		tempx2 = getVertice5((xCentro+diametro), variableP, (zCentro+diametro), variableP, (yCentro-diametro), variableP);
    	tempy2 = getVertice6((yCentro-diametro), variableP, (zCentro+diametro), variableP, (xCentro+diametro), variableP);
    	drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

    	//Dibujar el lado vertical derecho del vertice lateral derecho en Y
    	tempx2 = getVertice5((xCentro+diametro), variableP, (zCentro-diametro), variableP, (yCentro+diametro), variableP);
    	tempy2 = getVertice6((yCentro+diametro), variableP, (zCentro-diametro), variableP, (xCentro+diametro), variableP);
    	drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

    	//Dibujar el lado vertical izquierdo del vertice frontal en Y
    	tempx1 = getVertice5((xCentro-diametro), variableP, (zCentro-diametro), variableP, (yCentro+diametro), variableP);
    	tempy1 = getVertice6((yCentro+diametro), variableP, (zCentro-diametro), variableP, (xCentro-diametro), variableP);
    	tempx2 = getVertice5((xCentro-diametro), variableP, (zCentro-diametro), variableP, (yCentro-diametro), variableP);
    	tempy2 = getVertice6((yCentro-diametro), variableP, (zCentro-diametro), variableP, (xCentro-diametro), variableP);
   		drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

   		//Dibujar el lado inferior del vertice frontal de en X
   		tempx2 = getVertice5((xCentro+diametro), variableP, (zCentro-diametro), variableP, (yCentro+diametro), variableP);
    	tempy2 = getVertice6((yCentro+diametro), variableP, (zCentro-diametro), variableP, (xCentro+diametro), variableP);
    	drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);

    	//Dibujar el lado inferior del vertice lateral izquierdo en X
    	tempx2 = getVertice5((xCentro-diametro), variableP, (zCentro+diametro), variableP, (yCentro+diametro), variableP);
    	tempy2 = getVertice6((yCentro+diametro), variableP, (zCentro+diametro), variableP, (xCentro-diametro), variableP);
    	drawLine3D(tempx1, tempy1, tempx2, tempy2, bu);
	}

	public int getVertice1(int puntoOriginal, int punto1, int z1, int zp, int punto2, int punto3){
		int puntoOriginal2 = 0;
		int z12 = 0;

		//Rotacion sobre el eje Z
		puntoOriginal = puntoOriginal - 500;
		punto2 = punto2 - 500;
		puntoOriginal = (int) ((puntoOriginal*Math.cos(zaumento)) - (punto2*Math.sin(zaumento))) + 500;

		//Rotacion sobre el eje X
		z1 = z1 - 500;
		z1 = (int) ((punto2*Math.sin(xaumento)) + (z1*Math.cos(xaumento))) + 500;

		//Rotacion sobre el eje Y
		puntoOriginal = puntoOriginal - 500;
		z1 = z1 - 500;
		
		puntoOriginal2 = (int) ((puntoOriginal*Math.cos(yaumento)) + (z1*Math.sin(yaumento))) + 500;
		z12 = (int) (-1*(puntoOriginal*Math.sin(yaumento)) + (z1*Math.cos(yaumento))) + 500;

		int valor = puntoOriginal2 + punto1 - (z12 / zp);
		return valor;
	}

	public int getVertice2(int puntoOriginal, int punto1, int z1, int zp, int punto2, int punto3){
		//Rotacion sobre el eje Z
		puntoOriginal = puntoOriginal - 500;
		punto2 = punto2 - 500;
		puntoOriginal = (int) ((punto2*Math.sin(zaumento)) + (puntoOriginal*Math.cos(zaumento))) + 500;

		//Rotacion sobre el eje Y		
		z1 = z1 - 500;
		z1 = (int) (-1*(punto2*Math.sin(yaumento)) + (z1*Math.cos(yaumento))) + 500;

		//Rotacion sobre el eje X
		int puntoOriginal2 = 0;
		int z12 = 0;
		
		puntoOriginal = puntoOriginal - 500;
		z1 = z1 - 500;
		
		z12 = (int) ((puntoOriginal*Math.sin(xaumento)) + (z1*Math.cos(xaumento))) + 500;
		puntoOriginal2 = (int) ((puntoOriginal*Math.cos(xaumento)) - (z1*Math.sin(xaumento))) + 500;

		int valor = puntoOriginal2 + punto1 - (z12 / zp);
		return valor;
	}

	public int getVertice3(int puntoOriginal, int punto1, int z1, int zp, int punto2, int punto3){
		int puntoOriginal2 = 0;
		int z12 = 0;

		//Rotacion sobre el eje Z
		puntoOriginal = puntoOriginal - 900;
		punto2 = punto2 - 900;
		puntoOriginal = (int) ((puntoOriginal*Math.cos(zaumento3)) - (punto2*Math.sin(zaumento3))) + 900;

		//Rotacion sobre el eje X
		z1 = z1 - 900;
		z1 = (int) ((punto2*Math.sin(xaumento3)) + (z1*Math.cos(xaumento3))) + 900;

		//Rotacion sobre el eje Y
		puntoOriginal = puntoOriginal - 900;
		z1 = z1 - 900;
		
		puntoOriginal2 = (int) ((puntoOriginal*Math.cos(yaumento3)) + (z1*Math.sin(yaumento3))) + 900;
		z12 = (int) (-1*(puntoOriginal*Math.sin(yaumento3)) + (z1*Math.cos(yaumento3))) + 900;

		int valor = puntoOriginal2 + punto1 - (z12 / zp);
		return valor;
	}

	public int getVertice4(int puntoOriginal, int punto1, int z1, int zp, int punto2, int punto3){
		//Rotacion sobre el eje Z
		puntoOriginal = puntoOriginal - 900;
		punto2 = punto2 - 900;
		puntoOriginal = (int) ((punto2*Math.sin(zaumento3)) + (puntoOriginal*Math.cos(zaumento3))) + 900;

		//Rotacion sobre el eje Y		
		z1 = z1 - 900;
		z1 = (int) (-1*(punto2*Math.sin(yaumento3)) + (z1*Math.cos(yaumento3))) + 900;

		//Rotacion sobre el eje X
		int puntoOriginal2 = 0;
		int z12 = 0;
		
		puntoOriginal = puntoOriginal - 900;
		z1 = z1 - 900;
		
		z12 = (int) ((puntoOriginal*Math.sin(xaumento3)) + (z1*Math.cos(xaumento3))) + 900;
		puntoOriginal2 = (int) ((puntoOriginal*Math.cos(xaumento3)) - (z1*Math.sin(xaumento3))) + 900;

		int valor = puntoOriginal2 + punto1 - (z12 / zp);
		return valor;
	}

	public int getVertice5(int puntoOriginal, int punto1, int z1, int zp, int punto2, int punto3){
		int puntoOriginal2 = 0;
		int z12 = 0;

		//Rotacion sobre el eje Z
		puntoOriginal = puntoOriginal - 900;
		punto2 = punto2 - 900;
		puntoOriginal = (int) ((puntoOriginal*Math.cos(zaumento2)) - (punto2*Math.sin(zaumento2))) + 900;

		//Rotacion sobre el eje X
		z1 = z1 - 900;
		z1 = (int) ((punto2*Math.sin(xaumento2)) + (z1*Math.cos(xaumento2))) + 900;

		//Rotacion sobre el eje Y
		puntoOriginal = puntoOriginal - 900;
		z1 = z1 - 900;
		
		puntoOriginal2 = (int) ((puntoOriginal*Math.cos(yaumento2)) + (z1*Math.sin(yaumento2))) + 900;
		z12 = (int) (-1*(puntoOriginal*Math.sin(yaumento2)) + (z1*Math.cos(yaumento2))) + 900;

		int valor = puntoOriginal2 + punto1 - (z12 / zp);
		return valor;
	}

	public int getVertice6(int puntoOriginal, int punto1, int z1, int zp, int punto2, int punto3){
		//Rotacion sobre el eje Z
		puntoOriginal = puntoOriginal - 900;
		punto2 = punto2 - 900;
		puntoOriginal = (int) ((punto2*Math.sin(zaumento2)) + (puntoOriginal*Math.cos(zaumento2))) + 900;

		//Rotacion sobre el eje Y		
		z1 = z1 - 900;
		z1 = (int) (-1*(punto2*Math.sin(yaumento2)) + (z1*Math.cos(yaumento2))) + 900;

		//Rotacion sobre el eje X
		int puntoOriginal2 = 0;
		int z12 = 0;
		
		puntoOriginal = puntoOriginal - 900;
		z1 = z1 - 900;
		
		z12 = (int) ((puntoOriginal*Math.sin(xaumento2)) + (z1*Math.cos(xaumento2))) + 900;
		puntoOriginal2 = (int) ((puntoOriginal*Math.cos(xaumento2)) - (z1*Math.sin(xaumento2))) + 900;

		int valor = puntoOriginal2 + punto1 - (z12 / zp);
		return valor;
	}

	public void drawLine3D(int x1, int y1, int x2, int y2, Graphics bu){
		int temp1, temp2;
        
        if(x1 > x2){
	        temp1=x2;
	        temp2=y2;
	        x2=x1;
	        y2=y1;
	        x1=temp1;
	        y1=temp2;
        }
        
        x = x1;
        y = y1;        
        dx = (x2-x1);
        dy = (y2-y1);
         
        if((dx) != 0){
        	m = (((float)dy) / ((float)dx));   
        
        	if(m == 0){
            	if((dy)==0){
                	if(x1>x2){
                    	x = x1;
	                    for( ;x != x1; x--){
	                        xglobal = x;
	                        yglobal = y;
	                        putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
	                    }
                	}
                	else if(x1 < x2){
	                    x = x1;
	                    for( ;x != x2; x++){
	                        xglobal=x;
	                        yglobal=y;
	                        putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
	                	}
                	}
            	}
            	else if((dx) == 0){
                	if(y1 > y2){
	                    y = y1;
	                    for( ;y != y2; y--){
	                        xglobal = x;
	                        yglobal = y;
	                        putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
	                    }
                	}
	                else if(y1 < y2){
	                    y = y1;
	                    for( ;y != y2; y++){
	                    	xglobal=x;
	                        yglobal=y;
	                        putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
	                    }
	                }
            	}
        	}        
	        else{	        
	        	if(m >= 0 && m <= 1){
			        putPixel2(x1, y1, new Color(51,162,255-(int)caumento), bu);
			        A = 2 * dy;
			        B = 2 * dy - 2 * dx;
			        p = 2 * dy - dx;
		        
		   			for(int k = 1; k <= dx; k++){
				        if(p < 0){
				            x = x + 1;
				        	putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
				        	p = p + A;
				        }
				        else if(p >= 0){
				            x = x + 1;
				            y = y + 1;
				        	putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
				        	p = p + B;    
				        }
				    }
	        	}	       
		        else if(m > 1){ 
			       	putPixel2(x1, y1, new Color(51,162,255-(int)caumento), bu);
			       	A = 2 * dx;
			       	B = 2 * dx - 2 * dy;
			       	p = 2 * dx - dy;
			       
			       	for(int k = 1; k <= dy; k++){		          
				        if(p < 0){
				           	y = y + 1;
				       		putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
				        	p = p + A;
				        }
				        else if(p >= 0){					            
					        y = y + 1;
					        x = x + 1;
					       	putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
					       	p = p + B;
					    }			        
				    }
		       	}	        
		        else if(m < 0 && m >= -1){
		        	putPixel2(x1, y1, new Color(51,162,255-(int)caumento), bu);
		        	A = 2 * Math.abs(dy);
		        	B = 2 * Math.abs(dy) - 2 * Math.abs(dx);
		        	p = 2 * Math.abs(dy) - Math.abs(dx);
		       
		       		for(int k = 1; k <= dx; k++){    
				        if(p < 0){
				            x = x + 1;
				        	putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
				        	p = p + A;
				        }
				        else if(p >= 0){
				            x = x + 1;
				            y = y - 1;
				        	putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
				        	p = p + B;
				        }
				    }
				}
		        else if(m < -1){	           
			        putPixel2(x1, y1, new Color(51,162,255-(int)caumento), bu);
			        A = 2 * dx;
			        B = 2 * dx - 2 * Math.abs(dy);
			        p = 2 * dx - Math.abs(dy);
		        
		        	for(int k = 1; k <= Math.abs(dy); k++){     
				        if(p < 0){
				            y = y - 1;
					        putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
					        p = p + A;
				        }
				        else if( p >= 0){
				            y = y - 1;
				            x = x + 1;
					        putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
					        p = p + B;
				        }
				    }
				}
		    }
		}
        else{  
            if(y1 > y2){        
                y = y1;
                for( ;y != y2; y--){                        
                    xglobal = x;
                    yglobal = y;
                    putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);                    
                }
            }
            else if(y1 < y2){
                y = y1;
                for( ;y != y2; y++){
                    xglobal = x;
                    yglobal = y;
                    putPixel2(x, y, new Color(51,162,255-(int)caumento), bu);
                }
            }        
        }
	}

	public void keyTyped(KeyEvent ke){ }

	public void keyPressed(KeyEvent ke){
		int key = ke.getKeyCode();

		if(key == KeyEvent.VK_D){
			valorAnimacion(1);
			repaint();
		}
		else if(key == KeyEvent.VK_A){
			valorAnimacion(2);
			repaint();
		}
		else if(key == KeyEvent.VK_W){
			valorAnimacion(3);
			repaint();
		}
		else if(key == KeyEvent.VK_S){
			valorAnimacion(4);
			repaint();
		}
		else if(key == KeyEvent.VK_Q){
			valorAnimacion(5);
			repaint();
		}
		else if(key == KeyEvent.VK_E){
			valorAnimacion(6);
			repaint();
		}
		else if(key == KeyEvent.VK_T){
			thread.interrupt();
			
		}
	}

	public void keyReleased(KeyEvent ke){ }

	public void paint(Graphics g){
		Dimension d = getSize();
		Image imagen = createImage(d.width, d.height);
		Graphics buff = imagen.getGraphics();
		drawCylinder3D(buff);
		drawCubo3D(buff);
		drawCubo23D(buff);
		g.drawImage(imagen, 0, 0, null);
		update(g);
	}

	public void update(Graphics g){	}

	public void run(){
		while(true){
			try{
				sleep(50);
			}catch(InterruptedException ex){
				Logger.getLogger(Proyecto3erParcial.class.getName()).log(Level.SEVERE, null, ex);
			}
			raumento = raumento + 0.1;
			yaumento2 = yaumento2 + 0.1; //Rotacion en Z
			yaumento3 = yaumento3 - 0.1;
			
			if(condicion == 1){
				zaumento = zaumento + .01;
				zaumento2 = zaumento2 + .01;
				zaumento3 = zaumento3 + .01;
			}
			else if(condicion == 2){
				zaumento = zaumento - .01;
				zaumento2 = zaumento2 - .01;
				zaumento3 = zaumento3 - .01;
			}
			else if(condicion == 3){
				xaumento = xaumento + .01;
				xaumento2 = xaumento2 +.01;
				xaumento3 = xaumento3 + .01;
			}
			else if(condicion == 4){
				xaumento = xaumento - .01;
				xaumento2 = xaumento2 - .01;
				xaumento3 = xaumento3 - .01;
			}
			else if(condicion == 5){
				yaumento = yaumento + .01;
				yaumento2 = yaumento2 + .01;
				yaumento3 = yaumento3 + .01;
			}
			else if(condicion == 6){
				yaumento = yaumento - .01;
				yaumento2 = yaumento2 - .01;
				yaumento3 = yaumento3 - .01;
			}
			repaint();
		}
	}

	public void valorAnimacion(int valor){
		this.condicion = valor;
	}

	public void putPixel(int x, int y, Color color){
		buffer.setRGB(0, 0, color.getRGB());
		this.getGraphics().drawImage(buffer, x, y, this);
	}

	public void putPixel2(int x, int y, Color color, Graphics bu){
		buffer.setRGB(0, 0, color.getRGB());
		bu.drawImage(buffer, x, y, this);
	}

	public static void main(String[] args){
		Proyecto3erParcial proyecto = new Proyecto3erParcial();
		JOptionPane.showMessageDialog(null, "Manejo para la Rotacion del cilindro \n" 
			+ "Teclas 'A' y 'D', rotar sobre el eje Z \n" 
			+ "Teclas 'W' y 'S', rotar sobre el eje X \n" 
			+ "Teclas 'Q' y 'E', rotar sobre el eje Y");
	}
}