package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Aventurier (Adventurer)
 * 
 * Dévoilez des cartes de votre deck jusqu'à ce que 2 cartes Trésor soient dévoilées. Ajoutez ces cartes Trésor à votre main et défaussez les autres cartes dévoilées.
 */
public class Adventurer extends ActionCard {

	public Adventurer() {
		super("Adventurer",6);
	}

	public void play(Player p) {
		
		int nbTreasureCards=0;
		int i=0;
		
		while(nbTreasureCards<2) {
			Card c=p.drawCard();
			System.out.println(c.toString());
			if(c.getTypes().contains(CardType.Treasure)) {
				nbTreasureCards++;
			}else {
				p.discardCard(c);
			}

		}
		
	}
}