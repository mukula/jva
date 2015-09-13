/*

Type refers to classes and interfaces
generics enable types (classes and interfaces) to be parameters when defining classes, interfaces and methods
*/

import static java.lang.System.out;
import java.util.*;
class Gen <T>
{
private T ob;
Gen(T o)
{
ob=o;
}
T get()
{
return ob;
}
void showType()
{
out.println("type: "+ob.getClass().getName());
}
}
public class Test
{
public static void main(String string[])
{
Gen<Integer> i;
i=new Gen<>(10);
i.showType();
int v=i.get();

List list = new ArrayList();
list.add("non-generic");
//String s = (String) list.get(0);//casting required
int d=(Integer)list.get(0);
out.println(d);

List<String> list1 = new ArrayList<String>();
list1.add("generic");
String s1 = list1.get(0);   // no cast
out.println(s1);
}
}