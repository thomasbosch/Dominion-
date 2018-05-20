package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dévoilent les 2 premières cartes de leur deck. S'ils dévoilent des cartes Trésor, ils en écartent 1 de votre choix. Parmi ces cartes Trésor écartées, recevez celles de votre choix. Les autres cartes dévoilées sont défaussées.
 */
public class Thief extends AttackCard {

	public Thief() {
		super("Thief", 4);
		
	}

	public void play(Player p, List<Player> victimes) {
		
		CardList TreasureCards=new CardList();
		
		
		for(Player player:victimes){//Pour chaque joueur
			
			//System.out.println( "\n\n\nInfos joueurs :\nNom : "+player.getName()+"\nDraw : "+ player.getDraw().toString()+"\nDiscard : "+ player.getDiscard().toString()+"\n\n\n");
			
			CardList playerCards=new CardList();
			
			for(int i=0;i<2;i++) {//On prend les deux premières cartes de sa pioche
				
				Card c=player.drawCard();
				
				System.out.println(c.toString());//On les révèle
				
				if(c.getTypes().contains(CardType.Treasure)) {//Si elles contiennet des cartes trésor
					playerCards.add(c);//On  les ajoute dans une liste temporaire
					player.removeFromHand(c);
					}
				else {
					player.discardCard(c);
				}

				
				}
			
			if(playerCards.size()>0) {//Si elles contient des cartes trésor
				String choice=p.chooseCard("Quel carte écarter ?", playerCards, false);//On demande laquelle écarter
				
				System.out.println(" Carte à écarter choisise : "+choice);
				
				Card c=playerCards.getCard(choice);// On récupère la carte
				playerCards.remove(c);
				
				//CODE SALE 
				//NE PAS HESITER A NETTOYER TOUT CA HOP HOP HOP
				
				if(playerCards.size()>0) {
				player.discardCard(playerCards.get(0));
				}
				
				//FIN CODE SALE
				
				playerCards.clear();

				TreasureCards.add(c);
				}
			
			//System.out.println( "\n\n\nInfos joueurs :\nNom : "+player.getName()+"\nDraw : "+ player.getDraw().toString()+"\nDiscard : "+ player.getDiscard().toString()+"\n\n\n");
			}
		
		String choice="aaaaaa";	
		
			while(!choice.equals("") && TreasureCards.size()>0) {
				
			choice=p.chooseCard("Quel carte garder ?", TreasureCards, true);//On demande laquelle garder
			
			System.out.println("Carte choisise "+choice);
			System.out.println(TreasureCards.toString());
			p.gain(TreasureCards.getCard(choice));
			System.out.println(TreasureCards.toString());
			TreasureCards.remove(TreasureCards.getCard(choice));
			}
			
			System.out.println("Oué on arrive à la fin");
		}

	@Override
	public void play(Player p) {
		// TODO Auto-generated method stub
		
	}
	}
