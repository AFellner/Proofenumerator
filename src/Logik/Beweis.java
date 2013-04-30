package Logik;
import java.util.*;

import Formeln.*;

public class Beweis{

	private boolean debug = false;
	private boolean debugnull = false;
	private Formelliste voraussetzungen;
	private Formelliste folgerungen;
	private Beweisliste eltern;
	private boolean hatEltern = false;
	
	public Beweis(Formelliste in, Formelliste out)
	{
		voraussetzungen = in;
		folgerungen = out;
	}

	public Beweis(Formelliste in, Formelliste out, Beweisliste elt)
	{
		this(in,out);
		eltern = elt;
		hatEltern = true;
	}
	public Beweis (Formel[] in, Formel[] out)
	{
		this(new Formelliste(in), new Formelliste(out));
	}
	public Beweis (Formel[] in, Formel[] out, Beweisliste elt)
	{
		this(new Formelliste(in), new Formelliste(out),elt);
	}
	public Formelliste anwenden(Formel[] axiome) throws Exception
	{
		Formelliste tmp = new Formelliste(axiome);
		return anwenden(tmp);
	}
	public Formelliste anwenden(Formelliste axiome)
	{
		Formelliste aktAxiome = new Formelliste();
		Beweis aktElt;
		int counter;
		Iterator<Beweis> i;
		Beweisebene aktEltOben;
		Iterator<Beweis> j;
		int tmp;
		if (hatEltern)
		{
			i = eltern.iterator();
			counter = 0;
			while (i.hasNext())
			{
				aktElt = i.next();
				aktEltOben = aktElt.getObersteEbene();
				j = aktEltOben.iterator();
				tmp = 0;
				while (j.hasNext())
				{
					tmp += j.next().getAnzVS();
				}
				Formelliste teilliste = new Formelliste();
				if ((counter+tmp) <= axiome.size())
				{
					for (int k = counter; k < counter+tmp; k++)
					{
						teilliste.add(axiome.get(k));
					}
					Formelliste tmpList = aktElt.anwenden(teilliste);
					if (tmpList != null)
					{
						aktAxiome.addAll(tmpList);
					}
				
					else
					{
						if (debugnull) System.out.println("Null, weil eltern null liefern");
						return null;
					}
					counter += tmp;
				}
				else
				{
					if (debugnull) System.out.println("Null, weil mehr Axiome gebraucht: " + (counter+tmp) + " gebraucht und " + axiome.size() + " geliefert");
					return null;
				}
			}
		}
		else
		{
			aktAxiome = axiome;
		}
		/*aktAxiome muessen vorher korrekt bestimmt werden
		*das sind entweder direkt die uebergebenen Axiome
		*oder Ergebnisse aus Elternbeweisschritten
		*/
		if (debug){
			System.out.println("Regel: " + voraussetzungen + " folgt " + folgerungen); 
			System.out.println("wird angewandt mit folgenden Axiomen:");
			System.out.println(aktAxiome);
		}
		return anwendenAufAxiome(aktAxiome);
	}
	private Formelliste anwendenAufAxiome(Formelliste axiome)
	{
		Formelliste out = new Formelliste();
		Iterator<Formel> i = voraussetzungen.iterator();
		Iterator<Formel> j = axiome.iterator();
		Substitution subs = new Substitution();
		ArrayList<Variable> verwendeteVar = new ArrayList<Variable>();
		ArrayList<Variable> aktVariable;
		Iterator<Variable> l;
		Variable aktVar;
		Substitution tmpSubs;
		Formel aktVs;
		Formel aktAxiom;
		Substitution uni;
		Formel aktFolg;
		while (i.hasNext())
		{
			aktVs = i.next();
			aktAxiom = j.next();
			aktVariable = (ArrayList<Variable>) aktAxiom.getVariable().clone();
			l = aktVariable.iterator();
			while (l.hasNext())
			{
				aktVar = l.next();
				if (verwendeteVar.contains(aktVar))
				{
					if (debug) System.out.println("Die Variable " + aktVar + " kam bereits vor");
					tmpSubs = new Substitution();
					tmpSubs.put(aktVar, aktVar.clone());
					aktAxiom = aktAxiom.substituiere(tmpSubs);
				}
			}
			verwendeteVar.addAll(aktAxiom.getVariable());
			if (debug) System.out.println("Unifikator: " + Unifikator.unifiziere(aktVs,aktAxiom));
			uni = Unifikator.unifiziere(aktVs,aktAxiom);
			if (uni != null)
			{
				subs = subs.merge(uni);
			}
			else
			{
				if (debugnull) System.out.println("Null, weil zwei Substitutionen nicht zusammenpassen");
				return null;
			}
			if (debug) System.out.println("Substitution gefunden: " + subs);
		}
		if (debug) System.out.println("Gesamte Substitution: " + subs);
		if (subs != null)
		{
			i = folgerungen.iterator();
			while (i.hasNext())
			{
				aktFolg = i.next();
				if (debug) System.out.println ("wegen der Substitution wird " + aktFolg + " durch " + aktFolg.substituiere(subs) + " ersetzt");
				out.add(aktFolg.substituiere(subs));
			}
			if (out.equals(folgerungen))
			{
				if (debugnull) System.out.println("Null, weil keine wirkliche Folgerung geschlossen wurde (verkehrte Substitution)");
				return null;
			}
		}
		else
		{
			return null;
		}
		return out;
	}
	public int getAnzFolg()
	{
		return folgerungen.size();
	}
	public int getAnzVS()
	{
		return voraussetzungen.size();
	}
	public String toString(boolean printeltern)
	{
		
		String out = "";
		if (printeltern)
		{
			out = out + "Anfang\n";
			HashMap<Integer,Beweisebene> map = toMap();
			for (int i = map.size() -1 ; i >= 0;i--)
			{
				out = out + map.get(i).toString()+ "\n";
			}
			out = out + "Ende\n";
		}
		else if(!printeltern)
		{
			out = out + "{";
			Iterator<Formel> i = voraussetzungen.iterator();
			while (i.hasNext())
			{
				out = out + i.next() + ", ";
			}
			out = out.substring(0, out.length()-2);
			out = out + "}=>{";
			int tmp = out.length();
			i = folgerungen.iterator();
			while (i.hasNext())
			{
				out = out + i.next() + ", ";
			}
			out = out.substring(0, out.length()-2);
			out = out + "}";
		}
		return out;
	}
	public String toString()
	{
		return toString(true);
	}
	public Beweis clone()
	{
		if (hatEltern)
		{
			
			Iterator<Beweis> i = eltern.iterator();
			Beweisliste neueEltern = new Beweisliste();
			while (i.hasNext())
			{
				neueEltern.add(i.next().clone());
			}
			if (debug) System.out.println ("Beim klonen: schon Eltern und zwar: " + neueEltern);
			return new Beweis(voraussetzungen,folgerungen,neueEltern);
		}
		else
		{
			if (debug) System.out.println ("Beim klonen: keine Eltern");
			return new Beweis(voraussetzungen,folgerungen);
		}
	}
	public int beweisTiefe()
	{
		int out = 0;
		if (hatEltern)
		{
			Iterator<Beweis> i = eltern.iterator();
			int tmp = 0;
			while (i.hasNext())
			{
				Beweis tmpElt = i.next();
				tmp = tmpElt.beweisTiefe();
				if (tmp > out)
				{
					out = tmp;
				}
			}
			out++;
		}
		return out;
	}
	public Beweisebene getObersteEbene()
	{
		Beweisebene out = new Beweisebene();
		if (hatEltern)
		{
			Iterator<Beweis> i = eltern.iterator();
			while (i.hasNext())
			{
				Beweis tmpBWS = i.next();
				out.addAll(tmpBWS.getObersteEbene());
			}
		}
		else
		{
			out.add(this);
		}
		return out;
		
	}
	public Beweisebene getEbene(int nummer)
	{
		Beweisebene out = new Beweisebene();
		if (nummer > 0)
		{
			if (hatEltern)
			{
				Iterator<Beweis> i = eltern.iterator();
				while (i.hasNext())
				{
					Beweis tmpBWS = i.next();
					out.addAll(tmpBWS.getEbene(nummer-1));
				}
			}
		}
		else
		{
			out.add(this);
		}
		return out;
	}
	public void addEltern(Beweisliste neueEltern)
	{
		hatEltern = true;
		eltern = neueEltern;
	}
	public HashMap<Integer,Beweisebene> toMap()
	{
		HashMap<Integer,Beweisebene> out = new HashMap<Integer,Beweisebene>();
		for (int i = 0; i <= beweisTiefe(); i++)
		{
			out.put(i, getEbene(i));
		}
		return out;
	}
	public boolean hatEltern()
	{
		return hatEltern;
	}
	public Formelliste getFolg()
	{
		return folgerungen;
	}
}
