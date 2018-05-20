package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Mine
 * 
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends ActionCard {

	public Mine() {
		super("Mine", 5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(Player p) {
		CardList cl=new CardList();
		for(Card card: p.cardsInHand()) {
			if(card.getTypes().contains(CardType.Treasure)) {
				cl.add(card);
			}
		}
		
		
		
		String m=p.chooseCard("Ecarte!",cl,false);
		Card card=p.trashcard(m);
		int i=card.getCost();
		
		

		CardList ccll=new CardList();
		for(Card carde: p.getGame().availableSupplyCards()) {
			if(carde.getTypes().contains(CardType.Treasure)&& carde.getCost()<i+4 ){
				ccll.add(carde);
			}
		}
		m=p.chooseCard("Choisir!",ccll,false);
			p.gaininhand(m);
		}

		
}