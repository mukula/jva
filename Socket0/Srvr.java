/*
in following program,ServerSocket object is created using constructor which takes three arguments,by which we can specify backlog and IP,allowing us to
explicitly assign IP to Socket.backlog refers to no. of clients waiting to connect to server at a time.in following,backlog is 10.

the local port is the port at which server sits and Port given by s.getPort () is Port to which it is connected i.e port of Client's socket ,which in 
this case is 10.
*/
import java.net.*;
public class Srvr
{
public static void main(String k[])throws Exception
{
byte ip[]={127,2,2,2};//ip address
InetAddress n=InetAddress.getByAddress(ip);//creating object of InetAddress
ServerSocket ss=new ServerSocket(100,10,n);//passing port no , backlog and InetAddress.
Socket s=ss.accept();//Waiting for client to connect
System.out.println("Client connected");
System.out.println("Local Port: "+s.getLocalPort()+" Client Port: "+s.getPort()+" Local IP: "+s.getLocalAddress()+" Server IP: "+s.getInetAddress());

}
}