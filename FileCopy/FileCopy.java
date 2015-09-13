/*
in this example,data is being copied into newly created file destination.txt
we create a array of byte:buffer. statement fin.read(buffer) reads data from source and places in buffer.it only reads 8192
bytes in this statement as size of buffer is 8192.variable y checks if no. of bytes in source.txt is more or less than 8192,
if more,than value of y is 1 or more and while loop runs.if data > 8192 bytes,while loop runs atleast once.in statement
flee.write(buffer,0,BUFFER_SIZE),data from buffer from position 0 to 8192 in buffer is written into desination.txt. 
*/
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.*;
public class FileCopy {
public static final int BUFFER_SIZE=8192;
private RandomAccessFile flee=null;
private FileInputStream fin = null;
private int d=0;
private int y=0;
private byte buffer[];
public void con(String source,String destination)
{
try{
flee=new RandomAccessFile(destination,"rw");//output file
fin=new FileInputStream(source);//input
byte buffer[]=new byte[BUFFER_SIZE];
d=fin.available();//to store size of sai.txt in n
y=(d/BUFFER_SIZE);
while(y>0){
fin.read(buffer);//writes the content of sai.txt into array buffer
flee.write(buffer,0,BUFFER_SIZE);//writes the contents of buffer into kiran.txt
y--;
}
fin.read(buffer);//writes the content of sai.txt into array buffer
flee.write(buffer,0,d%BUFFER_SIZE);//writes the contents of buffer into kiran.txt
fin.close();
flee.close();
}
catch(Exception e)
{
System.out.println(e);}
}
public static void main(String s[])
{
FileCopy fx=new FileCopy();
fx.con("source.txt","C:\\java\\FileCopy\\destination.txt");
System.out.println("Done");
}
}
