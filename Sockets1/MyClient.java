import java.net.*;
import java.io.*;

public class MyClient
{
    
public static void main(String str[])throws Exception
{
Socket s=new Socket("Localhost",10);//connect to server listening at port 10 in locahost
//A socket is created at a port # assigned by system which is displayed below
System.out.println("connected through localPost: "+s.getLocalPort());
new RecieveMessage(s,"Server");
new SendMessage(s);

}
}