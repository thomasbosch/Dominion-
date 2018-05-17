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
	
	public Chapel(String name, int cost) {
		super("Chapel", 2);
	}

	public void play(Player p) {
			String m="m";
			int i=4;
			while(!m.equals("")&&i>0) {
			m=p.chooseCard("defausse?",p.cardsInHand(),true);
			p.discard("m");
			i--;
			}
	}
}
