public class Aa{
int a;
Aa(int a)
{
this.a=a;
}
}
class Ba
{
int a;
Ba get(Aa s)
{
Ba b=new Ba();
b.a=s.a+2;
return  b;
} 
}