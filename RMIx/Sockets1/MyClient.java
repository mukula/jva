import java.net.*;
import java.io.*;
import java.rmi.*;
import java.rmi.registry.*;
class Rmx extends Thread
{
Rmx()
{
start();
}
public void run()
{
if (System.getSecurityManager() == null) {
            System.setProperty("java.security.policy","file:\\F:\\java\\RMIx\\Sockets1\\registerit.policy"); 
            System.setSecurityManager(new SecurityManager());
        }
try{
Registry registry = LocateRegistry.getRegistry("Localhost");//i.p of Server
Adder stub=(Adder)registry.lookup("Server");//name given in server
System.out.println(stub.add(34,4));

}catch(Exception e){System.out.println(e);}
}


}
class Send1 extends Thread //Thread to send Messages
{
BufferedWriter bc;//to write outgoing message 
BufferedReader rb;//to read from keyboard
String dx;//to store line from keyboard
Socket s;//reference to client socket
Send1(Socket s)
{

this.s=s;
start();
}
public void run()
{
try{
bc=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
rb=new BufferedReader(new InputStreamReader(System.in));
do{   
dx=rb.readLine();//read from buffer connected to keyboard
bc.write(dx+"\n");//write message to buffer connected to output stream of socket
//we need to put \n as server will read till it recieves \n i.e we press enter.but when we press enter, \n is not passes and flush removes it.so we 
//need to put \n explicitily.to avoid this use DataOutputstream or printwriter.by using printwriter in autoflush mode,we can skip writing below code.
bc.flush();//to send complete data from buffer to socket and empty it..it's important
}while(!dx.equals("stop"));//loop till stop is typed
s.close();//close socket connection

}
catch(Exception e){}
}
}
class Recv1 extends Thread //thread to recieve incoming messages.
{
BufferedReader bb;//to read from socket
Socket s;//reference to socket created by server 
Recv1(Socket s)
{
this.s=s;
start();
}
public void run() 
{try{
bb=new BufferedReader(new InputStreamReader(s.getInputStream()));//input from server's socket
String srx;
while(true){    
srx=bb.readLine();//read from buffer connected to input stream of server's socket
if(srx.equals("stop"))
break;
System.out.println("Server: "+srx);//display message
}
System.out.println("Server exited");
s.close();//close connection
}
catch(Exception e){}
}
}
public class MyClient
{
    
public static void main(String str[])throws Exception
{
Socket s=new Socket("Localhost",10);//connect to server listening at port 10 in locahost
//A socket is created at a port # assigned by system which is displayed below
System.out.println("connected through localPost: "+s.getLocalPort());
new Rmx();
new Recv1(s);
new Send1(s);

}
}