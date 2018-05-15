package dominion.card;
import java.util.*;

import dominion.*;

/**
 * Les cartes Trésor
 */
public abstract class TreasureCard extends Card {

	public TreasureCard(String name, int cost) {
		super(name, cost);
	}
	
	public List<CardType> getTypes() {
		List<CardType> types= new ArrayList<CardType>();
		types.add(CardType.Treasure);
		return types;
	}
}