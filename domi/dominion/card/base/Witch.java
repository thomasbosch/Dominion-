package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.common.Curse;

/**
 * Carte Sorcière (Witch)
 * 
 * +2 Cartes.
 * Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends AttackCard {

	public Witch() {
		super("Witch", 5);

	}
		public void play(Player p) {
		p.drawNCard(2);
		
		for (Player player:p.otherPlayers()){
			player.gain("Curse");
		}
	}
}