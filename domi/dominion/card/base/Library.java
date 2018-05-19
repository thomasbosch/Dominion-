package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bibliothèque (Library)
 * 
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. Chaque carte Action piochée peut être mise de côté. Défaussez les cartes mises de côté lorsque vous avez terminé de piocher.
 */
public class Library extends ActionCard {
	

	public Library() {
		super("Library", 5);
	}

	public void play(Player p) {
		List<String> choices=new ArrayList<String>();
		choices.add("y");
		choices.add("n");
		while(p.cardsInHand().size()<7){
			Card c=p.drawCard();
			if(c.getTypes().contains(CardType.Action)){
			String response=p.choose("La carte piochée est de type action, la défausser ?", choices,false);
			
				if(response.equals("y")) {
					p.discardCard(c);
				}
		}
		}
	}
}