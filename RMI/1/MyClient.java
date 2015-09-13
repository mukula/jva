//java -Djava.security.policy=registerit.policy
import java.rmi.*;
import java.rmi.registry.*;
//uncomment the following interface if MyClient is present in other folder than that of MyServer or when 
//present on other PC
//interface Adder extends Remote{
//public int add(int x,int y)throws RemoteException;}
public class MyClient {
public static void main(String args[]){

if (System.getSecurityManager() == null) {
try{
            System.setProperty("java.security.policy",MyClient.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()+"//registerit.policy"); }
			catch(Exception e){}
			
            System.setSecurityManager(new SecurityManager());
        }
try{
Registry registry = LocateRegistry.getRegistry("192.168.1.4");//i.p of Server
Adder stub=(Adder)registry.lookup("Server");//name given in server
System.out.println(stub.add(34,4));

}catch(Exception e){System.out.println(e);}
}

}
