package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Festin (Feast)
 * 
 * Écartez cette carte.
 * Recevez une carte coûtant jusqu'à 5 Pièces.
 */
public class Feast extends ActionCard {

	public Feast() {
		super("Feast", 4);
		// TODO Auto-generated constructor stub
	}

	@Override
		public void play(Player p) {
			CardList c= p.getGame().availableSupplyCards();
			CardList cc= new CardList();
			Card ccc;
			while(!c.isEmpty()) {
				ccc=c.remove(0);
				if (ccc.getCost()<6) {
					cc.add(ccc);
				}
			}
				String m=p.chooseCard("Choisir?",cc,true);
				p.gain(m);
				
				p.trashcardinPlay("Feast");
			}
	
	
		
}