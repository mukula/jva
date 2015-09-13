/*
 *********************************************
 *      ______________________________       *
 *     //                            \\      * 
 *    //         G.SAIKIRAN           \\     * 
 *   //________________________________\\    *
 *                                           *
 *********************************************
program meant to run given file for remote commands.
*/
package console;
import java.io.*; 
import java.net.*;
import java.util.*;
import java.util.stream.*;
class Thread1 extends Thread
{
    private Thread2 t1;
    private BufferedReader br;
    private Process process;
Thread1(Process process)
{
 try{   
 this.t1=t1;
this.process=process;    
br=new BufferedReader(new InputStreamReader(process.getInputStream()));
 }catch(Exception e){System.out.println("failed in Thread1 const");}
 start();
}        
public void run()
{
try{
    String line;
while ((line = br.readLine()) != null && process.isAlive()) {
           
    System.out.println(line);
        }

process.destroy();//t1.stop();
}
catch(Exception e){System.out.println("failed in Thread1 run");}
}
}
class Thread2 extends Thread
{    
private BufferedReader br;    
private BufferedWriter bw;
private Process process;
Thread2(Process process)
 {
this.process=process;
br=new BufferedReader(new InputStreamReader(System.in));
bw=new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
start();
 }
public void run()
{try{
String line;

while(process.isAlive())
{
line=br.readLine();
bw.write(line+"\n");
bw.flush();
Thread.sleep(20);
}
}
catch(Exception e){System.out.println("failed in Thread2 run");e.printStackTrace();}
}
}

public class Cons {

    public static void main(String[] args) throws Exception {
      
        String[] command = {"CMD", "/C", "java Hello"};
        ProcessBuilder probuilder = new ProcessBuilder();
        probuilder.command(command);
        probuilder.directory(new File("c:\\java\\1"));
        Process process = probuilder.start();
        
        
        new Thread1(process);
        new Thread2(process);
         
         
             }
    
}
