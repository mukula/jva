import java.net.*;
import java.io.*;

class SendFile extends Thread
{
private String path;
private Socket socket=null;
private boolean printExceptionStackTrace=false;
SendFile(Socket socket,String path)
{
this.path=path;
this.socket=socket;
start();
}
public void run()
{
try{
new FileZip().zip(path,path+".zip");
File f=new File(path+".zip");
FileInputStream fin=new FileInputStream(f);
int c=0;
byte b[]=new byte[(int)f.length()];
fin.read(b);
socket.getOutputStream().write(b);
fin.close();
f.delete();
socket.close();
}catch(Exception e){if(printExceptionStackTrace) e.printStackTrace();}
}
}
