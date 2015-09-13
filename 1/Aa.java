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
int get(Aa s)
{
this.a=s.a;
return this.a;
} 
}