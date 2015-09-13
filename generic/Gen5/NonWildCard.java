// Use a wildcard.
class Gen<T extends Number> {
/*in following method we can either pass T as type or any other class that extends number.that means it pre-decided or
decided at compile time.if we write as: void show(Gen<T> ob),then we can only pass type which was declared 
at object creation,which in this case is Integer.
*/
void show(Gen<T> ob)
{
System.out.println("Show successful");
}
/*
if we declare as void show(Gen<Double> ob),then we will be restricted to only Double as type.
*/
void show1(Gen<Double> ob)
{
System.out.println("Show1 successful");
}
}
// Demonstrate wildcard.
public class NonWildCard {
public static void main(String args[]) {
Gen <Integer> gen=new Gen<>();
Gen <Integer> geni=new Gen<>();
Gen <Double> gen1=new Gen<>();
Gen <Short> gen2=new Gen<>();
gen.show(geni);//valid
gen.show1(gen1);//valid
//gen.show(gen1);//invalid
//gen.show1(gen2);//invalid
//gen.show1(geni);//invalid
}
}