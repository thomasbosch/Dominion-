package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Ã‰cartez jusqu'Ã  4 cartes de votre main.
 */
public class Chapel extends ActionCard {
	
	public Chapel() {
		super("Chapel", 2);
	}

	public void play(Player p) {
			String m;
			for(int i=0;i<4;i++) {
			m=p.chooseCard("ecarte?",p.cardsInHand(),true);
			if(m.equals("")) {
				i=4;
			}
			p.trashcard(m);
		
			}
	}
}
