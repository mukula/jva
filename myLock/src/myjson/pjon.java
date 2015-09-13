package myjson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
 public class pjon {
     //private String filename;
     private Feed feed;
     private  Gson gson;
     private Channel channel;
     private ArrayList<Feed> feedList;
public pjon(String filename)
 {
    String myJSONString = "";
  BufferedReader bufferedReader = null;
  try {
   String sCurrentLine;
   bufferedReader = new BufferedReader(new FileReader(filename));
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
channel = gson.fromJson(myJSONString, Channel.class);
feedList = channel.getFeedList();
 }
 public int siz()
 {
  return feedList.size();
 }
 public String getf1(int i){
     this.feed = feedList.get(i);
     return feed.getcreated_at();
 }
 public String getf2(int i){
     this.feed = feedList.get(i);
     return feed.getfield1();
 }}
 


class Channel {
 private int id = 0;
 private String name = null;
 private String field1 = null;
 private String field2=null;
 private String field3=null;
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
 public String getField3() {
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
 String field1 = null;
 String field2 = null;
 String field3 = null;
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
 public String getfield1() {
  return field1;
 }
 public void setfield1(String field1) {
  this.field1 = field1;
 }
 public String getfield2() {
  return field2;
 }
 public void setfield2(String field2) {
  this.field2 = field2;
 }
 
}
