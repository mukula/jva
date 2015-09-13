import java.util.ArrayList;
 
public class Order {
  
 String channel = null;
 ArrayList<OrderHeader> orderHeaderList;
  
 public String getChannel() {
  return channel;
 }
 public void setChannel(String channel) {
  this.channel = channel;
 }
 
 public ArrayList<OrderHeader> getOrderHeaderList() {
  return orderHeaderList;
 }
 public void setOrderDetailList(ArrayList<OrderHeader> orderHeaderList) {
  this.orderHeaderList = orderHeaderList;
 }
 
}