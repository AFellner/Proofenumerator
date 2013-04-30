package Logik;
import java.util.ArrayList;
import Formeln.*;

public class Unifikator {
	private static boolean debug = false;
	/*
	 * Vorraussetzung an diese Funktion ist eine Formelliste mit sich nicht überschneidenden Variablen
	 */
	public static Substitution unifiziere(Formelliste formeln)
	{
		Substitution unifikator = new Substitution();
		Substitution aktSubstitution = new Substitution();
		Formel formel1;
		Formel formel2;
		while (formeln.size()>1)
		{
			formel1 = formeln.get(0);
			formel2 = formeln.get(1);
			aktSubstitution = unifiziere(formel1,formel2);
			if (aktSubstitution != null)
			{
				formeln.remove(formel1);
				formeln.remove(formel2);
				formeln.add(formel1.substituiere(aktSubstitution));
				unifikator = unifikator.merge(aktSubstitution);
			}
			else
			{
				unifikator = null;
				break;
			}
		}
		return unifikator;
	}
	public static Substitution unifiziere(Formel formel1,Formel formel2)
	{
		Substitution unifikator = new Substitution();
		//Eine der beiden Formeln ist Variable
		if (debug)
		{
			System.out.println("Unifiziere " + formel2 + " und " + formel1);
		}
		if (!(formel1.equals(formel2)))
		{
			
			if (formel1 instanceof Variable)
			{
				if (formel2.contains((Variable)formel1))
				{
					unifikator = null;
					if (debug)
					{
						System.out.println(formel2 + " enthaelt " + formel1 + " -> unifizieren unmöglich");
					}
				}
				else
				{
					unifikator.put((Variable)formel1,formel2);
				}
				
			}
			else if
			(formel2 instanceof Variable)
			{
				if (formel1.contains((Variable)formel2))
				{
					unifikator = null;
					if (debug)
					{
						System.out.println(formel1 + " enthaelt " + formel2 + " -> unifizieren unmöglich");
					}
				}
				else
				{
					unifikator.put((Variable)formel2,formel1);
				}
				
			}
			//Beide Formeln sind keine Variable
			else
			{
				//Formeln haben unterschiedlichen Typ (z.B. Konjunktion und Disjunktion)
				if (!(formel1.getClass().equals(formel2.getClass())))
				{
					unifikator = null;
					if (debug)
					{
						System.out.println(formel1 + "("+formel1.getClass().toString() + ") und " + formel2 + "("+formel2.getClass().toString() + ") -> unifizieren unmöglich");
					}
				}
				else
				{
					if (formel1 instanceof Negation)
					{
						Formel neg1 = ((Negation) formel1).getNeg();
						Formel neg2 = ((Negation) formel2).getNeg();
						unifikator = unifiziere(neg1,neg2);
					}
					else
					{
						Formel f11 = ((Verknuepfung) formel1).getFormel1();
						Formel f12 = ((Verknuepfung) formel1).getFormel2();
						Formel f21 = ((Verknuepfung) formel2).getFormel1();
						Formel f22 = ((Verknuepfung) formel2).getFormel2();
						unifikator = unifiziere(f11,f21);
						if (unifikator != null)
						{
							f12 = f12.substituiere(unifikator);
							f22 = f22.substituiere(unifikator);
							Substitution tmpUnif = unifiziere(f12,f22);
							if (tmpUnif != null){
								unifikator = unifikator.merge(tmpUnif);
							}
							else
							{
								unifikator = null;
							}
						}
						
					}
				}
			}
		}
		return unifikator;
	}
}
