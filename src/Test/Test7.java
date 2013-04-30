package Test;

import Formeln.*;
import Logik.*;


public class Test7 extends Obertest{
	public static void main(String args[]) throws Exception
	{
		Variable a = var("a");
		Variable b = var("b");
		Variable c = var("c");
		Variable a1 = var("a1");
		Variable b1 = var("b1");
		Variable U = var("U");
		Variable V = var("V");
		Formel test1 = imp(a,imp(b,c));
		Formel test2 = imp(a1,imp(b1,a1));
		Formel test3 = imp(imp(a,imp(b,c)),imp(imp(a,b),imp(a,c)));
		Substitution unif = Unifikator.unifiziere(test2,test1);
		
		System.out.println(test1);
		System.out.println(test2);
		System.out.println(unif);
		//unif.remove(a);
		System.out.println(test1.substituiere(unif));
		System.out.println(test2.substituiere(unif));
		Substitution testsubs1 = new Substitution();
		testsubs1.put(U, test1);
		Substitution testsubs2 = new Substitution();
		testsubs2.put(U, test2);
		System.out.println(testsubs1.merge(testsubs2));
		Formelliste vs = new Formelliste();
		vs.add(imp(U,V));
		vs.add(U);
		Formelliste folg = new Formelliste();
		folg.add(V);
		Beweis mp = new Beweis(vs,folg);
		System.out.println(mp);
		Formelliste axiome = new Formelliste();
		axiome.add(test3);
		axiome.add(test2);
		
		System.out.println(mp.anwenden(axiome));
	}
}
