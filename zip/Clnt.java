import java.net.*;
import java.io.*;

class Sendzip extends Thread
{
private Socket socket=null;
Sendzip(Socket s)
{
socket=s;
start();
}
public void run()
{
try{
new FileZip().zip("F:\\t1","F:\\t1.zip");
File f=new File("F:\\t1.zip");
FileInputStream fin=new FileInputStream(f);
int c=0;
byte b[]=new byte[(int)f.length()];
fin.read(b);
socket.getOutputStream().write(b);
fin.close();
f.delete();
socket.close();
}catch(Exception e){e.printStackTrace();}
}
}
public class Clnt
{
public static void main(String hj[]) throws Exception
{

Socket s=new Socket("Localhost",20);
new Sendzip(s);
}
}