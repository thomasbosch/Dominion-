package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Jardins (Gardens)
 * 
 * Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi à l'unité inférieure).
 */
public class Gardens extends VictoryCard {

	public Gardens() {
		super("Garden", 4);
	}

	public void play(Player p) {
		System.out.println("Ntm tu t'es pris pour qui à jouer des cartes jardins ????!!!! >:(");
	}
	
	public int victoryValue(Player p) {
		return (p.totalCards().size()/10);
	}
}