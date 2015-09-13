import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
 
public class pjon {
  
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
  Channel channel = gson.fromJson(myJSONString, Channel.class);
 
  System.out.println("Channel info --->");
  System.out.println("Id: " + channel.getId());
  System.out.println("Name: " + channel.getName());
  System.out.println("Fieled1: " + channel.getField1());
  System.out.println("Field2: " + channel.getField2());
  System.out.println("created_at: " + channel.getCreated_at());
  System.out.println("last updated at: " + channel.getUpdated_at());
  System.out.println("last entry id: " + channel.getLast_entry_id());
  
  
  ArrayList<Feed> feedList = channel.getFeedList();
  for (int i=0; i<feedList.size(); i++){
   System.out.println("Channel  --->");
   Feed feed = feedList.get(i);
   System.out.println("created_at: " + feed.getcreated_at());
   System.out.println("Item Number: " + feed.getentry_id());
   System.out.println("Quantity: " + feed.getfield1());
   System.out.println("Price: " + feed.getfield2());
    
  }
 }
}
class Channel {
 private int id = 0;
 private String name = null;
 private String field1 = null;
 private String field2=null;
 private String created_at=null;
 private  String updated_at=null;
 private  int last_entry_id=0;
 
 ArrayList<Feed> feeds;
  
 public int getId() {
  return id;
 }
 public String getName() {
  return name;
 }
 public String getField1() {
  return field1;
 }
 public String getField2() {
  return field2;
 }
 public String getCreated_at()
 {
 return created_at;
 }
 public String getUpdated_at()
 {
 return updated_at;
 }
 public int getLast_entry_id()
 {
 return last_entry_id;
 }
 public ArrayList<Feed> getFeedList() {
  return feeds;
 }
 
 
}
class Feed {
  
 String created_at = null;
 String entry_id = null;
 int field1 = 0;
 Double field2 = null;
  
 public String getcreated_at() {
  return created_at;
 }
 public void setcreated_at(String created_at) {
  this.created_at = created_at;
 }
 public String getentry_id() {
  return entry_id;
 }
 public void setentry_id(String entry_id) {
  this.entry_id = entry_id;
 }
 public int getfield1() {
  return field1;
 }
 public void setfield1(int field1) {
  this.field1 = field1;
 }
 public Double getfield2() {
  return field2;
 }
 public void setfield2(Double field2) {
  this.field2 = field2;
 }
 
}