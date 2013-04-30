package Formeln;

import java.util.ArrayList;
import java.util.Iterator;

public class Formelliste extends ArrayList<Formel>
{
	public Formelliste(Formel[] in)
	{
		super();
		for (int i = 0; i < in.length; i++)
		{
			this.add(in[i]);
		}
	}
	
	public Formelliste()
	{
		super();
	}
	public boolean addNew(Formel toAdd)
	{
		if (!this.containsAequivalent(toAdd))
		{
			this.add(toAdd);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void loescheAequivalente()
	{
		for (int i = 0; i < size(); i++)
		{
			Formel aktFormel = get(i);
			for (int j = i+1; j < size(); j++)
			{
				if (aktFormel.isAequivalent(get(j)))
				{
					remove(j);
				}
			}
		}
	}
	
	public boolean containsAequivalent(Formel vergleich)
	{
		Iterator<Formel> i = this.iterator();
		while (i.hasNext())
		{
			if (vergleich.isAequivalent(i.next())){
				return true;
			}
		}
		return false;
	}
	
	public Formelliste subList(int from, int to)
	{
		Formelliste out = new Formelliste();
		for (int i = from; i < to; i++)
		{
			out.add(this.get(i));
		}
		
		return out;
	}
	public Formelliste clone()
	{
		Formelliste neueListe = new Formelliste();
		neueListe.addAll(this);
		return neueListe;
	}
	
	public boolean contains(Formel diese)
	{
		boolean out = false;
		Iterator<Formel> i= this.iterator();
		while (i.hasNext())
		{
			if (diese.equals(i.next()))
			{
				out = true;
				break;
			}
		}
		return out;
	}
}
