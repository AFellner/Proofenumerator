package Test;
import java.util.ArrayList;

import Formeln.*;
import Logik.*;

public class Test3 {
	public static void main(String[] args) throws Exception
	{
		Formelliste testarray = new Formelliste();
		Variable x = var("x");
		Variable y = var("y");
		Variable z = var("z");
		Variable a = var("a");
		Variable b = var("b");
		Variable x1 = var("x1");
		Variable x2 = var("x2");
		Variable x3 = var("x3");
		Variable x4 = var("x4");
		Formel phi = imp(x,imp(y,x));
		Formel psi = imp(imp(a,b),a);
		Formel f3 = imp(imp(x1,x2),imp(x3,x4));
		Formelliste fl = new Formelliste();
		fl.add(phi);
		fl.add(psi);
		//fl.add(f3);
		System.out.println(Unifikator.unifiziere(fl));
		//System.out.println(phi.substituiere(Unifikator.unifiziere(fl)));
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