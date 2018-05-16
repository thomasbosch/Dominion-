package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Laboratoire (Laboratory)
 * 
 * +2 Cartes.
 * +1 Action.
 */
public class Laboratory extends ActionCard {

	public Laboratory(String name, int cost) {
		super("Laboratory", 5);

	}


	public void play(Player p) {
		p.drawCard();
		p.drawCard();
		p.incrementActions(1);
	}
}