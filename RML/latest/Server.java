import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;
public class Server {
private BufferedReader bb;
private Process process;
private PrintWriter pw;
private String cmd;
private int portNo;
private String path;
private boolean printExceptionStackTrace=true;
public List cmdList=new ArrayList();
private static final String DEFAULT_FOLDER="C:\\java\\1";
private static final int DEFAULT_PORT=2828;
private Set clientList;
private boolean allowListCmd=false;
private int backLog=2;
public Server()
{
this.portNo=DEFAULT_PORT;
this.path=DEFAULT_FOLDER;
File fg=new File(DEFAULT_FOLDER);
clientList=new HashSet();
if(!fg.exists())
fg.mkdir();

//begin();
}
public Server(List l)
{
this();
cmdList=l;
}
public Server(String path,int portNo)
{
this.portNo=portNo;
this.path=path;
clientList=new HashSet();
File fg=new File(path);
if(!fg.exists())
fg.mkdir();
//begin();
}
public Server(String path)
{
this(path,DEFAULT_PORT);
}
public Server(int portNo)
{
this(DEFAULT_FOLDER,portNo);
}
public Server(String path,int portNo,List l)
{
this(path,portNo);
cmdList=l;
//begin();
}
public void setCmdList(List list)
{
cmdList=list;
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

public void begin() 
{

try{
		File fg;
		ProcessBuilder probuilder = new ProcessBuilder();
		
		ServerSocket ss=new ServerSocket(portNo);
        
		System.out.println("Waiting for Client to connect...");
        while(true){
		Socket s=ss.accept();//waiting for a client to connect
		String clientName=s.getInetAddress().getHostAddress();
        clientList.add(clientName);
		System.out.println("\n----- Client connected from IP "+clientName+" -----");
        pw=new PrintWriter(s.getOutputStream()); 
		bb=new BufferedReader(new InputStreamReader(s.getInputStream()));
        fg=new File(path+"\\"+clientName);
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
		System.out.println("Recieving file from "+clientName);
		new RecvFile(s,path);
		}
		else if(cmd.equals("exit_client"))
		if(clientList.contains(clientName))
		clientList.remove(clientName);
		else{
		System.out.println("Recieved Command \""+cmd+"\" from Client: "+clientName);
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
         new ServerOutput(s,process);}
		}
		 }
		}}catch(Exception e){
		if(printExceptionStackTrace)
		e.printStackTrace();}
		
}

}