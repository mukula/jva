import static java.lang.System.out;
class NonGen
{

Object ob;
void set(Object o)
{
ob=o;
}
Object get()
{
return ob;
}
}
public class NonGenTest
{
public static void main(String string[]){
NonGen ng=new NonGen();//non generic class
ng.set("sai");//adding string
//String ss=ng.get();//Compile Time error
String s=(String)ng.get();//need explicit casting -----> but generic classes provide implicit casting
int v=(Integer)ng.get();//compiles O.K ,but gives runtime ClassCastException  , so it doesnot ensures Type safety,but Generics classes do.
out.println(v);
}}