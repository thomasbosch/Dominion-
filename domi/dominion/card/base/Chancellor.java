package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 * 
 * +2 Pièces.
 * Vous pouvez immédiatement défausser votre deck.
 */
public class Chancellor extends ActionCard {

	public Chancellor() {
		super("Chancellor", 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(Player p) {
		p.incrementMoney(2);
		ArrayList<String> a= new ArrayList<String>();
		a.add("y");
		a.add("n");
		if(p.choose("defausse deck?", a, false) == "y"){
			p.defaussedeck();
		}
		
		
	}
	
}