package Formeln;

import Logik.*;
import java.util.ArrayList;

public class Negation extends Formel{
	Formel negierteFormel;
	public Negation(Formel negierteFormel)
	{
		this.negierteFormel = negierteFormel;
		variable = negierteFormel.getVariable();
	}
	public String toString()
	{
		return "-("+negierteFormel.toString()+")";
	}

	public Formel getNeg()
	{
		return negierteFormel;
	}
	
	public boolean equals(Negation vergleich)
	{
		return negierteFormel.equals(vergleich.getNeg());
	}
	public boolean equalStruct(Negation vergleich)
	{
		return negierteFormel.equalStruct(vergleich.getNeg());
	}
	public Formel substituiere(Substitution subs)
	{
		return new Negation(negierteFormel.substituiere(subs));
	}

	public Negation clone()
	{
		return new Negation(negierteFormel.clone());
	}
}
