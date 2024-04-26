import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NameComponent;
import StringReverseApp.StringReverse;
import StringReverseApp.StringReverseHelper;
import StringReverseApp.StringReversePOA;


class StringReverseImpl extends StringReversePOA{
    private ORB orb;

    public void setORB(ORB orb_value){
        this.orb = orb_value;
    }

    public String Reverse(String str1){
        String revStr = "";
        for(int i=str1.length()-1; i>=0; i--){
            revStr = revStr + str1.charAt(i);
        }
        
        return revStr;
    }

    public void shutdown(){
        orb.shutdown(false);
    }
}


public class Server{

    public static void main(String[] args){
        try{
            ORB orb = ORB.init(args, null);
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            StringReverseImpl strobj = new StringReverseImpl();
            strobj.setORB(orb);

            org.omg.CORBA.Object refobj = rootPOA.servant_to_reference(strobj);
            StringReverse href = StringReverseHelper.narrow(refobj);

            org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
            NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

            NameComponent path[] = ncref.to_name("ABC");
            ncref.rebind(path, href);

            System.out.println("Server Is Ready And Waiting....");

            for(;;){
                orb.run();
            }
 
        }catch(Exception e){
            System.out.println("Server Exception.....");
        }

        System.out.println("Server Is Exitting.....");
    }
}