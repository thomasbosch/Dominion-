 package dominion;
import java.util.*;

import dominion.card.*;
import dominion.card.common.Copper;
import dominion.card.common.Estate;

//Nina hagen yeah

/**
 * Un joueur de Dominion
 */
public class Player {
	/**
	 * Nom du joueur
	 */
	private String name;
	
	/**
	 * Nombre d'actions disponibles
	 */
	private int actions;
	
	/**
	 * Nombre de piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ces disponibles pour acheter des cartes
	 */
	private int money;
	
	/**
	 * Nombre d'achats disponibles
	 */
	private int buys;
	
	/**
	 * RÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©rence vers la partie en cours
	 */
	private Game game;
	
	/**
	 * Liste des cartes dans la main du joueur
	 */
	private CardList hand;
	
	/**
	 * Liste des cartes dans la dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fausse du joueur
	 */
	private CardList discard;
	
	/**
	 * Liste des cartes dans la pioche du joueur
	 */
	private CardList draw;
	
	/**
	 * Listes des cartes qui ont ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© jouÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es pendant le tour courant
	 */
	private CardList inPlay;
	
	/**
	 * Constructeur
	 * 
	 * Initialise les diffÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©rentes piles de cartes du joueur, place 3 cartes
	 * Estate et 7 cartes Copper dans la dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fausse du joueur puis fait piocher 5
	 * cartes en main au joueur.
	 * 
	 * @param name: le nom du joueur
	 * @param game: le jeu en cours
	 * 
	 * Indications: On peut utiliser la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode {@code this.endTurn()} pour 
	 * prÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©parer la main du joueur aprÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨s avoir placÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© les cartes dans la dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fausse.
	 */
	public Player(String name, Game game) {
		this.name=name;
		this.actions=1;
		this.buys=1;
		this.game=game;
		this.money=0;
		
		this.draw= new CardList();
		for (int i=0;i<7;i++){
			this.draw.add(new Copper());
		}
		
		for (int i=0;i<3;i++){
			this.draw.add(new Estate());
		}
		this.discard= new CardList();
		this.inPlay= new CardList();
		this.hand= new CardList();
		this.draw.shuffle();
		
		for(int i=0;i<5;i++){
		this.hand.add(this.draw.get(0));
		this.draw.remove(0);
		}
	}

	/**
	 * Getters et setters
	 */
	public String getName() {
		return this.name;
	}
	
