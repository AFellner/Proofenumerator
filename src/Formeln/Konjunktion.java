package Formeln;
import Logik.*;

public class Konjunktion extends Verknuepfung{
	public Konjunktion(Formel formel1, Formel formel2)
	{
		super(formel1,formel2);
		this.verknuepfung = '&';
	}
	public Formel substituiere(Substitution subs)
	{
		return new Konjunktion(formel1.substituiere(subs),formel2.substituiere(subs));
	}
	public Konjunktion clone()
	{
		return new Konjunktion(formel1.clone(),formel2.clone());
	}
}
