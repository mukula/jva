/*
in following program,we make a interface- Adder that extends other interface called Remote.
Adder contains abstract methods that we want to invoke remotely.
*/
import java.rmi.*;
import java.io.*;
public interface Adder extends Remote{
public int add(int x,int y)throws RemoteException;

}
