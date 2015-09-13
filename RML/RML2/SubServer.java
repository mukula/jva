import java.io.*;
import java.net.*;
import java.util.*;
public class SubServer extends Thread
{
private BufferedReader bb;
private Process process;
private PrintWriter pw;
private String cmd;
private String path;
private InetAddress id=null;
private boolean printExceptionStackTrace=true;
public List cmdList=new ArrayList();
private boolean allowListCmd=false;
private Socket s;
SubServer(Socket s,String path,List l)
{
this.s=s;
this.path=path;
cmdList=l;
id=s.getInetAddress();
start();
}
private boolean checkCmd(String cmd)
{
if(cmdList.isEmpty())
return false;
for(Object obj:cmdList)
{
String string = (String)obj;
if(cmd.equals(string))
return true;
else if(cmd.toLowerCase().startsWith(string+" "))
return true;
}
return false;
}
public void run()
{
File fg;
ProcessBuilder probuilder = new ProcessBuilder();
try{
pw=new PrintWriter(s.getOutputStream()); 
		bb=new BufferedReader(new InputStreamReader(s.getInputStream()));
		String hName=s.getInetAddress().getHostName();
		if(hName.contains("."))
		hName=hName.substring(0,hName.indexOf("."));
		String hIp=s.getInetAddress().getHostAddress();
		String sName=hName+"@"+hIp;
        fg=new File(path+"\\"+sName);
		if(fg.exists())
		{
		fg.delete();
		fg.mkdir();
		}
		else
		fg.mkdir();
		probuilder.directory(fg);
		cmd=bb.readLine();
		if(cmd.equals("file"))
		{
		System.out.println("Recieving file from "+sName);
		new RecvFile(s,path);
		}else{
		System.out.println("Recieved Command \""+cmd+"\" from Client: "+sName);
		String[] command = {"CMD", "/C",cmd};
		 probuilder.command(command);
		 process = probuilder.start();
		if(allowListCmd){
		if(!checkCmd(cmd)){
		pw.println("Error_recieved_abort");
		pw.flush();
		System.out.println("Client "+hName+" | "+hIp+" exited"+Server.count);
		Server.clientSet.remove(id);
        Server.count=Server.clientSet.size();
		}else{
	     new ServerInput(s,process);
         new ServerOutput(s,process);}
		}
		else
		{
		if(checkCmd(cmd)){
		pw.println("Error_recieved_abort");
		pw.flush();
		System.out.println("Client "+hName+" | "+hIp+" exited"+Server.count);
		Server.clientSet.remove(id);
        Server.count=Server.clientSet.size();
		}else{
		 new ServerInput(s,process);
         new ServerOutput(s,process);}}
		}
		
		}catch(Exception e){
		if(printExceptionStackTrace)
		e.printStackTrace();}

}
}