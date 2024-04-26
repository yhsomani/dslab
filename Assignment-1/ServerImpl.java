import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject
	implements ServerIntf {
	
		public ServerImpl() throws RemoteException{
		
		}
		public String stringJoin(String str1, String str2) throws RemoteException{
			String result = str1 + str2;

			return result;
		}
}
		

