import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;

class ServerInput extends Thread 
{
private InetAddress id=null;
private BufferedReader bb;
private BufferedWriter cw;
private Socket s;
private Process process;
public ServerInput(Socket s,Process process)
{
this.process=process;
this.s=s;
id=s.getInetAddress();
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
catch(IOException e){
Server.clientSet.remove(id);
Server.count=Server.clientSet.size();
String hName=s.getInetAddress().getHostName();
if(hName.contains("."))
hName=hName.substring(0,hName.indexOf("."));
System.out.println("Client "+hName+" | "+s.getInetAddress().getHostAddress()+" exited"+Server.count);}

}
}