	public int getActions() {
		return this.actions;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public int getBuys() {
		return this.buys;
	}
	
	public Game getGame() {
		return this.game;
	}
	public void discard (String cardName) {
		
		Card c=this.hand.remove(cardName);
		this.discard.add(c);
	}
	
	/**
	 * IncrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©mente le nombre d'actions du joueur
	 * 
	 * @param n nombre d'actions ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  ajouter (ce nombre peut ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Âªtre nÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©gatif si l'on
	 * souhaite diminuer le nombre d'actions)
	 */
	public void incrementActions(int n) {
		if ((this.actions==0 && n>=0)||this.actions>0){
			this.actions+=n;
			if(this.actions<0){
				this.actions=0;
				}
		} 
	}
	
	/**
	 * IncrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©mente le nombre de piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ces du joueur
	 * 
	 * @param n nombre de piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ces ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  ajouter (ce nombre peut ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Âªtre nÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©gatif si l'on
	 * souhaite diminuer le nombre de piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ces)
	 */
	public void incrementMoney(int n) {
		this.money=this.money+n;
		if (this.money<0) {
			this.money=0;}
	}
	
	/**
	 * IncrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©mente le nombre d'achats disponibles du joueur
	 * 
	 * @param n nombre d'achats ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  ajouter (ce nombre peut ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Âªtre nÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©gatif si l'on
	 * souhaite diminuer le nombre d'achats)
	 */
	public void incrementBuys(int n) {
		this.buys=this.buys+n;
		if (this.buys<0) {
			this.buys=0;}
	}

	/**
	 * Renvoie une liste des cartes que le joueur a en main.
	 * La liste renvoyÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e doit ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Âªtre une nouvelle {@code CardList} dont les 
	 * ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©lÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©ments sont les mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Âªmes que ceux de {@code this.hand}.
	 */
	public CardList cardsInHand() {
		CardList c=(CardList) this.hand.clone();
		return c;
	}
	
	/**
	 * Renvoie une liste de toutes les cartes possÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es par le joueur
	 * (le deck complet c'est-ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â -dire toutes les cartes dans la main, la
	 * dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fausse, la pioche et en jeu)
	 */
	public CardList totalCards() {
		CardList a=new CardList();
		a.addAll(this.hand);
		a.addAll(this.discard);
		a.addAll(this.draw);
		a.addAll(this.inPlay);
		return a;
	}
	
	/**
	 * Renvoie le nombre total de points de victoire du joueur
	 * 
	 * Ce total est calculÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© en ajoutant les valeurs individuelles de toutes les
	 * cartes dans le deck du joueur (en utilisant la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode
	 * {@code victoryValue()}) des cartes
	 */
	public int victoryPoints() {
		int victoryPoints=0;
		for(Card c:this.totalCards()){
			victoryPoints+=c.victoryValue(this);
		}
		return victoryPoints;
	}
	
	/**
	 * Renvoie une liste des autres joueurs de la partie.
	 * 
	 * Les adversaires sont listÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©s dans l'ordre de jeu, c'est-ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â -dire que le
	 * premier de la liste est celui qui joue immÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©diatement aprÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨s le joueur,
	 * puis le suivant, et ainsi de suite jusqu'au joueur qui joue immÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©diatement
	 * avant le joueur.
	 * 
	 * Rmq: Cette mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode fait appel ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode {@code otherPlayers(Player p)}
	 * de la classe {@code Game}.
	 */
	public List<Player> otherPlayers() {
		return this.game.otherPlayers(this);
	}
	
	/**
	 * Pioche une carte dans la pioche du joueur.
	 * 
	 * Si la pioche du joueur est vide, on commence par mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©langer la dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fausse
	 * et transfÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©rer toutes les cartes de la dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fausse dans la pioche.
	 * On retire et renvoie ensuite la premiÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨re carte de la pioche si elle n'est
	 * pas vide (sinon la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode ne fait rien et renvoie {@code null})
	 * 
	 * @return la carte piochÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e, {@code null} si aucune carte disponible
	 */
	public Card drawCard() {
		
			if(this.draw.size()==0){ //Si la pioche est vide		
			this.discard.shuffle(); //On mÃƒÆ’Ã‚Â¯Ãƒâ€šÃ‚Â¿Ãƒâ€šÃ‚Â½lange la dÃƒÆ’Ã‚Â¯Ãƒâ€šÃ‚Â¿Ãƒâ€šÃ‚Â½fausse
			this.draw.addAll(this.discard);// On ajoute toute la dÃƒÆ’Ã‚Â¯Ãƒâ€šÃ‚Â¿Ãƒâ€šÃ‚Â½fausse ÃƒÆ’Ã‚Â¯Ãƒâ€šÃ‚Â¿Ãƒâ€šÃ‚Â½ la pioche
			this.discard.clear();//On assigne une nouvelle CardList vide ÃƒÆ’Ã‚Â¯Ãƒâ€šÃ‚Â¿Ãƒâ€šÃ‚Â½ la dÃƒÆ’Ã‚Â¯Ãƒâ€šÃ‚Â¿Ãƒâ€šÃ‚Â½fausse
			}
		
		if(this.draw.size()==0){
			return null;
		}else{
		Card c=this.draw.remove(0);
		this.hand.add(c);
		return c ;
		}
	}
	
	
	/**
	 * Renvoie une reprÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sentation de l'ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tat du joueur sous forme d'une chaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®ne
	 * de caractÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨res.
	 * 
	 * Cette reprÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sentation comporte
	 * - le nom du joueur
	 * - le nombre d'actions, de piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ces et d'achats du joueur
	 * - le nombre de cartes dans la pioche et dans la dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fausse du joueur
	 * - la liste des cartes en jeu du joueur
	 * - la liste des cartes dans la main du joueur
	 */
	public String toString() {
		String r = String.format("     -- %s --\n", this.name);
		r += String.format("Actions: %d     Money: %d     Buys: %d     Draw: %d     Discard: %d\n", this.actions, this.money, this.buys, this.draw.size(), this.discard.size()); 
		r += String.format("In play: %s\n", this.inPlay.toString());
		r += String.format("Hand: %s\n", this.hand.toString());
		return r;
	}
	
	/**
	 * Renvoie la liste de toutes les cartes TrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sor dans la main du joueur
	 */
	public CardList getTreasureCards() {
		CardList TreasureCards=new CardList();
		for (Card c:this.hand){
			if (c.getTypes().contains(CardType.Treasure)){
				TreasureCards.add(c);
			}
		}
		return TreasureCards;
	}
	
	/**
	 * Renvoie la liste de toutes les cartes Action dans la main du joueur
	 */
	public CardList getActionCards() {
		CardList ActionCards=new CardList();
		for (Card c:this.hand){
			if (c.getTypes().contains(CardType.Action)){
				ActionCards.add(c);
			}
		}
		return ActionCards;
	}
	
	/**
	 * Renvoie la liste de toutes les cartes Victoire dans la main du joueur
	 */
	public CardList getVictoryCards() {
		CardList VictoryCards=new CardList();
		for (Card c:this.hand){
			if (c.getTypes().contains(CardType.Victory)){
				VictoryCards.add(c);
			}
		}
		return VictoryCards;
	}
	
	/**
	 * Joue une carte de la main du joueur.
	 * 
	 * @param c carte ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  jouer
	 * 
	 * Cette mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode ne vÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©rifie pas que le joueur a le droit de jouer la carte,
	 * ni mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Âªme que la carte se trouve effectivement dans sa main.
	 * La mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode retire la carte de la main du joueur, la place dans la liste
	 * {@code inPlay} et exÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cute la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode {@code play(Player p)} de la carte.
	 */
	public void playCard(Card c) {
		if(c!=null){
		this.hand.remove(c);
		this.inPlay.add(c);
		c.play(this);
		}
	}
	
	/**
	 * Joue une carte de la main du joueur.
	 * 
	 * @param cardName nom de la carte ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  jouer
	 * 
	 * S'il existe une carte dans la main du joueur dont le nom est ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©gal au
	 * paramÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨tre, la carte est jouÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  l'aide de la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode 
	 * {@code playCard(Card c)}. Si aucune carte ne correspond, la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode ne
	 * fait rien.
	 */
	public void playCard(String cardName) {
		Card playedCard=this.hand.getCard(cardName);
		if(playedCard!=null) {
		this.playCard(playedCard);
		}
		}
	
	/**
	 * Le joueur gagne une carte.
	 * 
	 * @param c carte ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  gagner (ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©ventuellement {@code null})
	 * 
	 * Si la carte n'est pas {@code null}, elle est placÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e sur la dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fausse du
	 * joueur. On suppose que la carte a correctement ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© retirÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e de son 
	 * emplacement prÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©dent au prÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©alable.
	 */
	public void gain(Card c) {
		if(c!=null){
			this.discard.add(c);
		}
	}
	
	/**
	 * Le joueur gagne une carte de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve
	 * 
	 * @param cardName nom de la carte ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  gagner. S'il existe une carte dans la 
	 * rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve ayant ce nom, cette carte est retirÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve et placÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e 
	 * sur la dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fausse du joueur.
	 * @return la carte qui a ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© ajoutÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  la dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©fausse du joueur, ou {@code 
	 * null} si aucune carte n'a ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© prise dans la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve.
	 */
	public Card gain(String cardName) {
		Card c=this.game.getFromSupply(cardName);
		if(c!=null) {
		this.game.removeFromSupply(cardName);
		this.discard.add(c);
		return c;
	}else {
		return null;
	}
		
	}
	
	/**
	 * Le joueur achÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨te une carte de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve
	 * 
	 * La mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode cherche une carte dans la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve dont le nom est ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©gal au
	 * paramÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨tre, puis vÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©rifie que le joueur a assez de piÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨ces pour l'acheter 
	 * et au moins un achat disponible.
	 * Si le joueur peut acheter la carte, le coÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â»t de la carte est soustrait ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â 
	 * l'argent du joueur, le nombre d'achats disponibles est dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©crÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©mentÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© de 1 
	 * et la carte est gagnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e par le joueur.
	 * 
	 * @param cardName nom de la carte ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  acheter
	 * @return la carte qui a ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© gagnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e ou {@code null} si l'achat n'a pas eu 
	 * lieu
	 */
	public Card buyCard(String cardName) {
		
			Card c=this.game.getFromSupply(cardName);

				if(this.money>=c.getCost()&& this.buys>0 && c!=null){
					this.game.removeFromSupply(cardName);
					this.money-=c.getCost();
					this.buys--;
					this.gain(c);
					return c;
				}
		return null;	
				
	}
	
	/**
	 * Attend une entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e de la part du joueur (au clavier) et renvoie le choix
	 *  du joueur.
	 * 
	 * @param instruction message ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  afficher ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  l'ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cran pour indiquer au joueur
	 * la nature du choix qui est attendu
	 * @param choices une liste de chaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®nes de caractÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨res correspondant aux
	 * choix valides attendus du joueur (la liste sera convertie en ensemble 
	 * par la fonction pour ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©liminer les doublons, ce qui permet de compter 
	 * correctement le nombre d'options disponibles)
	 * @param canPass boolÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©en indiquant si le joueur a le droit de passer sans
	 * faire de choix. S'il est autorisÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  passer, c'est la chaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®ne de
	 * caractÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨res vide ("") qui signifie qu'il dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sire passer.
	 * 
	 * @return la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode lit l'entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e clavier jusqu'ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  ce qu'un choix valide
	 * soit entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© par l'utilisateur (un ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©lÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©ment de {@code choices} ou
	 * ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©ventuellement la chaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®ne vide si l'utilisateur est autorisÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  passer).
	 * Lorsqu'un choix valide est obtenu, il est renvoyÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©.
	 * 
	 * Si l'ensemble {@code choices} ne comporte qu'un seul ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©lÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©ment et que
	 * {@code canPass} est faux, l'unique choix valide est automatiquement
	 * renvoyÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© sans lire l'entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e de l'utilisateur.
	 * 
	 * Si l'ensemble des choix est vide, la chaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®ne vide ("") est 
	 * automatiquement renvoyÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e par la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode (indÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©pendamment de la valeur de 
	 * {@code canPass}).
	 * 
	 * Exemple d'utilisation pour demander ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  un joueur de rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©pondre ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  une 
	 * question :
	 * <pre>
	 * {@code
	 * List<String> choices = Arrays.asList("y", "n");
	 * String input = p.choose("Do you want to ...? (y/n)", choices, false);
	 * }
	 * </pre>
	 */
	public String choose(String instruction, List<String> choices, boolean canPass) {
		// La liste de choix est convertie en ensemble pour ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©viter les doublons
		Set<String> choiceSet = new HashSet<String>();
		for (String c: choices) {
			choiceSet.add(c);
		}
		if (choiceSet.isEmpty()) {
			// Aucun choix disponible
			return "";
		} else if (choiceSet.size() == 1 && !canPass) {
			// Un seul choix possible (renvoyer cet unique ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©lÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©ment)
			return choiceSet.iterator().next();
		} else {
			String input;
			// Lit l'entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e de l'utilisateur jusqu'ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  obtenir un choix valide
			while (true) {
				System.out.print("\n\n");
				// affiche l'ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tat du jeu
				System.out.print(this.game);
				System.out.print("\n");
				// affiche l'ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tat du joueur
				System.out.print(this);
				System.out.print("\n");
				// affiche l'instruction
				System.out.println(">>> " + instruction);
				System.out.print("> ");
				// lit l'entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e de l'utilisateur au clavier
				input = this.game.readLine();
				if (choiceSet.contains(input) || (canPass && input.equals(""))){
					// si une rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©ponse valide est obtenue, elle est renvoyÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e
					return input;
				}
			}
		}
	}
	
	/**
	 * Attend une entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e de la part du joueur et renvoie le choix du joueur.
	 * Dans cette mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode, la liste des choix est donnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e sous la forme d'une 
	 * liste de cartes et le joueur doit choisir le nom d'une de ces cartes.
	 * 
	 * @param instruction message ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  afficher ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  l'ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cran pour indiquer au joueur
	 * la nature du choix qui est attendu
	 * @param choices liste de cartes parmi lesquelles il faut en choisir une
	 * parmi lesquelles l'utilisateur doit choisir
	 * @param canPass boolÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©en indiquant si le joueur a le droit de passer sans
	 * faire de choix. S'il est autorisÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  passer, c'est la chaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®ne de
	 * caractÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨res vide ("") qui signifie qu'il dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sire passer.
	 * 
	 * La mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode commence par construire une liste de tous les noms des cartes 
	 * dans {@code choices} puis appelle la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode prÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©dente pour faire 
	 * choisir un nom parmi cette liste ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  l'utilisateur.
	 * 
	 * Exemple d'utilisation pour faire choisir le nom d'une carte Action de sa
	 * main ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  un joueur (dans cet exemple le joueur n'a pas le droit de passer 
	 * s'il a au moins une carte Action en main, mais la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode peut quand 
	 * mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Âªme renvoyer {@code ""} s'il n'a aucune carte Action en main) :
	 * <pre>
	 * {@code
	 * CardList choices = new CardList();
	 * for (Card c: p.cardsInHand()) {
	 *   if (c.getTypes().contains(CardType.Action)) {
	 *     choices.add(c);
	 *   }
	 * }
	 * String input = p.chooseCard("Choose an Action card.", choices, false);
	 * </pre>
	 */
	public String chooseCard(String instruction, CardList choices, boolean canPass) {
		// liste de noms de cartes
		List<String> stringChoices = new ArrayList<String>();
		for (Card c: choices) {
			// tous les noms sont ajoutÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©s ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  l'ensemble
			stringChoices.add(c.getName());
		}
		// appel de la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode prÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©dente en passant l'ensemble de noms
		return this.choose(instruction, stringChoices, canPass);
	}
	
	/**
	 * DÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©marre le tour du joueur
	 * 
	 * Les compteurs d'actions et achats sont mis ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  1
	 */
	public void startTurn() {
		this.buys=1;
		this.actions=1;
	}
	
	/**
	 * Termine le tour du joueur
	 * 
	 * - Les compteurs d'actions, argent et achats du joueur sont remis ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  0
	 * - Les cartes en main et en jeu sont dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©faussÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es
	 * - Le joueur pioche 5 cartes en main
	 */
	public void endTurn() {
		this.buys=0;
		this.actions=0;
		this.money=0;
		
		for (int i=0;i<this.inPlay.size();i++){
			this.discard.add(this.inPlay.get(0));
			this.inPlay.remove(0);			
		}
		
		for (int i=0;i<this.hand.size();i++){
			this.discard.add(this.hand.get(0));
			this.hand.remove(0);			
		}
	}
	
	/**
	 * ExÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cute le tour d'un joueur
	 * 
	 * Cette mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode exÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cute successivement les 5 phases du tour d'un joueur:
	 * 
	 * 1. (PrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©paration) la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode {@code startTurn()} est appelÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e
	 * 
	 * 2. (Action) Tant que le joueur a des actions disponibles, on lui demande 
	 * de choisir le nom d'une carte Action de sa main ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  jouer. Il peut passer ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â 
	 * tout moment ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  la phase suivante (soit de maniÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨re forcÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e s'il n'a plus de 
	 * carte Action en main soit volontairement en entrant la chaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®ne vide). 
	 * Lorsqu'il joue une carte Action, la fonction dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©crÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©mente son nombre 
	 * d'actions puis joue la carte.
	 * 
	 * 3. (TrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sor) Le joueur joue toutes les cartes TrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sor de sa main 
	 * automatiquement (dans le jeu de base il n'y a aucune raison de ne pas 
	 * jouer tous les trÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sors automatiquement).
	 * 
	 * 4. (Achat) Tant que le joueur a au moins un achat disponible, on lui 
	 * demande de choisir le nom d'une carte de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve qu'il veut acheter. 
	 * Il ne peut acheter que des cartes dont le prix est infÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©rieur ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  l'argent 
	 * dont il dispose. Le joueur peut passer (et terminer son tour) ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  tout 
	 * moment pendant cette phase.
	 * 
	 * 5. (Fin) La mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode {@code endTurn()} est appelÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e pour terminer le tour 
	 * du joueur
	 */
	public void playTurn() {
		startTurn();
		
		CardList cardlist;
		while (actions>0){
			 cardlist=getActionCards();
			String n=chooseCard("Action?",cardlist,true);
			if(n.equals("")){
				this.actions=0;
			}else{
				playCard(n);
				this.actions--;
				
			}
			
		}
		
	
		for(Card c: getTreasureCards()){
			playCard(c);
		}
		CardList cardlistt;
		while(buys>0){
			 cardlistt=this.game.availableSupplyCards();
				String nn=chooseCard("Achat?",cardlistt,true);
				if(nn.equals("")){
					this.buys=0;
				}else{
					buyCard(nn);
					
				}
			
			
		}
	
		
		endTurn();
	}

	
	//MÃƒÂ©thodes rajoutÃƒÂ©es
	
	public void drawNCard(int i) {
		
		for(int a=0;a<i;a++) {
			this.drawCard();
		}
	}
	
	public void trashcard(String cardname) {
		Card c=this.hand.remove(cardname);
		this.game.hadtrash(c);
	
	}
	
	public Card gaindeck(String cardName) {
		Card c=this.game.getFromSupply(cardName);
		if(c!=null) {
		this.game.removeFromSupply(cardName);
		this.draw.add(0,c);
		return c;
	}else {
		return null;
	}
		
	}
}
