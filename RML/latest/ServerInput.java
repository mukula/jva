import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;

class ServerInput extends Thread 
{
private BufferedReader bb;
private BufferedWriter cw;
private Socket s;
private Process process;
public ServerInput(Socket s,Process process)
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
while(srx!=null){
srx=bb.readLine();
if(srx==null)
break;
cw.write(srx+"\n");
cw.flush();
}

s.close();
}
catch(IOException e){System.out.println("Client "+s.getInetAddress().getHostAddress()+" exited");}

}
}
