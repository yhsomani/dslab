import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import StringReverseApp.StringReverse;
import StringReverseApp.StringReverseHelper;

public class Client {
    public static void main(String[] args){
        try{
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
            NamingContextExt ncref = NamingContextExtHelper.narrow(objref);
            StringReverse obj = (StringReverse) StringReverseHelper.narrow(ncref.resolve_str("ABC"));

            Scanner sc = new Scanner(System.in);
            String str1 = sc.nextLine();

            System.out.println("Reversed String Is : " + obj.Reverse(str1));
            

        }catch(Exception e){
            System.out.println("Client Exception....");
        }
    }    
}
