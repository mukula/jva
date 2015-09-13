package FileDownloadTest;
import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.RandomAccessFile;
import java.net.*;
import java.io.*;
public class makeJson {
    private String date;
    private String fileName;
    
    public makeJson(String date,String fileName)
    {
        
        this.fileName=fileName;
        this.date=date;
    }
public void jsonp()
 throws MalformedURLException, IOException {
    File file = new File(fileName);
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
  RandomAccessFile fout=null;
  InputStream stream = null;
  StringBuffer date2=new StringBuffer(date);
  char cf=(char)(date.charAt(date.lastIndexOf("-")+2)+1);
  
  date2.setCharAt((date.lastIndexOf("-")+2),cf);
  System.out.println(date2);
  URL url=new URL("http://api.thingspeak.com/channels/28840/feed.json?key=PF87VU53HKRUJVE4&start="+date+"&end="+date2);
  HttpURLConnection connection;
 try {
 connection =(HttpURLConnection) url.openConnection();
 connection.connect();
 if (connection.getResponseCode() / 100 != 2) {
                System.out.println("error()");
            }
 int contentLength = connection.getContentLength();
            if (contentLength < 1) {
                System.out.println("error(1)");
            }
 stream = connection.getInputStream();			
 fout = new RandomAccessFile(fileName,"rw");
 int read=0;
 int d=0;
 int x=0,k=10,nm=0;
 byte buffer[]=new byte[1];
 
 while(true)
 {
 read=stream.read(buffer);
 if(read==-1)
 
 
 break;
 if(buffer[0]==125 && nm==0)
 {buffer[0]=32;
 nm++;
 }
 if(x>0 && x<5 )
 fout.write(buffer,0,read);
 else{
 while(k>0){
 read=stream.read(buffer);
 fout.write(" ".getBytes());
 k--;}
 x=4;
 }
 
 d+=read;
 }
 } finally {
 if (stream != null)
 stream.close();
 if (fout != null)
 fout.close();
 }
 }
public static void main(String s[])
{
    try{
    new makeJson("2015-03-25","C:\\java\\json - Copy\\myFile1.json").jsonp();
    pjon p=new pjon("C:\\java\\json - Copy\\myFile1.json");
    for(int i=0;i<p.siz();i++){
    System.out.println(p.getf1(i));
    System.out.println(p.getf2(i));
    }
    }
    catch(Exception e)
    {
    e.printStackTrace();
    }
}
}

