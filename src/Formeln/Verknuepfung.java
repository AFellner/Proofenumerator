package Formeln;
import java.util.*;


public abstract class Verknuepfung extends Formel{
	protected Formel formel1;
	protected Formel formel2;
	protected char verknuepfung;
	public Verknuepfung(Formel formel1, Formel formel2)
	{
		this.formel1 = formel1;
		this.formel2 = formel2;
		this.variable = (ArrayList<Variable>) formel1.getVariable().clone();
		Iterator<Variable> i = formel2.getVariable().iterator();
		while (i.hasNext())
		{
			Variable tmpVar = i.next();
			if (!this.variable.contains(tmpVar))
			{
				this.variable.add(tmpVar);
			}
		}
	}
	public String toString()
	{
		return "("+formel1.toString()+" " + verknuepfung+" " + formel2.toString()+")";
	}
	
	public Formel getFormel1()
	{
		return formel1;
	}
	
	public Formel getFormel2()
	{
		return formel2;
	}
	
	public char getVerknuepfung()
	{
		return verknuepfung;
	}
	
	public boolean equals(Verknuepfung vergleich)
	{
		if (formel1.equals(vergleich.getFormel1()) && formel2.equals(vergleich.getFormel2()) && verknuepfung == vergleich.getVerknuepfung())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean equalStruct(Verknuepfung vergleich)
	{
		if (formel1.equalStruct(vergleich.getFormel1()) && formel2.equalStruct(vergleich.getFormel2()) && verknuepfung == vergleich.getVerknuepfung())
		{
			return true;
		}
		else
		{
			return false;
		}
	} 
	public ArrayList<Variable> getVariable()
	{
		return variable;
	}
}
