package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Rénovation (Remodel)
 * 
 * Écartez une carte de votre main.
 * Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.
 */
public class Remodel extends ActionCard {

	public Remodel() {
		super("Remodel", 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(Player p) {
		String m=p.chooseCard("Ecarte!",p.cardsInHand(),false);
		Card card=p.trashcard(m);
		int i=card.getCost();
		
		
		CardList c= p.getGame().availableSupplyCards();
		CardList cc= new CardList();
		Card ccc;
		while(!c.isEmpty()) {
			ccc=c.remove(0);
			if (ccc.getCost()<i+3) {
				cc.add(ccc);
			}
		}
		m=p.chooseCard("Choisir?",cc,true);
			p.gain(m);
		}
		
}