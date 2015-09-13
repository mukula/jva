import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;

class ClientInput extends Thread 
{
private static byte active=4;
private BufferedWriter bc;
private BufferedReader bb;
private Socket s;
private static boolean status=true;
private boolean printExceptionStackTrace=false;
ClientInput(Socket s)
{
//System.out.println("recv started");
this.s=s;
start();
}
public byte status (){
return active;
}
public void run() 
{
String srx="";
try{
bb=new BufferedReader(new InputStreamReader(s.getInputStream()));
while(!srx.equals("exit_from_the_command_session")){
srx=bb.readLine();
if(srx.equals("Error_recieved_abort"))
{
System.out.println("\n Error: Command not allowed");
break;
}
else if(!srx.equals("exit_from_the_command_session"))
System.out.println(srx);
active=5;
}
s.close();
}
catch(Exception e){
if(printExceptionStackTrace)
e.printStackTrace();
}
finally{
active=3;
if(!srx.equals("Error_recieved_abort") && status){
status=false;
System.out.println("\n\n-------- Command Executed --------");}
else
status=false;
}
}
}
