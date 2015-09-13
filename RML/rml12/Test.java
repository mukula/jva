import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;
class Send extends Thread
{
Process process;
BufferedWriter bc;
BufferedReader cx;
BufferedReader rb;
String dx=null;
Socket s;
Send(Socket s,Process process)
{
this.process=process;
this.s=s;
start();
}
public void run()
{
try{
cx=new BufferedReader(new InputStreamReader(process.getErrorStream()));
rb=new BufferedReader(new InputStreamReader(process.getInputStream()));
bc=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
dx="";
while(dx!=null){ 
dx=rb.readLine();
if(dx==null)
break;
bc.write(dx+"\n");
bc.flush();
}
dx="";
//dx=cx.readLine();
//if(dx!=null){
//bc.write(dx+"\n");
//bc.flush();
while(dx!=null){ 
dx=cx.readLine();
if(dx==null)
break;
bc.write(dx+"\n");
bc.flush();
}
bc.write("exit\n\n");
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
while(srx!=null){
srx=bb.readLine();
if(srx==null)
break;
System.out.println("recv "+srx);
cw.write(srx+"\n");
cw.flush();
}
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

        Process process;
		ProcessBuilder probuilder = new ProcessBuilder();
        probuilder.directory(new File("c:\\java\\1"));
		ServerSocket ss=new ServerSocket(10);
        System.out.println("Waiting for Client to connect...");
        while(true){
		Socket s=ss.accept();
        System.out.println("client connected ");
        BufferedReader bb=new BufferedReader(new InputStreamReader(s.getInputStream()));
		cmd=bb.readLine();
		String[] command = {"CMD", "/C",cmd};
		probuilder.command(command);
		process = probuilder.start();
		new Recv(s,process);
        new Send(s,process);
		}}catch(Exception e){e.printStackTrace();}
		
}

    public static void main(String[] args) throws Exception {
      
        new Test("java Hello");

     
         
             }
    
}