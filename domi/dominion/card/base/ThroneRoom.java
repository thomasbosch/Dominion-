package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Salle du trône (Throne Room)
 * 
 * Choisissez 1 carte Action de votre main.
 * Jouez-la deux fois.
 */
public class ThroneRoom extends ActionCard {

	public ThroneRoom() {
		super("Throne Room", 4);
	}

	
	public void play(Player p) {
		String cString=p.chooseCard("Choisissez une carte de votre main", p.cardsInHand(), false);
		Card c=p.cardsInHand().getCard(cString);
		c.play(p);
		p.playCard(c);

	}

}