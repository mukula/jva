/*
Run this program to download gson library to specified location.
*/
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
  URL url=new URL("https://github.com/google/gson/archive/master.zip");//download address
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
 int x=0,k=10,nm=0;
 byte buffer[]=new byte[1];
 char cj;
 while(true)
 {
 read=stream.read(buffer);
 if(read==-1)
 {
 fout.seek(fout.length()-1);
 fout.write(" ".getBytes());

 break;
 
 }

 System.out.print((char)buffer[0]);
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

public static void main(String[] args) {

 // Make sure that this directory exists
 FileDownloadTest ff=new FileDownloadTest();
 String dirName = "F:\\";//directory

 try {

System.out.println("Downloading");

 ff.saveFileFromUrlWithJavaIO(dirName + "\\gson.zip");//Name of the file to and directory to save downloaded data 

 System.out.println("Downloaded");

 } catch (MalformedURLException e) {
 e.printStackTrace();
 } catch (IOException e) {
 e.printStackTrace();
 }

}

}