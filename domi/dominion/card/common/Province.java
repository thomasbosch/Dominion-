package dominion.card.common;
import dominion.*;
import dominion.card.*;

/**
 * Carte Province
 * 
 * 6 VP
 */
public class Province extends VictoryCard {
	public Province() { super("Province", 8);	}
	
	public int victoryValue(Player p) {
		return 6;
	}
}