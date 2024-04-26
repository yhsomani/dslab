import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf {

	public ServerImpl() throws RemoteException {
	}

	public double Addition(double num1, double num2) throws RemoteException {
		return num1 + num2;
	}

	public double multi(double num1, double num2) throws RemoteException {
		return num1 * num2;
	}

	public double div(double num1, double num2) throws RemoteException {
		return num1 / num2;
	}

	public double sub(double num1, double num2) throws RemoteException {
		return num1 - num2;
	}

}
