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
			
			
			for(int i=0;i<4;i++) {
			String m=p.chooseCard("Quel carte écarter ?",p.cardsInHand(),true);
			
			System.out.println("Carte écarté choisie : "+m);
			if(m.equals("")) {
				break;
			}
			
			if(p.cardsInHand().getCard(m)!=null) {
				
			p.trashCard(p.cardsInHand().getCard(m));
			}
			}
			
	}
}
