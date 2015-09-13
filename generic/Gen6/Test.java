//different types of declaration of generic methods
class Gen<T>
{

}
class GenM
{
public <T> void show(Gen<T> ob)//here the Type is decided at compile time
//public void show(Gen<?> ob)//here the type can be any class ,Type is decided at compile time
//public void show(Gen<? extends Number> ob)//it's Upper Bounded Wildcard | type should extends Number or not .Type is decided at compile time
//public void show(Gen<? super Integer> ob)//it's lower Bounded Wildcard. here the type should be parent of integer.
//public void show(Gen<Integer> ob)//here the Type is decided at compile time and type should be strictlt Integer.
//public <T extends Number> void show(Gen<T> ob) //here the Type is decided at compile time and type should extend Number
//public void show(Gen<Number> ob)// here we can pass only Number as type,not other class,not even its subtypes like Integer.

{
System.out.println("in show");
}
}
public class Test
{

public static void main(String ss[])
{
GenM gm=new GenM();

Gen<String> ng=new Gen<>();//for declaration: public <T> void show(Gen<T> ob)
gm.show(ng);
//gm.<String>show(ng);//we need not wite <String before show Java compiler automatically infers from the method's arguments that it's String-it's called type inference.
//we also need not write Gen<String> ng=new Gen<String>(), we can write Gen<String> ng=new Gen<>(), due to type inference.


//Gen<Thread> ng=new Gen<>();//for declaration: public void show(Gen<?> ob)
//gm.<Thread>show(ng);

//Gen<Double> ng=new Gen<>();//for declaration: public void show(Gen<? extends Number> ob)
//gm.show(ng);

//Gen<Number> ng=new Gen<>();//for declaration: public void show(Gen<? super Integer> ob)
//gm.show(ng);

//Gen<Integer> ng=new Gen<>();//for declaration: public void show(Gen<Integer> ob)
//gm.show(ng);

//Gen<Double> ng=new Gen<>();//for declaration: public <T extends Number> void show(Gen<T> ob)
//gm.show(ng);

//Gen<Double> ng=new Gen<>();//not valid ,for declaration: public void show(Gen<Number> ob) 
//gm.show(ng);// if the declaration of show was as void show(Number),we could pass Double  as it is subtype of Number
//but in generic,it's not valid,we should instead use the 6th declaration i.e public <T extends Number> void show(Gen<T> ob)
}
}