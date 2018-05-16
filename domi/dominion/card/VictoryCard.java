package dominion.card;
import java.util.*;

import dominion.*;

/**
 * Les cartes Victoire
 */
public abstract class VictoryCard extends Card {

	public VictoryCard(String name, int cost) {
		super(name, cost);
	}
	
	public List<CardType> getTypes() {
		List<CardType> types= new ArrayList<CardType>();
		types.add(CardType.Victory);
		return types;
	}
	
}