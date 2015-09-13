class GenWildNumber<T extends Number>
{
//in following method,we can pass any class as type that extends Number because T extends Number in above class declaration
public void show(GenWildNumber<?> ob)
{
System.out.println("Show Number");
}

}
class GenWildAny<T>
{
//in this method,we can pass any class as parameter.
public void show(GenWildAny<?> ob)
{
System.out.println("Show Any");
}
public void showMe(GenWildNumber<?> ob)
{
System.out.println("ShowMe");
}
}
public class WildCard
{
public static void main(String string[])
{
GenWildNumber<Integer> gen=new GenWildNumber<>();
GenWildNumber<Double> gen1=new GenWildNumber<>();
GenWildNumber<Short> gen2=new GenWildNumber<>();
//valid for any type extending Number
gen.show(gen1);
gen.show(gen2); 

//valid for any class Type
GenWildAny<Double> gen3=new GenWildAny<>();
GenWildAny<Thread> gen4=new GenWildAny<>();
gen3.show(gen4);
gen3.showMe(gen1);
//gen1.show(gen3);//not valid,as gen1 is of GenWildNumber and gen3 is of GenWildAny

}

}