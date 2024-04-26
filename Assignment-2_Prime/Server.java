import PrimeApp.Prime;
import PrimeApp.PrimeHelper;
import PrimeApp.PrimePOA;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

class PrimeImpl extends PrimePOA{
    private ORB orb;

    public void setORB(ORB orb_value){
        this.orb = orb_value;
    }

    public String CheckPrime(int num){
        boolean flag = false;
        for(int i=2; i<=num/2; ++i){
            if(num % i == 0){
                flag = true;
                break;
            } 
        }

        if(flag){
            return "Not Prime!";
        }

        else{
            return "Prime!";
        }

    }

    public void shutdown(){
        orb.shutdown(false);
    }
}


public class Server{
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();


            PrimeImpl primeObj = new PrimeImpl();
            primeObj.setORB(orb);


            org.omg.CORBA.Object refObj = rootPOA.servant_to_reference(primeObj);
            Prime href = PrimeHelper.narrow(refObj);


            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncref = NamingContextExtHelper.narrow(objRef);
            
            NameComponent path[] = ncref.to_name("Yogesh");
            ncref.rebind(path, href);


            System.out.println("Server is Started...");


            for(;;){
                orb.run();
            }



        } catch (Exception e) {
            System.out.println("Exception at server...");
        }

        System.out.println("Server is Exitting...");
    }
}

