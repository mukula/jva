import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;

public class Client
{
private String path;
private int portNo;
private String ip;
private boolean printExceptionStackTrace=true;
public Client(String ip,int portNo,String path)
{
this.path=path;
this.ip=ip;
this.portNo=portNo;
this.action();
}
private void action(){
try{
Socket s=new Socket(ip,portNo);
System.out.print("\n-------- Connected to "+ip+" --------\n\nEnter Command :  ");
String d=System.console().readLine();
if(d.equals("file")){
System.out.println("Sending files to Server form Local Dir: "+path);
BufferedWriter bc=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
bc.write(d+"\n");
bc.flush();
new SendFile(s,path);}
else{
new ClientInput(s);
new ClientOutput(s,new ClientInput(s),d);
}
}catch(Exception e){if(printExceptionStackTrace)e.printStackTrace();}
}
}