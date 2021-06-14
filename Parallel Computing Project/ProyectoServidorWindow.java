import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ProyectoServidorWindow extends JFrame implements ProyectoInterface {
	private JPanel panel;
	private JLabel label;
	private JButton button;
	private JTextArea textArea;
	private JScrollPane scrollPane;
    private JTextField fdServidor, fdPuerto;

    public ProyectoServidorWindow() {
    	setSize(435, 365);
		setTitle("Conexion con el Servidor");
		setLocationRelativeTo(null);

		initComponents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    }	

    private void initComponents() {
    	panel = new JPanel();
    	panel.setLayout(null);
    	this.add(panel);

    	label = new JLabel();
    	label.setBounds(20, 30, 180, 30);
    	label.setText("IP Servidor:");
    	label.setFont(new Font("cooper black", 0, 20));
    	panel.add(label);

    	fdServidor = new JTextField();
    	fdServidor.setBounds(210, 30, 180, 30);
    	fdServidor.setEnabled(true);
    	fdServidor.setHorizontalAlignment(JTextField.CENTER);
    	panel.add(fdServidor);

		label = new JLabel();
    	label.setBounds(20, 80, 180, 30);
    	label.setText("Puerto Servidor:");
    	label.setFont(new Font("cooper black", 0, 20));
    	panel.add(label);

    	fdPuerto = new JTextField();
    	fdPuerto.setBounds(210, 80, 180, 30);
    	fdPuerto.setEnabled(true);
    	fdPuerto.setHorizontalAlignment(JTextField.CENTER);
    	panel.add(fdPuerto);

    	textArea = new JTextArea();
        textArea.setEditable(false);
    	scrollPane = new JScrollPane(textArea);
    	scrollPane.setBounds(20, 130, 370, 100);
    	panel.add(scrollPane);

    	button = new JButton();
    	button.setBounds(60, 250, 280, 30);
    	button.setText("Conectar Servidor");
    	button.setFont(new Font("cooper black", 0, 20));
    	button.setHorizontalAlignment(JTextField.CENTER);
    	panel.add(button);	
    	
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionEventConectar(evt);
            }
        });
    }

    private void actionEventConectar(java.awt.event.ActionEvent evt) {
        if (fdServidor.getText().trim().equals("") || fdPuerto.getText().trim().equals("")) {
        	JOptionPane.showMessageDialog(null, "El campo IP Servidor y/o Puerto Servidor no pueden estar vacios");
       	} 
       	else {
       		String servidorIP = fdServidor.getText().trim();
      		int servidorPuerto = Integer.parseInt(fdPuerto.getText().trim());

           	startServer(servidorIP, servidorPuerto);
   		}            
	}

    public void updateServer() {
        fdServidor.setEditable(false);
        fdPuerto.setEditable(false);
        textArea.setEditable(false); 
        button.setEnabled(false);
    }

    public void startServer(String servidorIP, int servidorPuerto) {
        int PORT = servidorPuerto;
        String message1, message2, message3, message4;
    
        try {
            //System.out.println("IP y puerto de servidor: " + servidorIP + ": " + PORT);
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.bind("serverOperation", (ProyectoInterface)this);

            message1 = ("Iniciando la conexion con el servidor -> " 
                + servidorIP + ":" + servidorPuerto + "\n");
        
            message2 = ("Conectando con el servidor -> " 
                + servidorIP + ":" + servidorPuerto + "\n");

            message3 = ("Servidor Conectado -> " 
                + servidorIP + ":" + servidorPuerto + "\n");

            textArea.setText(message1 + message2 + message3);
            updateServer();
        } 
        catch (Exception e) {
            message4 = ("Fallo en la conexion con el servidor -> " 
                + servidorIP + ":" + servidorPuerto + "\n");

            textArea.setText(message4);
        }
    }

    public double[][] getMatrices(double [][] matriz1, double [][] matriz2) throws RemoteException{
        ProyectoParallel parallel = new ProyectoParallel();
        double [][] resultParallel;

        resultParallel = parallel.multiplicationParallel(matriz1,matriz2);
        /*for(int x = 0; x < resultParallel.length; x++){
            for(int y = 0; y < resultParallel[0].length; y++){
                System.out.println(resultParallel[x][y]);
            }
        }*/

        return  resultParallel;
    }
}
