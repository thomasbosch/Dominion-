package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 Pièces.
 * Tous vos adversaires défaussent leurs cartes de façon à n'avoir que 3 cartes en main.
 */
public  class Militia extends AttackCard {

	public Militia() {
		super("Militia", 4);
		// TODO Auto-generated constructor stub
	}


	public void play(Player p) {

	}


	public void play(Player p, List<Player> victimes) {
		p.incrementMoney(2);
		
		for (Player player:victimes){
			while(player.cardsInHand().size()>3) {
				String m=player.chooseCard("defausse!", player.cardsInHand(), false);
				player.discardCard(player.cardsInHand().getCard(m));
			}
		}
		
	}
}