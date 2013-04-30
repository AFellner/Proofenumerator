package Logik;

import java.util.*;
import Formeln.*;

public class Kalkuel {
	private boolean debug = true;
	private boolean showstatus = true;
	private Beweisliste moeglBW;
	private Beweisliste beweise;
	private int berechneteTiefe;
	
	public Kalkuel(Beweisliste m)
	{
		moeglBW = new Beweisliste();
		beweise = new Beweisliste();
		Formel[] tmp = {new Variable("X")};
		Beweis dummy = new Beweis(tmp,tmp);
		moeglBW.add(dummy);
		moeglBW.addAll(m);
		Iterator<Beweis> i = moeglBW.iterator();
		while (i.hasNext())
		{
			beweise.add(i.next().clone());
		}
		berechneteTiefe = 0;
	}
	
	public Beweisliste beweiseMitTiefe(int tiefe)
	{
		if (tiefe <= berechneteTiefe)
		{
			Iterator<Beweis> i = beweise.iterator();
			Beweisliste out = new Beweisliste();
			while (i.hasNext())
			{
				Beweis tmpBeweis = i.next();
				if (tmpBeweis.beweisTiefe() == tiefe)
				{
					out.add(tmpBeweis);
				}
			}
			return out;
		}
		else
		{
			return null;
		}
	}
	
	public void berechneBeweise(int tiefe)
	{
		while (berechneteTiefe < tiefe)
		{
			berechneEineEbene();
		}
	}
	public void berechneEineEbene()
	{
		Beweisliste anzusetzen = beweiseMitTiefe(berechneteTiefe);
		Iterator<Beweis> i = anzusetzen.iterator();
		if (debug) System.out.println("Berechnete Tiefe: " + berechneteTiefe + " relevante Beweise fuer diesen Schritt: " + anzusetzen + " ende relevante Beweise");
		while (i.hasNext())
		{
			Beweis tmpBWS = i.next().clone();
			Beweisebene tmpObersteebene = tmpBWS.getObersteEbene();
			ArrayList<Beweisebene> tmpNeueEbenen = tmpObersteebene.moeglicheEltern(moeglBW);
			if (debug) System.out.println("Fuer den Beweis " + tmpBWS + " kommen folgende neuen Ebenen in Frage: " + tmpNeueEbenen);
			Iterator<Beweisebene> j = tmpNeueEbenen.iterator();
			while (j.hasNext())
			{
				Beweisebene tmpEbene = j.next();
				Beweis neuerBeweis = tmpBWS.clone();
				Beweisebene alteOberste = neuerBeweis.getObersteEbene();
				alteOberste.connect(tmpEbene);
				beweise.add(neuerBeweis);
			}
		}
		berechneteTiefe++;
	}
	public Beweisliste getBeweise()
	{
		return beweise;
	}
	public Formelliste alleFolgerungen(Formelliste axiome)
	{
		Formelliste out = new Formelliste();
		Iterator<Beweis> i = beweise.iterator();
		int anzAxiome = axiome.size();
		LinkedList<Integer> bad = new LinkedList<Integer>();
		int anzBeweise = beweise.size();
		int count = 0;
		int itcount = 0;
		Beweis aktBW;
		Beweisebene aktObersteEbene;
		int anzVS;
		Formelliste aktAxiome = new Formelliste();
		Iterator<Integer> k;
		Formelliste aktOut;
		Iterator<Formel> l;
		
		
		while (i.hasNext())
		{
			count ++;
			aktBW = i.next();
			aktObersteEbene = aktBW.getObersteEbene();
			anzVS = aktObersteEbene.getAnzVS();
			if (debug) System.out.println(aktBW);
			for (int j = 0; j < Math.pow(anzAxiome, anzVS); j++)
			{
				itcount++;
				if (itcount == 659255 && debug)
				{
					System.out.println("lala");
				}
				aktAxiome.clear();
				bad = Badisch.dectob(j, anzAxiome, anzVS);
				k = bad.iterator();
				while (k.hasNext())
				{
					aktAxiome.add(axiome.get(k.next()));
				}
				aktOut = aktBW.anwenden(aktAxiome);
				if (aktOut != null)
				{
					l = aktOut.iterator();
					while (l.hasNext())
					{
						out.add(l.next());
					}
				}
				if (debug) System.out.println(itcount + " -te Iteration");
			}
			if (showstatus) System.out.println("Beweis nummer: " + count + " von " + anzBeweise + " abgearbeitet; bisherige Folgerungen: "+ out.size() + " Iterationen: " + itcount);
			
		}
		out.loescheAequivalente();
		return out;
	}
	public void setBeweise(Beweisliste aufdas)
	{
		beweise = aufdas;
	}
}
