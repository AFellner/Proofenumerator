package Formeln;
import Logik.*;

public class Disjunktion extends Verknuepfung{
	public Disjunktion(Formel formel1, Formel formel2)
	{
		super(formel1,formel2);
		this.verknuepfung = '|';
	}
	public Formel substituiere(Substitution subs)
	{
		return new Disjunktion(formel1.substituiere(subs),formel2.substituiere(subs));
	}
	
	public Disjunktion clone()
	{
		return new Disjunktion(formel1.clone(),formel2.clone());
	}
}
