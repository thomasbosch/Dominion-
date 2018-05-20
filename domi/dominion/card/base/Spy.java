package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Espion (Spy)
 * 
 * +1 Carte.
 * +1 Action.
 * Tous les joueurs (vous aussi) dévoilent la première carte de leur deck. Vous décidez ensuite si chaque carte dévoilée est défaussée ou replacée sur son deck.
 */
public class Spy extends AttackCard {

	public Spy() {
		super("Spy", 4);
	}

	public void play(Player p) {


		
	}

	
	public void play(Player p, List<Player> aP) {
		p.drawCard();
		p.incrementActions(1);
		
		List<String> choices=new ArrayList<String>();
		choices.add("y");
		choices.add("n");	
		
		
		Card c=p.drawCard();
		
		String choice=p.choose("Défausser la carte "+c.getName()+" ?", choices, false);
		
		if(choice.equals("y")) {
			p.discardCard(c);
		}else{
			p.cardHandToTopDraw(c);
		}
		

		
		for(Player pL:aP){
			
			Card card=pL.drawCard();
			
			String choicePL=p.choose("Défausser la carte "+c.getName()+" ?", choices, false);
			if(choicePL.equals("y")) {
				pL.discardCard(card);
			}else{
				pL.cardHandToTopDraw(card);
			}
			
		}
		
		
		
	}
	
}