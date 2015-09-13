import java.util.ArrayList;
 
public class OrderHeader {
  int id = 0;
 String name = null;
 String field1 = null;
 String field2=null;
 
 ArrayList<OrderDetail> orderDetailList;
  
 public int getId() {
  return id;
 }
 public void setId(int customerId) {
  this.id = customerId;
 }
 public String getName() {
  return name;
 }
 public void setName(String orderId) {
  this.name = name;
 }
 public String getFld1() {
  return field1;
 }
 public void setFld1(String fld){
 this.field1=fld;
 }
 public String getFld2() {
  return field2;
 }
 public void setFld2(String fld){
 this.field2=fld;
 }
 
 public ArrayList<OrderDetail> getOrderDetailList() {
  return orderDetailList;
 }
 public void setOrderDetailList(ArrayList<OrderDetail> orderDetailList) {
  this.orderDetailList = orderDetailList;
 }
 
}