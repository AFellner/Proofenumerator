package Test;

import Formeln.*;
import Logik.*;
import java.util.*;

public class Obertest {
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
