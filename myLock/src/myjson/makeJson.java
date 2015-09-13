package myjson;
import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.RandomAccessFile;
import java.net.*;
import java.io.*;
import java.util.Random;
public class makeJson {
    private String date;
    private String fileName;    
    public makeJson(String date,String fileName)
    {
        this.fileName=fileName;
        this.date=date;
    }
    private int maxdy(int month)
    {
    
int daysInMonth;
if (month == 4 || month == 6 || month == 9 || month == 11)

daysInMonth = 30;

else 

if (month == 2) 

daysInMonth = 28;

else 

daysInMonth = 31;

return daysInMonth;
    }
public void jsonp()
 throws MalformedURLException, IOException {
  RandomAccessFile fout=null;
  InputStream stream = null;
  String[] ymd=date.split("-");
  int year = Integer.parseInt(ymd[0]);
  int month = Integer.parseInt(ymd[1]);
  int day = Integer.parseInt(ymd[2]);
  //System.out.println("jhkh "+year+"-"+month+"-"+day);
if(day>28)
{
if(day==maxdy(month))
{month++;
day=1;
}
else
    day++;
}
else
day++;

StringBuffer date2=new StringBuffer(date);
  Random r = new Random();
  int ds=r.nextInt(2000-600);
int i1 = r.nextInt(5000 - ds) + ds;
System.out.println("date "+year+"-"+month+"-"+day);
URL url=new URL("http://api.thingspeak.com/channels/28840/feed.json?key=PF87VU53HKRUJVE4&start="+date+"&end="+year+"-"+month+"-"+day+"&offset=6.00&result="+i1);
  HttpURLConnection connection;
 try {
 connection =(HttpURLConnection) url.openConnection();
 connection.connect();
 if (connection.getResponseCode() / 100 != 2) {
                System.out.println("error()");
            }
 int contentLength = connection.getContentLength();
            
stream = connection.getInputStream();			
 fout = new RandomAccessFile(fileName,"rw");
 int read=0;
 int d=0;
 int x=0,k=10,nm=0;
 byte buffer[]=new byte[1];
 //char cj;
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
//public static void main(String s[])
//{
//    try{
//    new makeJson("2015-03-27","F:\\myFile1.json").jsonp();
//    pjon p=new pjon("F:\\myFile1.json");
//    for(int i=0;i<p.siz();i++){
//    System.out.println(p.getf1(i));
//    //System.out.println(p.getf2(i));
//    
//    }
//    
//    }
//    catch(Exception e)
//    {
//    e.printStackTrace();
//    }
//}
}

