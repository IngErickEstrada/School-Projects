import java.awt.Font;
import java.util.Random;
import java.lang.Runtime;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ProyectoClienteWindow extends JFrame{
	private JPanel panel;
	private JLabel label;
	private JScrollPane scrollPaneA, scrollPaneB, scrollPaneR,
						scrollPaneM;
	private JTextArea areaMatrizA, areaMatrizB, areaMatrizR,
						areaMatrizM;
	private JButton buttonSequential, buttonConcurrent, buttonParallel,
						buttonMatriz, buttonServidor;
	private JTextField fdConcurrent, fdSequential, fdParallel, fdCores,
						fdColumns, fdRows, fdIPServidor, fdPuertoServidor;

	ProyectoCrearMatriz matriz = new ProyectoCrearMatriz();
	ProyectoSequential sequential = new ProyectoSequential();
	ProyectoConcurrent concurrent = new ProyectoConcurrent();

	private long begind, end;
	private int servidorPuerto;
	private double [][] matriz1, matriz2;
	private double timeSequential, timeConcurrent, timeParallel;
	private String servidorIP, message1, message2, message3, message4;
	
	public ProyectoClienteWindow(){
		setSize(1030, 670);
		setTitle("Multiplicacion de Matrices");
		setLocationRelativeTo(null);

		initComponents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initComponents(){
		Runtime hardware = Runtime.getRuntime();
		int cores = hardware.availableProcessors();
	
		panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);

		label = new JLabel();
		label.setBounds(35, 30, 300, 30);
		label.setText("IP y Puerto Servidor");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);	

		fdIPServidor = new JTextField();
		fdIPServidor.setBounds(35, 80, 100, 30);
		fdIPServidor.setEnabled(true);
		fdIPServidor.setHorizontalAlignment(JTextField.CENTER);
		panel.add(fdIPServidor);

		label = new JLabel();
		label.setBounds(138, 80, 300, 30);
		label.setText(":");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);	

		fdPuertoServidor = new JTextField();
		fdPuertoServidor.setBounds(148, 80, 100, 30);
		fdPuertoServidor.setEnabled(true);
		fdPuertoServidor.setHorizontalAlignment(JTextField.CENTER);
		panel.add(fdPuertoServidor);			

		areaMatrizM = new JTextArea();
		areaMatrizM.setEditable(false);
		scrollPaneM = new JScrollPane(areaMatrizM);
		scrollPaneM.setBounds(35, 130, 210, 190);
		panel.add(scrollPaneM);

		buttonServidor = new JButton();
		buttonServidor.setBounds(35, 345, 210, 30);
		buttonServidor.setText("Conectar Cliente");
		buttonServidor.setFont(new Font("cooper black", 0, 20));
		panel.add(buttonServidor);
		ActionEventConectar();		

		label = new JLabel();
		label.setBounds(345, 30, 180, 30);
		label.setText("Matriz A");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);

		areaMatrizA = new JTextArea();
		areaMatrizA.setEditable(false);
		scrollPaneA = new JScrollPane(areaMatrizA);
		scrollPaneA.setBounds(280, 80, 210, 300);
		panel.add(scrollPaneA);

		label = new JLabel();
		label.setBounds(585, 30, 180, 30);
		label.setText("Matriz B");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);

		areaMatrizB = new JTextArea();
		areaMatrizB.setEditable(false);
		scrollPaneB = new JScrollPane(areaMatrizB);
		scrollPaneB.setBounds(520, 80, 210, 300);
		panel.add(scrollPaneB);

		label = new JLabel();
		label.setBounds(775, 30, 200, 30);
		label.setText("Matriz Resultante");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);

		areaMatrizR = new JTextArea();
		areaMatrizR.setEditable(false);
		scrollPaneR = new JScrollPane(areaMatrizR);
		scrollPaneR.setBounds(760, 80, 210, 300);
		panel.add(scrollPaneR);

		label = new JLabel();
		label.setBounds(70, 400, 200, 30);
		label.setText("Numero Filas");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);

		fdRows = new JTextField();
		fdRows.setBounds(90, 450, 100, 30);
		fdRows.setEnabled(true);
		fdRows.setHorizontalAlignment(JTextField.CENTER);
		fdRows.setText("1100");
		panel.add(fdRows);

		label = new JLabel();
		label.setBounds(45, 500, 200, 30);
		label.setText("Numero Columnas");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);

		fdColumns = new JTextField();
		fdColumns.setBounds(90, 550, 100, 30);
		fdColumns.setEnabled(true);
		fdColumns.setHorizontalAlignment(JTextField.CENTER);
		fdColumns.setText("1100");
		panel.add(fdColumns);

		buttonMatriz = new JButton();
		buttonMatriz.setBounds(280, 550, 210, 30);
		buttonMatriz.setText("Nueva Matriz");
		buttonMatriz.setFont(new Font("cooper black", 0, 20));
		panel.add(buttonMatriz);
		ActionEventMatriz();

		buttonSequential = new JButton();
		buttonSequential.setBounds(280, 400, 210, 30);
		buttonSequential.setText("Secuencial");
		buttonSequential.setFont(new Font("cooper black", 0, 20));
		buttonSequential.setEnabled(false);
		panel.add(buttonSequential);
		actionEventSequential();

		label = new JLabel();
		label.setBounds(520, 400, 300, 30);
		label.setText("Tiempo Secuencial");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);

		fdSequential = new JTextField();
		fdSequential.setBounds(760, 400, 100, 30);
		fdSequential.setEnabled(false);
		fdSequential.setHorizontalAlignment(JTextField.CENTER);
		fdSequential.setText("0 ms");
		panel.add(fdSequential);

		buttonConcurrent = new JButton();
		buttonConcurrent.setBounds(280, 450, 210, 30);
		buttonConcurrent.setText("Concurrente");
		buttonConcurrent.setFont(new Font("cooper black", 0, 20));
		buttonConcurrent.setEnabled(false);
		panel.add(buttonConcurrent);
		actionEventConcurrent();

		label = new JLabel();
		label.setBounds(520, 450, 300, 30);
		label.setText("Tiempo Concurrente");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);

		fdConcurrent = new JTextField();
		fdConcurrent.setBounds(760, 450, 100, 30);
		fdConcurrent.setEnabled(false);
		fdConcurrent.setHorizontalAlignment(JTextField.CENTER);
		fdConcurrent.setText("0 ms");
		panel.add(fdConcurrent);

		buttonParallel = new JButton();
		buttonParallel.setBounds(280, 500, 210, 30);
		buttonParallel.setText("Paralelo");
		buttonParallel.setFont(new Font("cooper black", 0, 20));
		buttonParallel.setEnabled(false);
		panel.add(buttonParallel);
		actionEventParallel();

		label = new JLabel();
		label.setBounds(520, 500, 300, 30);
		label.setText("Tiempo Paralelo");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);

		fdParallel = new JTextField();
		fdParallel.setBounds(760, 500, 100, 30);
		fdParallel.setEnabled(false);
		fdParallel.setHorizontalAlignment(JTextField.CENTER);
		fdParallel.setText("0 ms");
		panel.add(fdParallel);

		label = new JLabel();
		label.setBounds(520, 550, 350, 30);
		label.setText("Numero de nucleos del equipo ");
		label.setFont(new Font("cooper black", 0, 20));
		panel.add(label);

		fdCores = new JTextField();
		fdCores.setBounds(870, 550, 100, 30);
		fdCores.setEnabled(false);
		fdCores.setHorizontalAlignment(JTextField.CENTER);
		fdCores.setText(cores + " Nucleos");
		panel.add(fdCores);
	}

	public void updateButtons(){
		buttonSequential.setEnabled(true);
		buttonConcurrent.setEnabled(true);
	}

	public void updateCliente(){
        fdIPServidor.setEditable(false);
        fdPuertoServidor.setEditable(false);
        areaMatrizM.setEditable(false); 
        buttonServidor.setEnabled(false);
        buttonParallel.setEnabled(true);
    }

	private void ActionEventConectar(){
		ActionListener event = new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent ae) {
            	if (fdIPServidor.getText().trim().equals("") || fdPuertoServidor.getText().trim().equals("")) {
        			JOptionPane.showMessageDialog(null, "El campo IP Servidor y/o Puerto Servidor no pueden estar vacios");
       			} 
       			else {
       				servidorIP = fdIPServidor.getText().trim();
      				servidorPuerto = Integer.parseInt(fdPuertoServidor.getText().trim());

      				message1 = ("Iniciando la conexion con el servidor -> " 
                		+ servidorIP + ":" + servidorPuerto + "\n");
        
            		message2 = ("Conectando con el servidor -> " 
                		+ servidorIP + ":" + servidorPuerto + "\n");

            		message3 = ("Servidor Conectado -> " 
                		+ servidorIP + ":" + servidorPuerto + "\n");

            		areaMatrizM.append(message1 + message2 + message3);
      				updateCliente();
   				}  
            }
		};
    	
    	buttonServidor.addActionListener(event);
	}

	private void actionEventSequential(){
		ActionListener event = new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent ae) {
            	areaMatrizR.setText("");       
            	methodSequential();
            }
        };
		
		buttonSequential.addActionListener(event);
	}

	private void actionEventConcurrent(){
		ActionListener event = new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent ae) {
            	areaMatrizR.setText("");
            	methodConcurrent();
            }
        };
		
		buttonConcurrent.addActionListener(event);
	}

	private void actionEventParallel(){
		ActionListener event = new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent ae) {
            	areaMatrizR.setText("");
            	methodParallel();
            }
        };
		
		buttonParallel.addActionListener(event);
	}

	private void ActionEventMatriz(){
		ActionListener event = new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent ae) {
            	areaMatrizA.setText("");
            	areaMatrizB.setText("");
            	areaMatrizR.setText("");
            	createdMatriz();
            	updateButtons();
            }
        };
		
		buttonMatriz.addActionListener(event);
	}

	private boolean validationInt(String cadena){
		int numeric;

		try{
			numeric = Integer.parseInt(cadena);
			return true;
		} catch(Exception e){
			return false;
		}
	}

	private void createdMatriz(){		
		int columns, rows;
		double [][] m1, m2;
		boolean valR = validationInt(fdRows.getText());
		boolean valC = validationInt(fdColumns.getText());

		if((valR == false) || (valC == false)){
			JOptionPane.showMessageDialog(null, "Solo se admiten valores numericos en las filas y columnas");
		}
		else{
			if((fdRows.getText().length() == 0) || (fdColumns.getText().length() == 0)){
				JOptionPane.showMessageDialog(null, "No puede haber elementos vacios en las filas y columnas");
			}
			else{
				columns = Integer.parseInt(fdColumns.getText());
				rows = Integer.parseInt(fdRows.getText());

				if((columns > 1300) || (rows > 1300)){
					JOptionPane.showMessageDialog(null, "La cantidad maxima de filas y columnas que se permiten son 1300");
				}
				else{
					areaMatrizM.append("\n" + "Creando matriz " + columns + " x " + rows);

					m1 = new double[columns][rows];
					m2 = new double[columns][rows];

					matriz1 = matriz.createdMatriz(m1);
					for(int x = 0; x < matriz1.length; x++){
						for(int y = 0; y < matriz1[x].length; y++){
							areaMatrizA.append("[" + x + "][" + y + "] = " + matriz1[x][y] + "\n");
							//System.out.println(m1[x][y]);
						}
					}

					matriz2 = matriz.createdMatriz(m2);
					for(int x = 0; x < matriz2.length; x++){
						for(int y = 0; y < matriz2[x].length; y++){
							areaMatrizB.append("[" + x + "][" + y + "] = " + matriz2[x][y] + "\n");
							//System.out.println(m2[x][y]);
						}
					}

					areaMatrizM.append("\n" + "Matriz creada " + columns + " x " + rows);
				}
			}
		}
	}

	private void methodSequential(){
		double [][] resultSequential;

		areaMatrizM.append("\n\n" + "Calculando por metodo Secuencial");

		begind = System.currentTimeMillis();
		resultSequential = sequential.multiplicationSequential(matriz1, matriz2);
		end = System.currentTimeMillis();
 	
		timeSequential = (double) ((end - begind));
		fdSequential.setText(timeSequential + " ms");

		for(int x = 0; x < resultSequential.length; x++){
			for(int y = 0; y < resultSequential[0].length; y++){
				areaMatrizR.append("[" + x + "][" + y + "] = " + resultSequential[x][y] + "\n");
				//System.out.println(resultSequential[x][y]);
			}
		}

		areaMatrizM.append("\n" + "Terminando metodo Secuencial");
	}

	private void methodConcurrent(){
		double [][] resultConcurrent;

		areaMatrizM.append("\n\n" + "Calculando por metodo Concurrente");

		begind = System.currentTimeMillis();
		resultConcurrent = concurrent.multiplicationConcurrent(matriz1, matriz2);
		end = System.currentTimeMillis();

		timeConcurrent = (double) ((end - begind));
		fdConcurrent.setText(timeConcurrent + " ms");

		for(int x = 0; x < resultConcurrent.length; x++){
			for(int y = 0; y < resultConcurrent[0].length; y++){
				areaMatrizR.append("[" + x + "][" + y + "] = " + resultConcurrent[x][y] + "\n");
				//System.out.println(resultConcurrent[x][y]);
			}
		}

		areaMatrizM.append("\n" + "Terminando metodo Concurrente");
	}

	private void methodParallel(){          
        double [][] resultParallel;

        areaMatrizM.append("\n\n" + "Calculando por metodo Paralelo");

        try {
           	ProyectoInterface serverObject;
           	Registry registry = LocateRegistry.getRegistry(servidorIP, servidorPuerto);
           	serverObject = (ProyectoInterface) registry.lookup("serverOperation");

           	begind = System.currentTimeMillis();
           	resultParallel = serverObject.getMatrices(matriz1, matriz2);
           	end = System.currentTimeMillis();

           	timeParallel= (double) ((end - begind));
           	fdParallel.setText(timeParallel + " ms");   

           	for(int x = 0; x < resultParallel.length; x++){
				for(int y = 0; y < resultParallel[0].length; y++){
					areaMatrizR.append("[" + x + "][" + y + "] = " + resultParallel[x][y] + "\n");
					//System.out.println(resultParallel[x][y]);
				}
			}            

			areaMatrizM.append("\n" + "Terminando metodo Paralelo");         
        } 
        catch (RemoteException ex) {
          	Logger.getLogger(ProyectoClienteWindow.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (Exception ex) {
          	Logger.getLogger(ProyectoClienteWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
