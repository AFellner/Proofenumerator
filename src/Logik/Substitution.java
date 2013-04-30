package Logik;
import java.util.*;
import Formeln.*;


public class Substitution extends HashMap<Variable,Formel>{
	private boolean debug = false;
	private static final long serialVersionUID = 1L;
	
	public boolean istUmbenennung()
	{
		Set<Variable> variable = this.keySet();
		HashSet<Variable> vorgekommen = new HashSet<Variable>();
		vorgekommen.addAll(variable);
		Iterator<Variable> i = variable.iterator();
		while (i.hasNext())
		{
			Variable tmpVar = i.next();
			if (!(this.get(tmpVar) instanceof Variable))
			{
				return false;
			}
			else
			{
				if (vorgekommen.contains((Variable) this.get(tmpVar)))
				{
					return false;
				}
				else
				{
					vorgekommen.add((Variable) this.get(tmpVar));
				}
			}
		}
		return true;
	}
	
	public Substitution merge(Substitution withThis)
	{
		Substitution out = (Substitution) this.clone();
		Set<Variable> keys = withThis.keySet();
		Iterator<Variable> i = keys.iterator();
		while (i.hasNext())
		{
			Variable aktVar = i.next();
			if (out.containsKey(aktVar))
			{
				Formel subs1 = out.get(aktVar);
				Formel subs2 = withThis.get(aktVar);
				if (debug) System.out.println("Beider Subsitutionen enthalten Zuordnung fuer " + aktVar + " und zwar: " + subs1 + " und " + subs2);
				if (!(subs1.equals(subs2)))
				{
					Substitution unif = Unifikator.unifiziere(subs1,subs2);
					if (debug) System.out.println("Fuer diese zwei Formeln wurde Unifikator gefunden: " + unif);
					if (unif == null)
					{
						return null;
					}
					else
					{
						Set<Variable> tmpKeys = out.keySet();
						Iterator<Variable> j = tmpKeys.iterator();
						while (j.hasNext())
						{
							Variable tmpVar = j.next();
							if (debug) System.out.println("Ersetze Substitution fuer " + tmpVar + " von " + out.get(tmpVar) + " zu " +  out.get(tmpVar).substituiere(unif) + " unifikator: " + unif);
							if (debug)
							{
								Set<Variable> unifVar = unif.keySet();
								Iterator<Variable> u = unifVar.iterator();
								System.out.println("Die Formel " + out.get(tmpVar) + " enthaelt folgende Variable: " + out.get(tmpVar).getVariable());
								while (u.hasNext())
								{
									Variable uTmpVar = u.next();
									System.out.println("Die Formel " + out.get(tmpVar) + " enthaelt " + uTmpVar + out.get(tmpVar).contains(uTmpVar));
								}
							}
							out.put(tmpVar, out.get(tmpVar).substituiere(unif));
						}
					}
				}
			}
			else
			{
				out.put(aktVar, withThis.get(aktVar));
			}
		}
		return out;
	}
}
