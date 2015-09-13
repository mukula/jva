import java.net.*;
import java.io.*;

class Reczip extends Thread
{
private Socket socket;
Reczip(Socket s)
{
socket=s;
start();
}
public void run()
{try{
File file=new File("E:\\t1.zip");
FileOutputStream fout=new FileOutputStream(file);
BufferedInputStream bg=new BufferedInputStream(socket.getInputStream());
byte bt[]=new byte[100000];
bg.read(bt);
fout.write(bt);
bg.close();
fout.close();
socket.close();
new UnZip().unZipIt("E:\\t1.zip","E:\\t1");
file.delete();
}
catch(Exception e){e.printStackTrace();}
}
}
public class Serv
{
public static void main(String hj[]) throws Exception
{
ServerSocket ss=new ServerSocket(20);

while(true){Socket s=ss.accept();
new Reczip(s);}
}
}