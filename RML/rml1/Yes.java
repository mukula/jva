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
while(r.active>3)
{
Thread.sleep(200);
if(r.active<4)
break;
System.out.println("type: ");
if(r.active>4)
dx=rb.readLine();
bc.write(dx+"\n");
bc.flush();
}
s.close();
}
catch(Exception e){e.printStackTrace();}

}
}
class Recv1 extends Thread 
{
public static int active=4;
BufferedWriter bc;

BufferedReader bb;
Socket s;
Recv1(Socket s)
{
System.out.println("recv started");
this.s=s;
start();
}

public void run() 
{try{
bb=new BufferedReader(new InputStreamReader(s.getInputStream()));
String srx="";
while(!srx.equals("exit")){
System.out.println("r$");
active=5;
srx=bb.readLine();
System.out.println(srx);
}
s.close();
System.out.println("Client exited");
}
catch(Exception e){e.printStackTrace();}
finally{
active=3;
}
}
}
public class Yes
{
    
public static void main(String str[])throws Exception
{
Socket s=new Socket("Localhost",10);//connect to server listening at port 10 in locahost
//A socket is created at a port # assigned by system which is displayed below
System.out.println("connected through localPost: "+s.getLocalPort());
Recv1 se=new Recv1(s);
Thread.sleep(10);
new Send1(s,se);

}
}