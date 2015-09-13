import java.net.*;
import java.io.*;
class RecvFile extends Thread
{

private String path;
private Socket socket;
private boolean printExceptionStackTrace=false;
RecvFile(Socket s,String path)
{
this.path=path;
socket=s;
start();
}
public void setPath(String path)
{
this.path=path;
}

public void run()
{try{
File file=new File(path+"\\"+socket.getInetAddress().getHostAddress()+".zip");
FileOutputStream fout=new FileOutputStream(file);
InputStream bg=(socket.getInputStream());

byte bt[]=new byte[10];
int c;
while((c=bg.read(bt))>0)
{
fout.write(bt,0,c);
}

bg.close();
fout.close();
socket.close();
new FileUnZip().unZip(path+"\\"+socket.getInetAddress().getHostAddress()+".zip",path+"\\"+socket.getInetAddress().getHostAddress());
file.delete();
}
catch(Exception e){if(printExceptionStackTrace) e.printStackTrace();}
}
}
