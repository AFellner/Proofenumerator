package Logik;

import java.util.*;

import Formeln.Formel;
import Formeln.Variable;

public class Beweisebene extends Beweisliste{
	private boolean debug = false;

	public ArrayList<Beweisebene> moeglicheEltern(Beweisliste moeglBW)
	{
		ArrayList<Beweisebene> out = new ArrayList<Beweisebene>();
		for (int i = 0; i < Math.pow(moeglBW.size(),this.getAnzVS()); i++)
		{
			LinkedList<Integer> bad = Badisch.dectob(i, moeglBW.size());
			int aktAnzFolg = 0;
			Beweisebene tmpEbene = new Beweisebene();
			if (debug) System.out.println("BWSchritt: bad" + bad);
			for (int j = 0; j < bad.size(); j++)
			{
				aktAnzFolg += moeglBW.get(bad.get(j)).getAnzFolg();
				tmpEbene.add(moeglBW.get(bad.get(j)));
			}
			if (debug) System.out.println("Beweisschritt anzahl der VS: " + aktAnzFolg + " this: " + this.getAnzVS());
			if (aktAnzFolg <= this.getAnzVS())
			{
				for (int k = aktAnzFolg; k < this.getAnzVS(); k++)
				{
					tmpEbene.add(0,moeglBW.get(0));
				}
				out.add(tmpEbene);
			}
			
		}
		return out;
	}
	
	public int getAnzVS()
	{
		int out = 0;
		Iterator<Beweis> i = this.iterator();
		while (i.hasNext())
		{
			out += i.next().getAnzVS();
		}
		return out;
	}
	
	public int getAnzFolg()
	{
		int out = 0;
		Iterator<Beweis> i = this.iterator();
		while (i.hasNext())
		{
			out += i.next().getAnzFolg();
		}
		return out;
	}
	
	public void connect(Beweisebene withThis)
	{
		Iterator<Beweis> i = this.iterator();
		int count = 0;
		while (i.hasNext())
		{
			Beweis tmpBWS = i.next();
			Beweisliste tmpList = new Beweisliste();
			int anzEltern = tmpBWS.getAnzVS();
			if (debug) System.out.println(anzEltern + " neu eltern von " + tmpBWS);
			for (int j = count; j < count+anzEltern; j++)
			{
				tmpList.add(withThis.get(j));
				if (debug) System.out.println(withThis.get(j).clone());
			}
			tmpBWS.addEltern(tmpList);
			count += anzEltern;
			
		}
	}
	
	public String toString(boolean eltern)
	{
		String out = "";
		Iterator<Beweis> i = this.iterator();
		while (i.hasNext())
		{
			out = out + i.next().toString(eltern);
		}
		return out;
	}
	public String toString()
	{
		return toString(false);
	}
}
