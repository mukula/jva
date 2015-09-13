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
private boolean printExceptionStackTrace=true;
public List cmdList=new ArrayList();
private boolean allowListCmd=false;
private Socket s;
SubServer(Socket s,String path,List l)
{
this.s=s;
this.path=path;
cmdList=l;
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
		String sName=s.getInetAddress().getHostName()+"@"+s.getInetAddress().getHostAddress();
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
		}else{
	     new ServerInput(s,process);
         new ServerOutput(s,process);}
		}
		else
		{
		if(checkCmd(cmd)){
		pw.println("Error_recieved_abort");
		pw.flush();
		}else{
		 new ServerInput(s,process);
         new ServerOutput(s,process);}}
		}
		
		}catch(Exception e){
		if(printExceptionStackTrace)
		e.printStackTrace();}

}
}