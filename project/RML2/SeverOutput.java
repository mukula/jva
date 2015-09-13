import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;
class ServerOutput extends Thread
{
private Process process;
private BufferedWriter bc;
private BufferedReader cx;
private BufferedReader rb;
private String dx=null;
private Socket s;
private boolean printExceptionStackTrace=false;
ServerOutput(Socket s,Process process)
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
while(dx!=null){ 
dx=cx.readLine();
if(dx==null)
break;
bc.write(dx+"\n");
bc.flush();
}
bc.write("exit_from_the_command_session\n\n");
bc.flush();
s.close();
}
catch(Exception e){if(printExceptionStackTrace) e.printStackTrace();}
}
}
