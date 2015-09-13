/*
----annonymous classes with interface----
to use interface,we need to implement it and override,but by ann. inner class,we can define the methods of 
interface while  instantiation.so we need not implement the interface.
in following example,compiler creates class file of inner class by name B$1.class.its source code is:

class B$1 implements A
{
public void show()
{
System.out.println("sai");
}
}
*/
interface A
{
public void show();
}
class B
{
public static void main(String s[])
{
A a=new A(){public void show(){System.out.println("sai");}};
a.show();
}
}