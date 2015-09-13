import java.util.*;

public class RunServer {


    public static void main(String[] args) throws Exception {
      List al=new ArrayList();
	  al.add("javac");
	  al.add("java");
        
		Server s=new Server(67);
		s.setCmdList(al);
		s.begin();
		
         
             }
    
}
