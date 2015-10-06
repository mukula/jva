/*install oracle database and make "pass" as its passwprd
  create a table by query:  create table first (id int,name varchar2(20))
  populate the table by query: insert into first values(1,'sai')
  now add odbc14.jar to libraries in OrcleCon project in netbeans
  odbc14 is loacted at C:\oraclexe\app\oracle\product\10.2.0\server\jdbc\lib\odbc14.jar
  now run the project.
*/     
package oraclecon;
import java.sql.*;
class OracleCon{
public static void main(String args[]){
try{
    String x=new String("sad");
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost","system","sai121");
PreparedStatement pst = con.prepareStatement("delete from cod where code=?");
	  	pst.setString(1,x);

	  	pst.executeUpdate();
Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("select * from cod");
while(rs.next())
System.out.println(rs.getString(1)+"  "+rs.getString(2));
con.close();
}catch(Exception e){ System.out.println(e);}
}
}