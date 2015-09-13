import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;

class Send1 extends Thread
{
BufferedWriter bc;
BufferedReader rb;
String dx;
Socket s;
Recv1 r;
Send1(Socket s,Recv1 r)
{
this.s=s;
this.r=r;
start();
}
public void run()
{
try{
bc=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
rb=new BufferedReader(new InputStreamReader(System.in));

while(r.isAlive())
{
dx=rb.readLine();
System.out.println(dx);
bc.write(dx+"\n");
bc.flush();
}
s.close();
}
catch(Exception e){System.out.println("sasassaasas");}
}
}
class Recv1 extends Thread 
{
BufferedReader bb;
Socket s;
Recv1(Socket s)
{
this.s=s;
start();
}
public void run() 
{try{
bb=new BufferedReader(new InputStreamReader(s.getInputStream()));
String srx="";
while(!srx.equals("exit")){//!(srx=bb.readLine()).equals(null) ){
srx=bb.readLine();
System.out.println("recv "+srx);
}
System.out.println("Client exited");
s.close();
}
catch(Exception e){e.printStackTrace();}
}
}
public class Yes
{
    
public static void main(String str[])throws Exception
{
Socket s=new Socket("Localhost",10);//connect to server listening at port 10 in locahost
//A socket is created at a port # assigned by system which is displayed below
System.out.println("connected through localPost: "+s.getLocalPort());
new Recv1(s);
//new Send1(s,new Recv1(s));

}
}