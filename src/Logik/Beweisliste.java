package Logik;

import java.util.ArrayList;
import java.util.Iterator;

public class Beweisliste extends ArrayList<Beweis>{
	public String toString()
	{
		String out = "";
		Iterator<Beweis> i = this.iterator();
		while (i.hasNext())
		{
			out += (i.next().toString());
		}
		return out;
	}
}
