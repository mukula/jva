import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;

class Send1 extends Thread
{
BufferedWriter bc;
BufferedReader rb;
String dx;
String sds;

Socket s;
Recv1 r;
Send1(Socket s,Recv1 r,String sds)
{
this.s=s;
this.r=r;
this.sds=sds;
start();
}
public void run()
{
try{
bc=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
rb=new BufferedReader(new InputStreamReader(System.in));
bc.write(sds+"\n");
bc.flush();
while(r.isActive())
{
Thread.sleep(300);
if(!r.isActive())
break;
System.out.println("type: ");
if(r.isActive())
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
private static boolean active=true;
BufferedWriter bc;

BufferedReader bb;
Socket s;
Recv1(Socket s)
{
//System.out.println("recv started");
this.s=s;
start();
}
public boolean isActive(){
return active;
}
public void run() 
{try{
bb=new BufferedReader(new InputStreamReader(s.getInputStream()));
String srx="";
while(!srx.equals("exit")){
//System.out.println("r$");
//active=5;
srx=bb.readLine();
System.out.println(srx);
}
s.close();
System.out.println("Client exited");
}
catch(Exception e){e.printStackTrace();}
finally{
active=false;
}
}
}
public class Yes
{
    
public static void main(String str[])throws Exception
{
Socket s=new Socket("Localhost",10);
System.out.println("connected through localPost: "+s.getLocalPort());
String d=System.console().readLine();
Recv1 se=new Recv1(s);
Thread.sleep(10);
new Send1(s,se,d);

}
}