import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
 //import com.as400samplecode.util.OrderDetail;
//import com.as400samplecode.util.OrderHeader;
import com.google.gson.Gson;
 
public class ParseJSON {
  
 public static void main(String[] args) {
 
  String myJSONString = "";
  BufferedReader bufferedReader = null;
 
  try {
 
   String sCurrentLine;
    
   bufferedReader = new BufferedReader(new FileReader("C:\\java\\json - Copy\\myFile.json"));
 
   while ((sCurrentLine = bufferedReader.readLine()) != null) {
    myJSONString = myJSONString + sCurrentLine;
   }
 
  } catch (IOException e) {
   e.printStackTrace();
  } finally {
   try {
    if (bufferedReader != null){
     bufferedReader.close();
    }
   } catch (IOException ex) {
    ex.printStackTrace();
   }
  }
   
  Gson gson = new Gson();
  OrderHeader orderHeader = gson.fromJson(myJSONString, OrderHeader.class);
 
  System.out.println("Order Information --->");
  System.out.println("Customer Id: " + orderHeader.getId());
  System.out.println("Order Id: " + orderHeader.getName());
  System.out.println("Order Total: " + orderHeader.getFld1());
  System.out.println("Order Total: " + orderHeader.getFld2());
   
  ArrayList<OrderDetail> orderDetailList = orderHeader.getOrderDetailList();
  for (int i=0; i<orderDetailList.size(); i++){
   System.out.println("Order Detail --->");
   OrderDetail orderDetail = orderDetailList.get(i);
   System.out.println("Line Id: " + orderDetail.getcreated_at());
   System.out.println("Item Number: " + orderDetail.getentry_id());
   System.out.println("Quantity: " + orderDetail.getfield1());
   System.out.println("Price: " + orderDetail.getfield2());
    
  }
   
 }
}