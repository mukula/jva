import java.io.*;
import java.net.*;
public class SendMessage extends Thread
{
BufferedWriter bc;//to write outgoing message 
BufferedReader rb;//to read from keyboard
String dx;//to store line from keyboard
Socket s;//reference to client socket
SendMessage(Socket s)
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
bc.close();
rb.close();
}
catch(Exception e){e.printStackTrace();}
}
}