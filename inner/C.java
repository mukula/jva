/*
---member inner class with inner class intance created outside method of enclosing class---
in the following example,the class file of inner class B is made by name A$B.class
*/
class C{
class D{
public void show()
{
System.out.println("in C");
}
}

public static void main(String s[])
{
C a=new C();
C.D d=a.new D();
d.show();
}
}