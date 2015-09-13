import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;
class Send extends Thread
{
Process process;
BufferedWriter bc;
BufferedReader rb;
String dx="";
Socket s;
Send(Socket s,Process process)
{
this.process=process;
this.s=s;
start();
}
public void run()
{
System.out.println(process.isAlive());
try{
rb=new BufferedReader(new InputStreamReader(process.getInputStream()));
bc=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
while(dx!=null){ 
dx=rb.readLine();
System.out.println("sending "+dx);
if(dx==null)
break;
bc.write(dx+"\n");
bc.flush();
}
bc.write("exit\n");
bc.flush();
s.close();
}
catch(Exception e){e.printStackTrace();}
}
}
class Recv extends Thread 
{
BufferedReader bb;
private BufferedWriter cw;
Socket s;
Process process;
Recv(Socket s,Process process)
{
this.process=process;
this.s=s;

start();
}
public void run() 
{try{
cw=new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
bb=new BufferedReader(new InputStreamReader(s.getInputStream()));
String srx="";
//!(srx=bb.readLine()).equals(null)){  
do{
srx=bb.readLine();
System.out.println("recv "+srx);
System.out.println(srx);
cw.write(srx+"\n");
cw.flush();
}while(process.isAlive());
System.out.println("Client exited");
s.close();
}
catch(Exception e){e.printStackTrace();}
}
}
public class Test {
String cmd;
Test(String s)
{
cmd=s;
run();
}
private void run() 
{try{
String[] command = {"CMD", "/C",cmd};
        ProcessBuilder probuilder = new ProcessBuilder();
        probuilder.command(command);
        probuilder.directory(new File("c:\\java\\2"));
        Process process = probuilder.start();
        ServerSocket ss=new ServerSocket(10);
        System.out.println("Waiting for Client to connect...");
        Socket s=ss.accept();
        System.out.println("client connected ");
        //new Recv(s,process);
        new Send(s,process);
		}catch(Exception e){e.printStackTrace();}
}

    public static void main(String[] args) throws Exception {
      
        new Test("java Hello");

     
         
             }
    
}