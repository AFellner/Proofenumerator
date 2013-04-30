package Test;

import Formeln.*;
import Logik.*;
import java.util.Date;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Test6 extends Obertest{

	public static void main(String args[]) throws Exception
	{
		boolean calcFolgerungen = true;
		Variable v = var("V");
		Variable u = var("U");
		Formelliste vs = new Formelliste();
		Formelliste folg = new Formelliste();
		vs.add(imp(u,v));
		vs.add(u);
		folg.add(v);
		Beweis mp = new Beweis(vs,folg);
		Beweisliste moeglBW = new Beweisliste();
		moeglBW.add(mp);
		Kalkuel testKalk = new Kalkuel(moeglBW);
		testKalk.berechneBeweise(3);
		//System.out.println("voherher\n" + testKalk.getBeweise());
		//System.out.println("nachher\n"+ testKalk.getBeweise());
		Formelliste axiome = new Formelliste();
		Beweisliste bwliste = testKalk.getBeweise();
		System.out.println(bwliste);
		System.out.println(bwliste.size());
		Variable a = var("a");
		Variable b = var("b");
		Variable c = var("c");
		Formel ax1 = imp(a,imp(b,a));
		Formel ax2 = imp(imp(a,imp(b,c)),imp(imp(a,b),imp(a,c)));
		Formel ax3 = imp(imp(neg(a),neg(b)),imp(b,a));
		Formel ax4 = imp(imp(a,imp(b,c)),imp(b,imp(a,c)));
		Formel ax5 = imp(imp(a,b),imp(neg(b),neg(a)));
		Formel ax6 = imp(a,neg(neg(a)));
		Formel ax7 = imp(neg(neg(a)),a);
		axiome.add(ax1);
		axiome.add(ax2);
		axiome.add(ax3);
		//axiome.add(ax4);
		//axiome.add(ax5);
		//axiome.add(ax6);
		//axiome.add(ax7);
		//System.out.println(axiome);
		/*Beweisliste neueBeweisliste = new Beweisliste();
		neueBeweisliste.add(testKalk.getBeweise().get(22));
		testKalk.setBeweise(neueBeweisliste);
		System.out.println(testKalk.alleFolgerungen(axiome));
		*/
		if (calcFolgerungen)
		{
			long zeitvorher = System.currentTimeMillis();
			Formelliste alleSchlussfolgerungen = testKalk.alleFolgerungen(axiome);
			System.out.println(alleSchlussfolgerungen);
			System.out.println("Anzahl der Folgerungen: " + alleSchlussfolgerungen.size());
			long zeitnachher = System.currentTimeMillis();
			long zeit = zeitnachher-zeitvorher;
	        long stunden = zeit/(1000*60*60);
	        long minuten = (zeit -stunden*1000*60*60)/(1000*60);
	        long sekunden = (zeit-minuten*1000*60)/1000;
	        long millisek = (zeit-sekunden*1000);
	        System.out.println("stunden:minuten:sekunden:millisekunden");
	        System.out.println(stunden+":"+minuten+":"+sekunden+":"+millisek);
	        Formel t1 = imp(a,a);
	        Formel t2 = imp(a,neg(neg(a)));
	        Formel t3 = imp(neg(neg(a)),a);
	        Formel t4 = imp(imp(a,neg(a)),neg(a));
	        Formel t5 = imp(imp(neg(a),a),neg(neg(a)));
	        Formel t6 = imp(imp(neg(a),a),a);
	        Formel t7 = imp(imp(a,b),imp(neg(b),neg(a)));
	        Formel t8 = imp(a,imp(imp(a,b),b));
	        Formel t9 = imp(a,imp(imp(a,b),b));
	        Formel t10 = imp(imp(a,imp(b,c)),imp(b,imp(a,c)));
	        Formel t11 = imp(imp(a,b),imp(imp(b,c),imp(a,c)));
	        Formel t12 = imp(imp(imp(a,b),c),imp(imp(imp(b,a),c),c));
	        Formelliste testFormeln = new Formelliste();
	        testFormeln.add(t1);
	        testFormeln.add(t2);
	        testFormeln.add(t3);
	        testFormeln.add(t4);
	        testFormeln.add(t5);
	        testFormeln.add(t6);
	        testFormeln.add(t7);
	        testFormeln.add(t8);
	        testFormeln.add(t9);
	        testFormeln.add(t10);
	        testFormeln.add(t11);
	        testFormeln.add(t12);
	        Iterator<Formel> i = testFormeln.iterator();
	        while (i.hasNext())
	        {
	        	Formel tmpFormel = i.next();
	        	System.out.println("Formel: " + tmpFormel + " ist in den Schlussfolgerungen enthalten: >>" + alleSchlussfolgerungen.containsAequivalent(tmpFormel) + "<< bereits Axiom: " + axiome.containsAequivalent(tmpFormel));
	        }
	        
	        
		}
		
		System.out.println("getestete Axiome: " + axiome);
		
        //System.out.println(axiome);
        /*Beweis testBW2 = testKalk.getBeweise().get(6);
        System.out.println("Testbeweis: " + testBW2);
        Formelliste anw = new Formelliste();
        
        anw.add(ax2);
        anw.add(ax1);
        anw.add(ax1);
        Formelliste out = testBW2.anwenden(anw);
        System.out.println(anw);
        System.out.println("blablaout: " + out);*/
        /*Formelliste anw2 = new Formelliste();
        anw2.add(ax2);
        anw2.add(ax1);
        System.out.println(mp.anwenden(anw2));
        Formel bla = mp.anwenden(anw2).get(0).clone();
        Formelliste anw3 = new Formelliste();
        anw3.add(bla);
        anw3.add(ax1);
        System.out.println(mp.anwenden(anw3));*/
	}
}
