package dominion.card;
import java.util.*;

import dominion.*;

/**
 * Les cartes Malédiction
 */
public abstract class CurseCard extends Card {

	public CurseCard(String name, int cost) {
		super(name, cost);
	}
	
	public List<CardType> getTypes() {
		List<CardType> types= new ArrayList<CardType>();
		types.add(CardType.Curse);
		return types;
	}
	
}