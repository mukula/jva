/*
by using RandomAccessFile(RAF),we can create a file in which we can read or write from any point within file.thus we can append it or start writing
or reading at desired point in file.
*/
import java.io.RandomAccessFile;
class Cono{  	
public static void main(String args[]){  
try{
	RandomAccessFile file = new RandomAccessFile("F:\\core\\java\\java\\random_access_file\\rfile.txt", "rw");//creates text file at given location and "rw" specifies that it can be written and read.
	file.write("Hello World".getBytes());//it writes at begining of file
	file.seek(file.length());//it sets the pointer at end of file
	file.write(0x0A);//this is ascii code for new line
	file.write("Welcome".getBytes());//it writes in new line
	file.seek(0);//it sets the pointer to 1st byte i.e character 'H'
	file.write("W".getBytes());//replaces 1st character 'H' with 'W',then pointer is incemented and points to 'e'
	int aByte = file.read();//it pass 1st byte to aByte
    file.close();
	System.out.println((char)aByte);//prints e
} 
catch(Exception e){}
}
}  