package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Douves (Moat)
 * 
 * +2 Cartes.
 * Lorsqu’un adversaire joue une carte Attaque, vous pouvez dévoiler cette carte de votre main. Dans ce cas, l’Attaque n’a pas d’effet sur vous.
 */
public class Moat extends ReactionCard {

	public Moat() {
		super("Moat", 2);
	}


	public void play(Player p) {
	p.drawNCard(2);
	}
	
	public boolean react(Player p) {
		
		List<String> choices=new ArrayList<String>();
		choices.add("y");
		choices.add("n");
		
		boolean react=false;
		
		String choice=p.choose("Esquiver l'attaque ?", choices, false);
		
		if(choice.equals("y")) {
			react=true;
		}
		System.out.println("\n\n\n REACT CHECKED value : "+react+"\n\n\n");
		return react;
	}


	public void play(Player p, List<Player> aP) {

	}


}