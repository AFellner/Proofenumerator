package Test;

import Formeln.*;
import Logik.*;

import java.util.Date;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Test9 extends Obertest{

	public static void main(String args[]) throws Exception
	{
		boolean calcFolgerungen = true;
		Variable v = var("V");
		Variable u = var("U");
		Variable a = var("a");
		Variable b = var("b");
		Variable c = var("c");
		Formelliste vs = new Formelliste();
		Formelliste folg = new Formelliste();
		vs.add(imp(u,v));
		vs.add(u);
		folg.add(v);
		Beweis mp = new Beweis(vs,folg);
		Beweisliste moeglBW = new Beweisliste();
		moeglBW.add(mp);
		Kalkuel testKalk = new Kalkuel(moeglBW);
		Formel ax1 = imp(a,imp(b,a));
		Formel ax2 = imp(imp(a,imp(b,c)),imp(imp(a,b),imp(a,c)));
		Formelliste axiome = new Formelliste();
		axiome.add(ax1);
		axiome.add(ax2);
		testKalk.berechneEineEbene();
		System.out.println(testKalk.getBeweise());
		Formelliste schlussfolg = testKalk.alleFolgerungen(axiome);
		System.out.println(schlussfolg);
		System.out.println(schlussfolg.size());
		/*Formel ax3 = schlussfolg.get(3);
		Formel ax4 = schlussfolg.get(2);
		Formel ax5 = schlussfolg.get(5);
		Formel ax6 = schlussfolg.get(4);
		System.out.println("ax3: " + ax3);
		System.out.println("ax4: " + ax4);
		System.out.println("ax5: " + ax5);
		System.out.println("ax6: " + ax6);
		Formel[] tmp = {new Variable("X")};
		Beweis dummy = new Beweis(tmp,tmp);
		Beweisliste eltern = new Beweisliste();
		eltern.add(dummy);
		eltern.add(mp.clone());
		mp.addEltern(eltern);
		System.out.println(mp);
		Formelliste tmpAxiome = new Formelliste();
		tmpAxiome.add(ax2);
		tmpAxiome.add(ax2);
		tmpAxiome.add(ax1);
		System.out.println(mp.anwenden(tmpAxiome));*/
	}
}
