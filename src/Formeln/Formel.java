package Formeln;

import java.util.ArrayList;
import java.util.Iterator;

import Logik.*;

public abstract class Formel {
	ArrayList<Variable> variable;
	public boolean equals(Formel vergleich)
	{
		if (this.getClass().equals(vergleich.getClass()))
		{
			if (this instanceof Variable)
			{
				Variable t = (Variable) this;
				return t.equals((Variable)vergleich);
			}
			else if (this instanceof Negation)
			{
				Negation t = (Negation) this;
				return t.equals((Negation)vergleich);
			}
			else if (this instanceof Verknuepfung)
			{
				Verknuepfung t = (Verknuepfung) this;
				return t.equals((Verknuepfung)vergleich);
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean isAequivalent(Formel vergleich)
	{
		if (!equalStruct(vergleich))
		{
			return false;
		}
		else
		{
			Substitution unif = Unifikator.unifiziere(this,vergleich);
			if (unif == null || !unif.istUmbenennung())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		
	}
	public boolean equalStruct(Formel vergleich)
	{
		if (this.getClass().equals(vergleich.getClass()))
		{
			if (this instanceof Variable)
			{
				return true;
			}
			else if (this instanceof Negation)
			{
				Negation t = (Negation) this;
				return t.equalStruct((Negation)vergleich);
			}
			else if (this instanceof Verknuepfung)
			{
				Verknuepfung t = (Verknuepfung) this;
				return t.equalStruct((Verknuepfung)vergleich);
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	public abstract Formel substituiere(Substitution subs);

	public boolean contains(Variable var)
	{
		return variable.contains(var);
	}

	public ArrayList<Variable> getVariable()
	{
		return variable;
	}
	
	public abstract Formel clone();
}
