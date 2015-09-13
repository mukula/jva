/*
in the following example,The importance of casting is depicted.
we can make a variable of Parent class point to object of it's child class,but not vice versa i.e child class
variable cannot refer to parent class object because child class has all the properties of parent class,but not vice versa.
if we try to do so,compile time error is generated.but we can prevent it by CASTING.without casting the statement: Dog d=new Animal();
will generate compile time error,but we we write as: Dog d=(Dog)new Animal(); then C.T error is not generated,but at runtime ,the Exception 
of ClassCastException thrown as I said before that child class can't refer to parent class object.
So why the heck do we need casting when it fails at run time....?...let's check it out

Basically,by casting,we ask compiler to not generate error while we make child class point to parent class object.but when a 
variable of child class is assigned to variable of parent class ,the C.T error is again generated which we really don't need always.
it's because the parent class variable need not refer to object of it's own type.for eg: the variable of Animal need not refer to
object of Animal class always,it can refer to even Dog or Cat class object.so if we have statements as:
Animal a=new Dog(); // Valid
Dog d=a;// C.T error

in above statement we are want variable d to refer to Dog object indirectly i.e through a,but it will throw C.T error and we know 
that the code will work fine if compiled successfully,because ultimately d is gonna point to Dog object which is fine . so now
casting comes for our rescue.we can cast to avoid C.T error,and hence,at run time d points to Dog object and hence no exception is thrown.
so we can rewrite as :
Animal a=new Dog(); 
Dog d=(Dog)a;
but we should not use casting for cases like:
Animal a=new Animal();
Dog d=(Dog) a;
this will definately throw runtime exception as d cant point to parent class object.

So when is casting really useful?
I have show it in below program.  get() of Test class returns variable animal,which can point to either 
Animal or Dog or Cat.so if we know that it will point to Dog Object,then we can cast it to Dog and
access it.we must better check it animal points to Dog by instanceof operator,because if animal
points to cat,the casting will throw Exception at R.T.

lets take following program as an example.
*/
class Animal 
{ 
    public void callMeGeneral()
    {
        System.out.println("I am animal");
    }
}
class Dog extends Animal 
{ 
    public void callMeSpecific()
    {
        System.out.println("I am Dog");
    }

    public void callMyName()
    {
        System.out.println("My name is Tommy");
    }
}
class Cat extends Animal
{
 public void callMeSpecific()
    {
        System.out.println("I am Cat");
    }

    public void callMyName()
    {
        System.out.println("My name is kitty");
    }
}
class Test
{
Animal animal;
Test()
{
animal=new Dog();
//animal=new Cat(); //try this statement also
}
public Animal get()
{
return animal;
}
}
public class Casting
{
    public static void main (String [] args) 
    {
       Animal a=new Dog(); 
       Dog d=(Dog)a;
       d.callMeSpecific();
	   
	   Animal animal=new Test().get();
	   if(animal instanceof Dog){
	   Dog dog = (Dog)animal;
	   dog.callMeSpecific();
	   dog.callMyName();
	   }
	   if(animal instanceof Cat){
	   Cat cat = (Cat)animal;
	   cat.callMeSpecific();
	   cat.callMyName();
	   }
	   
    }
}