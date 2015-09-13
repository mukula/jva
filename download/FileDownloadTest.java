import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.RandomAccessFile;
import java.net.*;
import java.io.*;
public class FileDownloadTest {
public void saveFileFromUrlWithJavaIO(String fileName)
 throws MalformedURLException, IOException {
  RandomAccessFile fout=null;
  InputStream stream = null;
  URL url=new URL("http://api.thingspeak.com/channels/27487/feed.json?key=RO7J2JGOZ99RSIVC&results=35");
  HttpURLConnection connection;
 try {
 connection =(HttpURLConnection) url.openConnection();
 connection.setRequestProperty("Range","bytes=" +0+ "-");
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
 byte buffer[]=new byte[1];
 while(true)
 {
 read=stream.read(buffer);
 if(read==-1)
 break;
 System.out.println("read="+read+"d="+d);
 fout.write(buffer,0,read);
 d+=read;
 }
 } finally {
 if (stream != null)
 stream.close();
 if (fout != null)
 fout.close();
 }
 }

public static void main(String[] args) {

 // Make sure that this directory exists
 FileDownloadTest ff=new FileDownloadTest();
 String dirName = "F:\\core\\java\\java\\download";

 try {

System.out.println("Downloading");

 ff.saveFileFromUrlWithJavaIO(
 dirName + "\\callbyvalue2.txt");

 System.out.println("Downloaded");

 } catch (MalformedURLException e) {
 e.printStackTrace();
 } catch (IOException e) {
 e.printStackTrace();
 }

}

}