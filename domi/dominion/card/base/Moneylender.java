package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;
import dominion.card.common.Copper;

/**
 * Carte Prêteur sur gages (Moneylender)
 * 
 * Écartez une carte Cuivre de votre main.
 * Dans ce cas, +3 Pièces.
 */
public class Moneylender extends ActionCard {

	public Moneylender() {
		super("Moneylender", 4);
		
		// TODO Auto-generated constructor stub
	}		public void play(Player p) {
		if(p.cardsInHand().getCard("Copper") instanceof Copper) {
			p.trashcard("Copper");
			p.incrementMoney(3);
		}
	}


}