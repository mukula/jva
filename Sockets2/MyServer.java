/*********************************************
 *      ______________________________       *
 *     //                            \\      * 
 *    //         G.SAIKIRAN           \\     * 
 *   //________________________________\\    *
 *                                           *
 *********************************************
*/
import java.net.*;
import java.io.*;
class Peer extends Thread
{
 BufferedReader bb;
 BufferedWriter bc;
 BufferedReader rb;
 BufferedWriter br;
Socket s1;
Socket s2;
String read;
String write;
Peer(Socket s1,Socket s2){
this.s1=s1;
this.s2=s2;
//System.out.println("Socket: "+s.getPort());
start();
}

public void run()
{try{
//System.out.println("in peer run");
bb=new BufferedReader(new InputStreamReader(s1.getInputStream()));
bc=new BufferedWriter(new OutputStreamWriter(s1.getOutputStream()));
rb=new BufferedReader(new InputStreamReader(s2.getInputStream()));
br=new BufferedWriter(new OutputStreamWriter(s2.getOutputStream()));
new Recv(bb,br,s1);

String dx=" ";
do{   
dx=rb.readLine();
bc.write(dx+"\n");
bc.flush();
}while(!dx.equals("stop"));
System.out.println("exiting");
//s1.close();
}
catch(Exception e){}
}
}

class Recv extends Thread 
{
BufferedReader bb;
Socket s1;
BufferedWriter br;

Recv(BufferedReader bb,BufferedWriter br,Socket s1)
{
this.bb=bb;
this.s1=s1;
this.br=br;
start();
}
public void run() 
{
    try{
// System.out.println("in recv run");
String srx;
do{    
srx=bb.readLine();
//BufferedWriter ds=new BufferedWriter(new OutputStreamWriter(s2.getOutputStream()));
br.write(srx+"\n");
br.flush();
}
while(!srx.equals("stop"));
System.out.println("Client exited");
//s1.close();
}
catch(Exception e){}
}
}
public class MyServer
{
    
public static void main(String str[]) throws Exception
{
ServerSocket ss=new ServerSocket(10);
System.out.println("Waiting for Client to connect...");
Socket s1=ss.accept();
System.out.println("connected 1");
Socket s2=ss.accept();
System.out.println("connected 2");
new Peer(s1,s2);
new Peer(s2,s1);

}
}

