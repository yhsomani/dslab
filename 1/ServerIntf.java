import java.rmi.Remote;
import java.rmi.RemoteException;

interface ServerIntf extends Remote {
	public double Addition(double num1, double num2) throws RemoteException;

	public double multi(double num1, double num2) throws RemoteException;

	public double sub(double num1, double num2) throws RemoteException;

	public double div(double num1, double num2) throws RemoteException;
}
