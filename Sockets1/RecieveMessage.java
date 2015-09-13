import java.io.*;
import java.net.*;
public class RecieveMessage extends Thread
{
BufferedReader bb;//to read from socket
Socket s;//reference to socket created by server 
String RecieverName;
RecieveMessage(Socket s,String RecieverName)
{
this.RecieverName=RecieverName;
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
System.out.println(RecieverName+": "+srx);//display message
}
System.out.println(RecieverName+" exited");
s.close();//close connection
bb.close();
}
catch(Exception e){e.printStackTrace();}
}
}