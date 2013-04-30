package Test;

import java.util.ArrayList;
import java.util.Iterator;

import Formeln.Formelliste;
import Formeln.Variable;
import Logik.*;

public class Test5 extends Obertest{
	
	public static void main(String args[])
	{
		Variable v = var("V");
		Variable u = var("U");
		Formelliste vs = new Formelliste();
		Formelliste folg = new Formelliste();
		vs.add(imp(u,v));
		vs.add(u);
		folg.add(v);
		Beweis mp = new Beweis(vs,folg);
		Beweisliste moeglBW = new Beweisliste();
		moeglBW.add(mp.clone());
		Beweisliste eltern = new Beweisliste();
		eltern.add(mp.clone());
		eltern.add(mp.clone());
		mp.addEltern(eltern);
		//System.out.println(mp);
		
		//System.out.println(mp.getObersteEbene(moeglBW));
		//System.out.println(mp.hatEltern());
		Kalkuel testkalk = new Kalkuel(moeglBW);
		//System.out.println(testkalk.beweiseMitTiefe(0));
		//System.out.println(testkalk.getBeweise());
		testkalk.berechneEineEbene();
		//testkalk.berechneEineEbene();
		//testkalk.berechneEineEbene();
		//System.out.println(testkalk.beweiseMitTiefe(1));
		/*Beweisliste bws = testkalk.getBeweise();
		Iterator<Beweis> i = bws.iterator();
		while (i.hasNext())
		{
			System.out.println(i.next());
			System.out.println("**************");
		}*/
		Beweis testbw = testkalk.getBeweise().get(7);
		Beweisebene testebene = testbw.getObersteEbene();
		ArrayList<Beweisebene> testebeneDrauf = testebene.moeglicheEltern(moeglBW);
		//System.out.println(testebene);
		Iterator<Beweisebene> k = testebeneDrauf.iterator();
		//System.out.println(testbw);
		Beweisebene eineMehr = new Beweisebene();
		eineMehr.add(mp.clone());
		eineMehr.add(mp.clone());
		eineMehr.add(mp.clone());
		eineMehr.add(mp.clone());
		testebene.connect(eineMehr);
		//System.out.println(testbw);
		/*while (k.hasNext())
		{
			Beweisebene tmp = k.next();
			testebene.connect(tmp);
			//System.out.println(k.next());
			//System.out.println("~~~~~~~~~~~~~");
		}*/
		//System.out.println(testkalk.getBeweise().get(7).toMap());
		//testkalk.berechneEineEbene();
		//System.out.println(testkalk.getBeweise().size());
		//System.out.println(testbw.toMap().get(2));
		Beweis nullteEbene = new Beweis(vs.clone(),folg.clone());
		Beweis ersteEbene1 = new Beweis(vs.clone(),folg.clone());
		Beweis ersteEbene2 = new Beweis(vs.clone(),folg.clone());
		Beweisebene ersteEbene = new Beweisebene();
		ersteEbene.add(ersteEbene1);
		ersteEbene.add(ersteEbene2);
		nullteEbene.addEltern(ersteEbene);
		Beweisebene zweiteEbene = new Beweisebene();
		Beweis dummy = new Beweis(folg.clone(),folg.clone());
		zweiteEbene.add(dummy.clone());
		zweiteEbene.add(dummy.clone());
		zweiteEbene.add(dummy.clone());
		zweiteEbene.add(dummy.clone());
		ersteEbene.connect(zweiteEbene);
		System.out.println(nullteEbene);
		Beweis testBW = new Beweis(vs.clone(),folg.clone());
		System.out.println(mp);
		System.out.println("blablabla");
		System.out.println(mp.clone());
		//System.out.println(folg.clone());
	}
}
