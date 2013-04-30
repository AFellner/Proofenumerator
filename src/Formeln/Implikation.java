package Formeln;
import Logik.*;

public class Implikation extends Verknuepfung{
	public Implikation(Formel formel1, Formel formel2)
	{
		super(formel1,formel2);
		this.verknuepfung = '>';
	}
	public Formel substituiere(Substitution subs)
	{
		return new Implikation(formel1.substituiere(subs),formel2.substituiere(subs));
	}
	public Implikation clone()
	{
		return new Implikation(formel1.clone(),formel2.clone());
	}
}
