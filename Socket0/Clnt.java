/*
in the following program,the client socket is explicitly binded to port no. 10. after that,
we connect the client socket to server by connect();
*/
import java.net.*;
public class Clnt
{
public static void main(String k[])throws Exception
{
byte b[]={127,2,2,2};
InetAddress n=InetAddress.getByAddress(b);
SocketAddress g=new InetSocketAddress(n,10);//passing in desired port number and InetAddress for IP.
SocketAddress gg=new InetSocketAddress(n,100);
Socket s=new Socket();
s.bind(g);//explicitly binding to socket no.10 
s.connect(gg);//connecting to server socket.
System.out.println("Local Port: "+s.getLocalPort()+" Server Port: "+s.getPort()+" Local IP: "+s.getLocalAddress()+" Server IP: "+s.getInetAddress());

}
}