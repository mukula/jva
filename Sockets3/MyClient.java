import java.io.*;
import java.net.*;
public class MyClient
{
Socket s;
DataInputStream din;
DataOutputStream dout;
public MyClient()
{
try
{
s=new Socket("Localhost",10);
din=new DataInputStream(s.getInputStream());
dout=new DataOutputStream(s.getOutputStream());
clientChat();
}
catch(Exception e){}
}
public void clientChat() throws Exception
{
My m=new My(din);
Thread t1=new Thread(m);
t1.start();
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
String s1;
do{
s1=br.readLine();
dout.writeUTF(s1);
dout.flush();
}while(!s1.equals("stop"));
}
public static void main(String s[]) throws Exception
{
new MyClient();
}
}
class My implements Runnable
{
DataInputStream din;
My(DataInputStream din)
{
this.din=din;
}
public void run()
{
String s=" ";
do{
try{
s=din.readUTF();
System.out.println(s);
}catch(Exception e){}

}while(!s.equals("stops"));


}
}