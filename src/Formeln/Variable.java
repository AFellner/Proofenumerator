package Formeln;
import java.util.ArrayList;

import Logik.*;


public class Variable extends Formel{
	private String name;
	
	public Variable(String name)
	{
		this.name = name;
		variable = new ArrayList<Variable>();
		variable.add(this);
	}
	
	public String toString()
	{
		return this.name;
	}
	public String getName()
	{
		return name;
	}
	
	public boolean equals(Variable vergleich)
	{
		if (this.name.equals(vergleich.getName()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean equalStruct(Variable vergleich)
	{
		return true;
	}
	
	
	public Formel substituiere(Substitution subs)
	{
		if (subs.containsKey(this))
		{
			Variable aktKey = this;
			Formel toMap = subs.get(aktKey);
			while(subs.containsKey(toMap))
			{
				aktKey = (Variable) toMap;
				toMap = subs.get(aktKey);
			}
			return subs.get(aktKey);
		}
		else
		{
			return this;
		}
	}
	public Variable clone()
	{
		return new Variable(name+"'");
	}
}
