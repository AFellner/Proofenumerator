package Test;
import java.util.ArrayList;
import java.util.Iterator;

import Formeln.*;
import Logik.*;

public class Test4 {
	public static void main(String[] args)
	{
		Variable v = var("V");
		Variable u = var("U");
		Formelliste vs = new Formelliste();
		Formelliste folg = new Formelliste();
		vs.add(imp(u,v));
		vs.add(u);
		folg.add(v);
		Beweis mp = new Beweis(vs,folg);
		Variable a = var("A");
		Variable b = var("B");
		Variable c = var("C");
		Formel ax1 = imp(imp(a,imp(b,c)),imp(b,imp(a,c)));
		Formel ax2 = imp(a,imp(b,a));
		Formelliste axiome = new Formelliste();
		axiome.add(ax1);
		axiome.add(ax2);
		try {
			Formelliste out = mp.anwenden(axiome);
			Iterator<Formel> i = out.iterator();
			System.out.println("Folgerungen:");
			while (i.hasNext())
			{
				System.out.println(i.next());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Substitution test = new Substitution();
		test.put(u, imp(b,c));
		Substitution test2 = new Substitution();
		test2.put(u, imp(a,b));
		test2.put(v, dis(a,b));
		System.out.println(test.put(u, imp(b,c)));
		System.out.println(test);
		System.out.println(imp(u,v).substituiere(test));
		try{
			System.out.println(test2.merge(test));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(mp);
		Beweisliste moeglBW = new Beweisliste();
		moeglBW.add(mp);
		Formelliste vs2 = new Formelliste();
		Formelliste folg2 = new Formelliste();
		vs2.add(kon(u,v));
		folg2.add(u);
		folg2.add(v);
		Beweis vundu = new Beweis(vs2,folg2);
		moeglBW.add(vundu);
		System.out.println(moeglBW);
		Beweisebene bwtest = new Beweisebene();
		bwtest.add(mp);
		System.out.println(bwtest);
		System.out.println(bwtest.moeglicheEltern(moeglBW));
	}
	public static Negation neg(Formel n)
	{
		return new Negation(n);
	}
	public static Disjunktion dis(Formel d1,Formel d2)
	{
		return new Disjunktion(d1,d2);
	}
	public static Implikation imp(Formel d1,Formel d2)
	{
		return new Implikation(d1,d2);
	}
	public static Konjunktion kon(Formel d1,Formel d2)
	{
		return new Konjunktion(d1,d2);
	}
	public static Variable var(String name)
	{
		return new Variable(name);
	}
}