package Test;
import java.util.ArrayList;

import Formeln.*;
import Logik.*;

public class Test {
	public static void main(String[] args) throws Exception
	{
		Variable u = var("u");
		Variable v = var("v");
		Formel[] vs = {imp(u,v),u};
		Formel[] folg = {v};
		Beweisliste eltern = new Beweisliste();
		Beweis MP = new Beweis(vs,folg);
		Beweis dummy = new Beweis(folg,folg);
		eltern.add(MP);
		eltern.add(dummy);
		//eltern.add(MP);
		Beweis abglMP = new Beweis(vs,folg,eltern);
		Variable a = var("a");
		Variable b = var("b");
		Variable c = var("c");
		Variable d = var("d");
		Variable x = var("x");
		Variable y = var("y");
		Formel[] inp1 = {imp(a,imp(c,d)),a};
		Formel[] inp2 = {imp(c,d),c};
		Formel[] inp3 = {imp(a,dis(x,c)),a,y};//imp(y,d),y};
		System.out.println(abglMP.anwenden(inp3));
		//Formelliste test = new Formelliste(inp3);
		//System.out.println(test.subList(0, 2));
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