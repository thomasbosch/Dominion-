package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 * 
 * +1 Action.
 * DÃ©faussez autant de cartes que vous voulez.
 * +1 Carte par carte dÃ©faussÃ©e.
 */
public class Cellar extends ActionCard {
	public Cellar() {
		super("Cellar", 2);
	}
	
	public void play(Player p) {
		p.incrementActions(1);
		
		String m="m";
		int i=0;
		
		while(!m.equals("")) {
		m=p.chooseCard("defausse?",p.cardsInHand(),true);
			if(!m.equals("")) {
				p.discardCard(p.cardsInHand().getCard(m));
				i++;
		
			}
		}
		p.drawNCard(i);
		}
}
