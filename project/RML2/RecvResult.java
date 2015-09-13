import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;

class RecvResult extends Thread 
{
private static byte active=4;
private BufferedWriter bc;
private BufferedReader bb;
private Socket s;
RecvResult(Socket s)
{
//System.out.println("recv started");
this.s=s;
start();
}
public byte status (){
return active;
}
public void run() 
{try{
bb=new BufferedReader(new InputStreamReader(s.getInputStream()));
String srx="";
while(!srx.equals("exit_from_the_command_session")){
srx=bb.readLine();
if(!srx.equals("exit_from_the_command_session"))
System.out.println(srx);
active=5;
}
s.close();
System.out.println("\n\n-------- Command Executed --------");
}
catch(Exception e){e.printStackTrace();
System.out.println("\n\n------- Command Failed --------");
}
finally{
active=3;
}
}
}
