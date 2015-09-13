/*
in following program,ServerSocket object is created using constructor which takes three arguments,by which we can specify backlog and IP,allowing us to
explicitly assign IP to Socket.backlog refers to no. of clients waiting to connect to server at a time.in following,backlog is 10.

the local port is the port at which server sits and Port given by s.getPort () is Port to which it is connected i.e port of Client's socket ,which in 
this case is 10.
*/
import java.net.*;
public class Srvr2
{
public static void main(String k[])throws Exception
{
InetAddress n=InetAddress.getByName("192.168.1.3");//creating object of InetAddress
InetSocketAddress nn=new InetSocketAddress(n,10);
ServerSocket ss=new ServerSocket();//passing port no , backlog and InetAddress.
ss.bind(nn);
Socket s=ss.accept();//Waiting for client to connect
System.out.println("Client connected");
System.out.println("Local Port: "+s.getLocalPort()+" Client Port: "+s.getPort()+" Local IP: "+s.getLocalAddress()+" Server IP: "+s.getInetAddress());

}
}