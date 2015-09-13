/*
Sockets are endpoints of two way connection.If you want to connect to someone,you need to be connected with their socket.

*/
import java.net.*;
import java.io.*;
public class MyServer
{
public static void main(String str[]) throws Exception
{
ServerSocket ss=new ServerSocket(10);//start server in localhost and port #10
System.out.println("Waiting for Client to connect...");
Socket s=ss.accept();//now the accept method waits for client to connect,when client tries to connect,this method returns a socket which gets connected to the client.
System.out.println("client connected | Client's local Port: "+s.getPort());//to show on which port clientsocket exists.
new RecieveMessage(s,"Client");//thread for recieving messages,as messages are coming from client,we pass String "Client"
new SendMessage(s);//thread for sending messages
}
}