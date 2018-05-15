package dominion.card;
import java.util.*;

import dominion.*;

/**
 * Les cartes Action
 */
public abstract class ActionCard extends Card {

	public ActionCard(String name, int cost) {
		super(name, cost);
	}
	
	public List<CardType> getTypes() {
		List<CardType> types= new ArrayList<CardType>();
		types.add(CardType.Action);
		return types;
	}
	
}