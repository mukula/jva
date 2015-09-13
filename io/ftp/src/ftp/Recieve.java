/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ftp;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author sai
 */
public class Recieve {

    public static void main(String[] args) throws Exception {
        String[] command = {"CMD", "/C", "javac Hello.java"};
        String[] command1 = {"CMD", "/C", "java Hello"};
        FileOutputStream fout=new FileOutputStream("F:\\Hello.java");
        BufferedOutputStream bout=new BufferedOutputStream(fout);
        ServerSocket ss=new ServerSocket(2048);
        Socket s=ss.accept();
        System.out.println("connected");
        BufferedInputStream din= new BufferedInputStream(s.getInputStream());
        int c;
        while((c=din.read())!=-1)
        {
        fout.write(c);
        
        }
        din.close();
        bout.flush();
        bout.close();
        fout.close();
        s.close();
        System.out.println("recieved!");
       
        ProcessBuilder probuilder = new ProcessBuilder( command );
        probuilder.directory(new File("F:\\"));
        Process process1 = probuilder.start();
         probuilder = new ProcessBuilder( command1 );
        probuilder.directory(new File("F:\\"));
        Process process = probuilder.start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        System.out.printf("Output of running %s is:\n",
               Arrays.toString(command));
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        
    }
    
}
