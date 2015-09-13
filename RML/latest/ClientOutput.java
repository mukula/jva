import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;

class ClientOutput extends Thread
{
private BufferedWriter bc;
private BufferedReader rb;
private String dx;
private String sds;
private Socket s;
private ClientInput r;
private boolean printExceptionStackTrace=false;
ClientOutput(Socket s,ClientInput r,String sds)
{
this.s=s;
this.r=r;
this.sds=sds;
start();
}
public void run()
{
try{
bc=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
rb=new BufferedReader(new InputStreamReader(System.in));
bc.write(sds+"\n");
bc.flush();
while(r.status()>3)
{
Thread.sleep(300);
if(r.status()<4)
break;
if(r.status()>4)
dx=rb.readLine();
bc.write(dx+"\n");
bc.flush();
}
s.close();
}
catch(Exception e){
if(printExceptionStackTrace)
e.printStackTrace();}

}
}
