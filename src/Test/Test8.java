package Test;

import Formeln.*;
import Logik.*;

public class Test8 extends Obertest{
	public static void main(String args[]) throws Exception
	{
		Variable a = var("a");
		Variable b = var("b");
		Variable c = var("c");
		Variable a1 = var("a1");
		Variable b1 = var("b1");
		Variable U = var("U");
		Variable V = var("V");
		Formel test1 = imp(a,b);
		Formel test2 = imp(b1,imp(U,V));
		Substitution bla = new Substitution();
		bla.put(U, test1);
		System.out.println(test2.substituiere(bla).getVariable());
	}
}
