package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Atelier (Workshop)
 * 
 * Recevez une carte coÃ»tant jusqu'Ã  4 PiÃ¨ces.
 */
public class Workshop extends ActionCard {
	public Workshop() {
		super("Workshop", 3);
	}
	
	public void play(Player p) {
		CardList c= p.getGame().availableSupplyCards();
		CardList cc= new CardList();
		Card ccc;
		while(!c.isEmpty()) {
			ccc=c.remove(0);
			if (ccc.getCost()<5) {
				cc.add(ccc);
			}
		}
			String m=p.chooseCard("Choisir?",cc,true);
			p.gain(m);
		}
}
