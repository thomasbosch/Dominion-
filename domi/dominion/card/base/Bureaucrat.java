package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bureaucrate (Bureaucrat)
 * 
 * Recevez une carte Argent; placez-la sur votre deck.
 * Tous vos adversaires dévoilent une carte Victoire et la placent sur leur deck (sinon ils dévoilent leur main afin que vous puissiez voir qu'ils n'ont pas de cartes Victoire).
 */
public class Bureaucrat extends AttackCard {

	public Bureaucrat() {
		super("Bureaucrat", 4);
			}

	@Override
	public void play(Player p) {
		p.gaindeck("Silver");
		
		for(Player player:p.otherPlayers()) {



				if(player.getVictoryCards().size()==0) {

							player.revealdeck();
					}else {
	
					String m=player.chooseCard("bbbbbbbbbbbbuu", player.getVictoryCards(), false);
					player.putondeck(m);

				}

		}
	}
				
}