package Logik;
import java.util.LinkedList;

public class Badisch {
	public static LinkedList<Integer> dectob(int dezimalzahl,int base)
	{
		int dez=dezimalzahl;
		LinkedList<Integer> out = new LinkedList<Integer>();
		while (dez > 0)
		{
			out.addFirst(dez%base);
			dez = dez/base;
		}
		return out;
	}
	public static LinkedList<Integer> dectob(int dezimalzahl, int base, int mindeststellen)
	{
		LinkedList<Integer> out = dectob(dezimalzahl,base);
		for (int i = out.size(); i < mindeststellen; i++)
		{
			out.addFirst(0);
		}
		return out;
	}
}