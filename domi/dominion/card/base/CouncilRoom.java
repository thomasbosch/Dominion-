package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chambre du conseil (Council Room)
 * 
 * +4 Cartes.
 * +1 Achat.
 * Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends ActionCard {

	public CouncilRoom() {
		super("Council Room", 5);
		// TODO Auto-generated constructor stub
	}


	public void play(Player p) {
		p.incrementBuys(1);
		p.drawNCard(4);
		
		for (Player player:p.otherPlayers()){
			player.drawCard();
		}

	}
}