/*
----Annonymous inner classes-----
Annonymous inner classes are inner classes whose name is decided by compiler.
if a class has to override a method of some other class,then it has to extend that class.
we generally override to use our own implementation of that particular method.
but if we want to override the method just to use our own implementation of that particular method,
we can use annonymous inner classes.
this can be useful when we have to override methods of several classes as java doesnot support 
multiple inheritance.

in following example,compiler creates two class files for two inner classes used in outer class B and 
are named as B$1.class, B$2.class.
the source code genertaed for class B$1 is:

class B$1 extends A1
{
public void show()
{
System.out.println("show() v3.0");
}
}

similarly for class B$2

class B$2 extends A2
{
public void show()
{
System.out.println("show() v4.0");
}
}


*/
class A1
{
public void show()
{
System.out.println("show() v1.0");
}
}
class A2{
public void show()
{
System.out.println("show() v2.0");
}
}
class B
{
public void show()
{
A1 a1=new A1(){public void show(){System.out.println("show() v3.0");}};
a1.show();
A2 a2=new A2(){public void show(){System.out.println("show() v4.0");}};
a2.show();

}
public static void main(String s[])
{
B b=new B();
b.show();
}
}