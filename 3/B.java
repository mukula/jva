class A
{
static int i=1;
 static {
i++;
System.out.println("sai"+i);
}
}
class B
{
public static void main(String s[])
{
A a=new A();
A b=new A();

}
}