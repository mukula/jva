import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;
public class Server {
private int portNo;
private String path;
private boolean printExceptionStackTrace=true;
public List cmdList;
private static final String DEFAULT_FOLDER="C:\\java\\1";
private static final int DEFAULT_PORT=2828;
public static int count=0;
public static Set clientSet=new HashSet();
public Server()
{
this.portNo=DEFAULT_PORT;
this.path=DEFAULT_FOLDER;
File fg=new File(DEFAULT_FOLDER);
if(!fg.exists())
fg.mkdir();
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
File fg=new File(path);
if(!fg.exists())
fg.mkdir();
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
}
public void setCmdList(List list)
{
cmdList=list;
}
public void begin() 
{
try{
		
		ServerSocket ss=new ServerSocket(portNo);
        System.out.println("Waiting for Client to connect...");
        while(true){
		Socket s=ss.accept();//waiting for a client to connect
		String hName=s.getInetAddress().getHostName();
		if(hName.contains("."))
		hName=hName.substring(0,hName.indexOf("."));
		String hIp=s.getInetAddress().getHostAddress();
		clientSet.add(s.getInetAddress());
		Server.count=clientSet.size();
		System.out.println("\n----- Client connected from IP "+hIp+" Name: "+hName+" ----- "+clientSet.size());
        new SubServer(s,path,cmdList);
		}
		}catch(Exception e){
		if(printExceptionStackTrace)
		e.printStackTrace();}
		
}

}