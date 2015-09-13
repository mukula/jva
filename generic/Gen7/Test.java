class Gen<T>
{
void show(){
System.out.println("Show");
}
}
public class Test
{
public static void main(String stt[])
{
//Gen<Integer> gn[]=new Gen<Integer>[10];//invalid
Gen<?> gn[]=new Gen<?>[10];//valid , but the memory is not allocated yet as objects are not formed because the Type is not decided yet
//now we can pass different types as:-
gn[0]=new Gen<Integer>();//now the objects are formed as type is decided as Integer.
gn[0].show();
gn[1]=new Gen<String>();
gn[1].show();

}
}