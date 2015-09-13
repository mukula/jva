/*
MyServer provides methods for remote invokation for clients.MyServer should implements Adder so that it can
implement abstract methods defined in Adder.MyServer also implements Serializable as the objects of
MyServer should be able to travel on the network.in main,first we install security manager.then we 
create RMI Registery at port 1099.we provide its reference to registry variable.then we create stub
and pass it in registry.rebind().we also passs naame "Server" in it,by which client will find it.
*/
import java.rmi.*;
import java.rmi.registry.*;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
public class MyServer implements Adder,Serializable{
public MyServer(){
super();
}
public int add(int x,int y) throws RemoteException{
return x+y;
}
public static void main(String args[]){


 if (System.getSecurityManager() == null) 
    {try{
   System.setProperty("java.security.policy",MyServer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()+"//registerit.policy"); 
   System.setSecurityManager(new SecurityManager());
		}catch(Exception e){}	              }
try{
LocateRegistry.createRegistry(1099);//works with only 1099
Registry registry = LocateRegistry.getRegistry();
MyServer sr=new MyServer();

Adder stub=(Adder)UnicastRemoteObject.exportObject(sr, 0); 
 registry.rebind("Server", stub);
}catch(Exception e){System.out.println(e);}
}

}
