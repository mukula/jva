import java.lang.System;
interface Don
{
public void trust();
}
class Sonu implements Don
{
public void trust()
{
System.out.println("sonuuu");
}
public void trut()
{
System.out.println("son");
}

}
public class Tesla
{
public void test(Don d)
{
d.trust();
}
public static void main(String dre[])
{
Sonu s=new Sonu();
Don d=(Don)s;

System.out.println(d.hashCode()+" "+new Sonu().hashCode());
//d.trut();
new Tesla().test(d);
}
}