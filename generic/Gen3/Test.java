//multiuple type parameters in Generic classes
import static java.lang.System.out;
class Gen<T,V>
{
T ob1;
V ob2;
Gen(T ob1,V ob2)
{
this.ob1=ob1;
this.ob2=ob2;
}
void showType()
{
out.println("Type of ob1: "+ob1.getClass().getName()+"\nType of ob1: "+ob2.getClass().getName());
}
}
public class Test
{
public static void main(String string[])
{
Gen<Integer,String> gen=new Gen<>(1,"sai");
gen.showType();

}
}