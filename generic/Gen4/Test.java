//Bounded types- here we can pass Type who extends Number.
//class Gen<T extends MyClass & MyInterface> //for interfaces
class Gen<T extends Number>
{

Gen(T g)
{
T ob=g;
}

}
public class Test
{
public static void main(String string[])
{
Gen<Integer> in=new Gen(10);
Gen<Double> db=new Gen(10.0);
//Gen<String> st=new Gen("sai");//gives compile time error,as String doesnot extends number
}
}