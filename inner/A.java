/*
---member inner class---
here the instance of inner class is created inside a method of enclosing class.
in the following example,the class file of inner class B is made by name A$B.class
*/
class A{
class B{
public void show()
{
System.out.println("in B");
}
}

public void disp()
{
B b=new B();
b.show();
}

public static void main(String s[])
{
A a=new A();
a.disp();
}
}