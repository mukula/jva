/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ftp;
import java.io.*;
import java.net.*;
public class Send {
    public static void main(String ds[])throws Exception
    {
    FileInputStream fr=new FileInputStream("F:\\misc\\Hello.java");
    BufferedInputStream br=new BufferedInputStream(fr);
    Socket s=new Socket("192.168.1.5",2048);
    System.out.println("sending");
    DataOutputStream dout=new DataOutputStream(s.getOutputStream());
    int f;
    
   while((f=fr.read())!=-1)
    {
dout.write(f);
 }
   br.close();
   fr.close();
   dout.close();
   
    s.close();
    System.out.println("sent"); 
    }
}
