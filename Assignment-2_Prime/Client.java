import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import PrimeApp.Prime;
import PrimeApp.PrimeHelper;

public class Client {
    public static void main(String[] args) {
        try {
            
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncref = NamingContextExtHelper.narrow(objRef);
            Prime primeObj = PrimeHelper.narrow(ncref.resolve_str("Yogesh"));

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter Number: ");
            int num = sc.nextInt();

            String result = primeObj.CheckPrime(num);
            System.out.println("Result: " + result);

        } catch (Exception e) {
            System.out.println("Exception at Client....");
        }
    }
}
