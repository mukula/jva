import static java.lang.System.out;
class Gen <T>
{

//T ob=new T();//invalid
//T ob2[]=new T[10];//cannot create object because the size of object is not known until run time
//T[] ob1; //valid reference
T ob;
void set(T o)
{
ob=o;
}
T get()
{
return ob;
}
}
public class GenTest
{
public static void main(String string[]){
Gen<String> gen=new Gen<>();//non generic class
gen.set("sai");//adding string
String s=gen.get();//no need of casting
out.println(s);
//int v=gen.get();//gives Compile time error,so ensures type safety

//we can also use generic classes as non-generic classes,but then they act as non generic classes-they are called as raw types.
Gen ng=new Gen();
ng.set("sai");
String ss=(String)ng.get();//casting required
int vv=(Integer)ng.get();//compiles ok,but gives runtime error.
/*many legacy code(old codes but widely used) are written in non generic classes,so generic classes are
made possible to use as non generic classes.
the above raw type codes can give warning as:
Note: GenTest.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.

The term "unchecked" means that the compiler does not have enough type information to perform all type checks necessary to ensure type safety. The "unchecked" warning is disabled, by default, though the compiler gives a hint. 
To see all "unchecked" warnings, recompile with -Xlint:unchecked.

*/
}
}