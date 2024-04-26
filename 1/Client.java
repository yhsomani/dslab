import java.rmi.*;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try {
			String serverURL = "rmi://localhost/Server";
			ServerIntf serverIntf = (ServerIntf) Naming.lookup(serverURL);

			System.out.println("Enter no 1:");
			double num1 = sc.nextDouble();

			System.out.println("Enter no 2:");
			double num2 = sc.nextDouble();

			System.out.println("Addition is: " + serverIntf.Addition(num1, num2));
			System.out.println("div is: " + serverIntf.div(num1, num2));
			System.out.println("mul is: " + serverIntf.multi(num1, num2));
			System.out.println("sub is: " + serverIntf.sub(num1, num2));

		} catch (Exception e) {
			System.out.println("Exception Occured" + e.getMessage());
		}

	}
}
