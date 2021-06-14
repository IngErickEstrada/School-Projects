/*
    Erick Antonio Estrada Vargas 17110079 7°M
    Computacion Paralela
    Proyecto Terccer Parcial
*/

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.JTextArea;

public interface ProyectoInterface extends Remote{
  public double[][] getMatrices(double[][] matriz1, double[][] matriz2) throws RemoteException;
}