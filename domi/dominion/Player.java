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
	 * Nombre de piÃƒÆ’Ã‚Â¨ces disponibles pour acheter des cartes
	 */
	private int money;
	
	/**
	 * Nombre d'achats disponibles
	 */
	private int buys;
	
	/**
	 * RÃƒÆ’Ã‚Â©fÃƒÆ’Ã‚Â©rence vers la partie en cours
	 */
	private Game game;
	
	/**
	 * Liste des cartes dans la main du joueur
	 */
	private CardList hand;
	
	/**
	 * Liste des cartes dans la dÃƒÆ’Ã‚Â©fausse du joueur
	 */
	private CardList discard;
	
	/**
	 * Liste des cartes dans la pioche du joueur
	 */
	private CardList draw;
	
	/**
	 * Listes des cartes qui ont ÃƒÆ’Ã‚Â©tÃƒÆ’Ã‚Â© jouÃƒÆ’Ã‚Â©es pendant le tour courant
	 */
	private CardList inPlay;
	
	/**
	 * Constructeur
	 * 
	 * Initialise les diffÃƒÆ’Ã‚Â©rentes piles de cartes du joueur, place 3 cartes
	 * Estate et 7 cartes Copper dans la dÃƒÆ’Ã‚Â©fausse du joueur puis fait piocher 5
	 * cartes en main au joueur.
	 * 
	 * @param name: le nom du joueur
	 * @param game: le jeu en cours
	 * 
	 * Indications: On peut utiliser la mÃƒÆ’Ã‚Â©thode {@code this.endTurn()} pour 
	 * prÃƒÆ’Ã‚Â©parer la main du joueur aprÃƒÆ’Ã‚Â¨s avoir placÃƒÆ’Ã‚Â© les cartes dans la dÃƒÆ’Ã‚Â©fausse.
	 */
	public Player(String name, Game game) {
		this.name=name;
		this.game=game;
		
		
		this.actions=1;
		this.buys=1;
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
	
	/**
	 * IncrÃƒÆ’Ã‚Â©mente le nombre d'actions du joueur
	 * 
	 * @param n nombre d'actions ÃƒÆ’Ã‚Â  ajouter (ce nombre peut ÃƒÆ’Ã‚Âªtre nÃƒÆ’Ã‚Â©gatif si l'on
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
	 * IncrÃƒÆ’Ã‚Â©mente le nombre de piÃƒÆ’Ã‚Â¨ces du joueur
	 * 
	 * @param n nombre de piÃƒÆ’Ã‚Â¨ces ÃƒÆ’Ã‚Â  ajouter (ce nombre peut ÃƒÆ’Ã‚Âªtre nÃƒÆ’Ã‚Â©gatif si l'on
	 * souhaite diminuer le nombre de piÃƒÆ’Ã‚Â¨ces)
	 */
	public void incrementMoney(int n) {
		this.money=this.money+n;
		if (this.money<0) {
			this.money=0;}
	}
	
	/**
	 * IncrÃƒÆ’Ã‚Â©mente le nombre d'achats disponibles du joueur
	 * 
	 * @param n nombre d'achats ÃƒÆ’Ã‚Â  ajouter (ce nombre peut ÃƒÆ’Ã‚Âªtre nÃƒÆ’Ã‚Â©gatif si l'on
	 * souhaite diminuer le nombre d'achats)
	 */
	public void incrementBuys(int n) {
		this.buys=this.buys+n;
		if (this.buys<0) {
			this.buys=0;}
	}

	/**
	 * Renvoie une liste des cartes que le joueur a en main.
	 * La liste renvoyÃƒÆ’Ã‚Â©e doit ÃƒÆ’Ã‚Âªtre une nouvelle {@code CardList} dont les 
	 * ÃƒÆ’Ã‚Â©lÃƒÆ’Ã‚Â©ments sont les mÃƒÆ’Ã‚Âªmes que ceux de {@code this.hand}.
	 */
	public CardList cardsInHand() {
		CardList c=(CardList) this.hand.clone();
		return c;
	}
	
	/**
	 * Renvoie une liste de toutes les cartes possÃƒÆ’Ã‚Â©dÃƒÆ’Ã‚Â©es par le joueur
	 * (le deck complet c'est-ÃƒÆ’Ã‚Â -dire toutes les cartes dans la main, la
	 * dÃƒÆ’Ã‚Â©fausse, la pioche et en jeu)
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
	 * Ce total est calculÃƒÆ’Ã‚Â© en ajoutant les valeurs individuelles de toutes les
	 * cartes dans le deck du joueur (en utilisant la mÃƒÆ’Ã‚Â©thode
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
	 * Les adversaires sont listÃƒÆ’Ã‚Â©s dans l'ordre de jeu, c'est-ÃƒÆ’Ã‚Â -dire que le
	 * premier de la liste est celui qui joue immÃƒÆ’Ã‚Â©diatement aprÃƒÆ’Ã‚Â¨s le joueur,
	 * puis le suivant, et ainsi de suite jusqu'au joueur qui joue immÃƒÆ’Ã‚Â©diatement
	 * avant le joueur.
	 * 
	 * Rmq: Cette mÃƒÆ’Ã‚Â©thode fait appel ÃƒÆ’Ã‚Â  la mÃƒÆ’Ã‚Â©thode {@code otherPlayers(Player p)}
	 * de la classe {@code Game}.
	 */
	public List<Player> otherPlayers() {
		return this.game.otherPlayers(this);
	}
	
	/**
	 * Pioche une carte dans la pioche du joueur.
	 * 
	 * Si la pioche du joueur est vide, on commence par mÃƒÆ’Ã‚Â©langer la dÃƒÆ’Ã‚Â©fausse
	 * et transfÃƒÆ’Ã‚Â©rer toutes les cartes de la dÃƒÆ’Ã‚Â©fausse dans la pioche.
	 * On retire et renvoie ensuite la premiÃƒÆ’Ã‚Â¨re carte de la pioche si elle n'est
	 * pas vide (sinon la mÃƒÆ’Ã‚Â©thode ne fait rien et renvoie {@code null})
	 * 
	 * @return la carte piochÃƒÆ’Ã‚Â©e, {@code null} si aucune carte disponible
	 */
	public Card drawCard() {
		
			if(this.draw.size()==0){ //Si la pioche est vide		
			this.discard.shuffle(); //On mÃƒÂ¯Ã‚Â¿Ã‚Â½lange la dÃƒÂ¯Ã‚Â¿Ã‚Â½fausse
			this.draw.addAll(this.discard);// On ajoute toute la dÃƒÂ¯Ã‚Â¿Ã‚Â½fausse ÃƒÂ¯Ã‚Â¿Ã‚Â½ la pioche
			this.discard.clear();//On assigne une nouvelle CardList vide ÃƒÂ¯Ã‚Â¿Ã‚Â½ la dÃƒÂ¯Ã‚Â¿Ã‚Â½fausse
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
	 * Renvoie une reprÃƒÆ’Ã‚Â©sentation de l'ÃƒÆ’Ã‚Â©tat du joueur sous forme d'une chaÃƒÆ’Ã‚Â®ne
	 * de caractÃƒÆ’Ã‚Â¨res.
	 * 
	 * Cette reprÃƒÆ’Ã‚Â©sentation comporte
	 * - le nom du joueur
	 * - le nombre d'actions, de piÃƒÆ’Ã‚Â¨ces et d'achats du joueur
	 * - le nombre de cartes dans la pioche et dans la dÃƒÆ’Ã‚Â©fausse du joueur
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
	 * Renvoie la liste de toutes les cartes TrÃƒÆ’Ã‚Â©sor dans la main du joueur
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
	 * @param c carte ÃƒÆ’Ã‚Â  jouer
	 * 
	 * Cette mÃƒÆ’Ã‚Â©thode ne vÃƒÆ’Ã‚Â©rifie pas que le joueur a le droit de jouer la carte,
	 * ni mÃƒÆ’Ã‚Âªme que la carte se trouve effectivement dans sa main.
	 * La mÃƒÆ’Ã‚Â©thode retire la carte de la main du joueur, la place dans la liste
	 * {@code inPlay} et exÃƒÆ’Ã‚Â©cute la mÃƒÆ’Ã‚Â©thode {@code play(Player p)} de la carte.
	 */
public void playCard(Card c) {
		if(c!=null){
		this.hand.remove(c);
		this.inPlay.add(c);
		
		
		//Check for reactions
		if(c.getTypes().contains(CardType.Attack)) {
			
			c=(ActionCard) c;
			
			List<Player> victimes=this.otherPlayers();
			
			for(Player p:this.otherPlayers()){
				
				for(Card a:p.hand) {
					
					if (a.getTypes().contains(CardType.Reaction)) {
						
						ReactionCard rC=(ReactionCard) a;
						
						if(rC.react(p)) {
							victimes.remove(p);
							
							break;
						}
					}
					
				}
				
			}
			System.out.println("\n\nVitcimes : "+victimes.toString()+"\n\n");
			AttackCard aC= (AttackCard) c;
			aC.play(this,victimes);
		
		}
		else {
			c.play(this);
		}
		
		}
	}
	
	
	/**
	 * Joue une carte de la main du joueur.
	 * 
	 * @param cardName nom de la carte ÃƒÆ’Ã‚Â  jouer
	 * 
	 * S'il existe une carte dans la main du joueur dont le nom est ÃƒÆ’Ã‚Â©gal au
	 * paramÃƒÆ’Ã‚Â¨tre, la carte est jouÃƒÆ’Ã‚Â©e ÃƒÆ’Ã‚Â  l'aide de la mÃƒÆ’Ã‚Â©thode 
	 * {@code playCard(Card c)}. Si aucune carte ne correspond, la mÃƒÆ’Ã‚Â©thode ne
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
	 * @param c carte ÃƒÆ’Ã‚Â  gagner (ÃƒÆ’Ã‚Â©ventuellement {@code null})
	 * 
	 * Si la carte n'est pas {@code null}, elle est placÃƒÆ’Ã‚Â©e sur la dÃƒÆ’Ã‚Â©fausse du
	 * joueur. On suppose que la carte a correctement ÃƒÆ’Ã‚Â©tÃƒÆ’Ã‚Â© retirÃƒÆ’Ã‚Â©e de son 
	 * emplacement prÃƒÆ’Ã‚Â©cÃƒÆ’Ã‚Â©dent au prÃƒÆ’Ã‚Â©alable.
	 */
	public void gain(Card c) {
		if(c!=null){
			this.discard.add(c);
		}
	}
	
	/**
	 * Le joueur gagne une carte de la rÃƒÆ’Ã‚Â©serve
	 * 
	 * @param cardName nom de la carte ÃƒÆ’Ã‚Â  gagner. S'il existe une carte dans la 
	 * rÃƒÆ’Ã‚Â©serve ayant ce nom, cette carte est retirÃƒÆ’Ã‚Â©e de la rÃƒÆ’Ã‚Â©serve et placÃƒÆ’Ã‚Â©e 
	 * sur la dÃƒÆ’Ã‚Â©fausse du joueur.
	 * @return la carte qui a ÃƒÆ’Ã‚Â©tÃƒÆ’Ã‚Â© ajoutÃƒÆ’Ã‚Â©e ÃƒÆ’Ã‚Â  la dÃƒÆ’Ã‚Â©fausse du joueur, ou {@code 
	 * null} si aucune carte n'a ÃƒÆ’Ã‚Â©tÃƒÆ’Ã‚Â© prise dans la rÃƒÆ’Ã‚Â©serve.
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
	 * Le joueur achÃƒÆ’Ã‚Â¨te une carte de la rÃƒÆ’Ã‚Â©serve
	 * 
	 * La mÃƒÆ’Ã‚Â©thode cherche une carte dans la rÃƒÆ’Ã‚Â©serve dont le nom est ÃƒÆ’Ã‚Â©gal au
	 * paramÃƒÆ’Ã‚Â¨tre, puis vÃƒÆ’Ã‚Â©rifie que le joueur a assez de piÃƒÆ’Ã‚Â¨ces pour l'acheter 
	 * et au moins un achat disponible.
	 * Si le joueur peut acheter la carte, le coÃƒÆ’Ã‚Â»t de la carte est soustrait ÃƒÆ’Ã‚Â 
	 * l'argent du joueur, le nombre d'achats disponibles est dÃƒÆ’Ã‚Â©crÃƒÆ’Ã‚Â©mentÃƒÆ’Ã‚Â© de 1 
	 * et la carte est gagnÃƒÆ’Ã‚Â©e par le joueur.
	 * 
	 * @param cardName nom de la carte ÃƒÆ’Ã‚Â  acheter
	 * @return la carte qui a ÃƒÆ’Ã‚Â©tÃƒÆ’Ã‚Â© gagnÃƒÆ’Ã‚Â©e ou {@code null} si l'achat n'a pas eu 
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
	 * Attend une entrÃƒÆ’Ã‚Â©e de la part du joueur (au clavier) et renvoie le choix
	 *  du joueur.
	 * 
	 * @param instruction message ÃƒÆ’Ã‚Â  afficher ÃƒÆ’Ã‚Â  l'ÃƒÆ’Ã‚Â©cran pour indiquer au joueur
	 * la nature du choix qui est attendu
	 * @param choices une liste de chaÃƒÆ’Ã‚Â®nes de caractÃƒÆ’Ã‚Â¨res correspondant aux
	 * choix valides attendus du joueur (la liste sera convertie en ensemble 
	 * par la fonction pour ÃƒÆ’Ã‚Â©liminer les doublons, ce qui permet de compter 
	 * correctement le nombre d'options disponibles)
	 * @param canPass boolÃƒÆ’Ã‚Â©en indiquant si le joueur a le droit de passer sans
	 * faire de choix. S'il est autorisÃƒÆ’Ã‚Â© ÃƒÆ’Ã‚Â  passer, c'est la chaÃƒÆ’Ã‚Â®ne de
	 * caractÃƒÆ’Ã‚Â¨res vide ("") qui signifie qu'il dÃƒÆ’Ã‚Â©sire passer.
	 * 
	 * @return la mÃƒÆ’Ã‚Â©thode lit l'entrÃƒÆ’Ã‚Â©e clavier jusqu'ÃƒÆ’Ã‚Â  ce qu'un choix valide
	 * soit entrÃƒÆ’Ã‚Â© par l'utilisateur (un ÃƒÆ’Ã‚Â©lÃƒÆ’Ã‚Â©ment de {@code choices} ou
	 * ÃƒÆ’Ã‚Â©ventuellement la chaÃƒÆ’Ã‚Â®ne vide si l'utilisateur est autorisÃƒÆ’Ã‚Â© ÃƒÆ’Ã‚Â  passer).
	 * Lorsqu'un choix valide est obtenu, il est renvoyÃƒÆ’Ã‚Â©.
	 * 
	 * Si l'ensemble {@code choices} ne comporte qu'un seul ÃƒÆ’Ã‚Â©lÃƒÆ’Ã‚Â©ment et que
	 * {@code canPass} est faux, l'unique choix valide est automatiquement
	 * renvoyÃƒÆ’Ã‚Â© sans lire l'entrÃƒÆ’Ã‚Â©e de l'utilisateur.
	 * 
	 * Si l'ensemble des choix est vide, la chaÃƒÆ’Ã‚Â®ne vide ("") est 
	 * automatiquement renvoyÃƒÆ’Ã‚Â©e par la mÃƒÆ’Ã‚Â©thode (indÃƒÆ’Ã‚Â©pendamment de la valeur de 
	 * {@code canPass}).
	 * 
	 * Exemple d'utilisation pour demander ÃƒÆ’Ã‚Â  un joueur de rÃƒÆ’Ã‚Â©pondre ÃƒÆ’Ã‚Â  une 
	 * question :
	 * <pre>
	 * {@code
	 * List<String> choices = Arrays.asList("y", "n");
	 * String input = p.choose("Do you want to ...? (y/n)", choices, false);
	 * }
	 * </pre>
	 */
	public String choose(String instruction, List<String> choices, boolean canPass) {
		// La liste de choix est convertie en ensemble pour ÃƒÆ’Ã‚Â©viter les doublons
		Set<String> choiceSet = new HashSet<String>();
		for (String c: choices) {
			choiceSet.add(c);
		}
		if (choiceSet.isEmpty()) {
			// Aucun choix disponible
			return "";
		} else if (choiceSet.size() == 1 && !canPass) {
			// Un seul choix possible (renvoyer cet unique ÃƒÆ’Ã‚Â©lÃƒÆ’Ã‚Â©ment)
			return choiceSet.iterator().next();
		} else {
			String input;
			// Lit l'entrÃƒÆ’Ã‚Â©e de l'utilisateur jusqu'ÃƒÆ’Ã‚Â  obtenir un choix valide
			while (true) {
				System.out.print("\n\n");
				// affiche l'ÃƒÆ’Ã‚Â©tat du jeu
				System.out.print(this.game);
				System.out.print("\n");
				// affiche l'ÃƒÆ’Ã‚Â©tat du joueur
				System.out.print(this);
				System.out.print("\n");
				// affiche l'instruction
				System.out.println(">>> " + instruction);
				System.out.print("> ");
				// lit l'entrÃƒÆ’Ã‚Â©e de l'utilisateur au clavier
				input = this.game.readLine();
				if (choiceSet.contains(input) || (canPass && input.equals(""))){
					// si une rÃƒÆ’Ã‚Â©ponse valide est obtenue, elle est renvoyÃƒÆ’Ã‚Â©e
					return input;
				}
			}
		}
	}
	
	/**
	 * Attend une entrÃƒÆ’Ã‚Â©e de la part du joueur et renvoie le choix du joueur.
	 * Dans cette mÃƒÆ’Ã‚Â©thode, la liste des choix est donnÃƒÆ’Ã‚Â©e sous la forme d'une 
	 * liste de cartes et le joueur doit choisir le nom d'une de ces cartes.
	 * 
	 * @param instruction message ÃƒÆ’Ã‚Â  afficher ÃƒÆ’Ã‚Â  l'ÃƒÆ’Ã‚Â©cran pour indiquer au joueur
	 * la nature du choix qui est attendu
	 * @param choices liste de cartes parmi lesquelles il faut en choisir une
	 * parmi lesquelles l'utilisateur doit choisir
	 * @param canPass boolÃƒÆ’Ã‚Â©en indiquant si le joueur a le droit de passer sans
	 * faire de choix. S'il est autorisÃƒÆ’Ã‚Â© ÃƒÆ’Ã‚Â  passer, c'est la chaÃƒÆ’Ã‚Â®ne de
	 * caractÃƒÆ’Ã‚Â¨res vide ("") qui signifie qu'il dÃƒÆ’Ã‚Â©sire passer.
	 * 
	 * La mÃƒÆ’Ã‚Â©thode commence par construire une liste de tous les noms des cartes 
	 * dans {@code choices} puis appelle la mÃƒÆ’Ã‚Â©thode prÃƒÆ’Ã‚Â©cÃƒÆ’Ã‚Â©dente pour faire 
	 * choisir un nom parmi cette liste ÃƒÆ’Ã‚Â  l'utilisateur.
	 * 
	 * Exemple d'utilisation pour faire choisir le nom d'une carte Action de sa
	 * main ÃƒÆ’Ã‚Â  un joueur (dans cet exemple le joueur n'a pas le droit de passer 
	 * s'il a au moins une carte Action en main, mais la mÃƒÆ’Ã‚Â©thode peut quand 
	 * mÃƒÆ’Ã‚Âªme renvoyer {@code ""} s'il n'a aucune carte Action en main) :
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
			// tous les noms sont ajoutÃƒÆ’Ã‚Â©s ÃƒÆ’Ã‚Â  l'ensemble
			stringChoices.add(c.getName());
		}
		// appel de la mÃƒÆ’Ã‚Â©thode prÃƒÆ’Ã‚Â©cÃƒÆ’Ã‚Â©dente en passant l'ensemble de noms
		return this.choose(instruction, stringChoices, canPass);
	}
	
	/**
	 * DÃƒÆ’Ã‚Â©marre le tour du joueur
	 * 
	 * Les compteurs d'actions et achats sont mis ÃƒÆ’Ã‚Â  1
	 */
	public void startTurn() {
		this.buys=1;
		this.actions=1;
	}
	
	/**
	 * Termine le tour du joueur
	 * 
	 * - Les compteurs d'actions, argent et achats du joueur sont remis ÃƒÆ’Ã‚Â  0
	 * - Les cartes en main et en jeu sont dÃƒÆ’Ã‚Â©faussÃƒÆ’Ã‚Â©es
	 * - Le joueur pioche 5 cartes en main
	 */
	public void endTurn() {
		//Reset des compteurs
		this.buys=0;
		this.actions=0;
		this.money=0;
		
		//On défausse les cartes en jeu
		for (Card c:this.inPlay){
			this.discard.add(c);
			this.inPlay.remove(c);			
		}
		
		//On défausse les cartes en main
		for (Card c:this.hand){
			this.discardCard(c);
		}
		
		//On pioche 5 cartes
		this.drawNCard(5);
		
	}
	
	/**
	 * ExÃƒÆ’Ã‚Â©cute le tour d'un joueur
	 * 
	 * Cette mÃƒÆ’Ã‚Â©thode exÃƒÆ’Ã‚Â©cute successivement les 5 phases du tour d'un joueur:
	 * 
	 * 1. (PrÃƒÆ’Ã‚Â©paration) la mÃƒÆ’Ã‚Â©thode {@code startTurn()} est appelÃƒÆ’Ã‚Â©e
	 * 
	 * 2. (Action) Tant que le joueur a des actions disponibles, on lui demande 
	 * de choisir le nom d'une carte Action de sa main ÃƒÆ’Ã‚Â  jouer. Il peut passer ÃƒÆ’Ã‚Â 
	 * tout moment ÃƒÆ’Ã‚Â  la phase suivante (soit de maniÃƒÆ’Ã‚Â¨re forcÃƒÆ’Ã‚Â©e s'il n'a plus de 
	 * carte Action en main soit volontairement en entrant la chaÃƒÆ’Ã‚Â®ne vide). 
	 * Lorsqu'il joue une carte Action, la fonction dÃƒÆ’Ã‚Â©crÃƒÆ’Ã‚Â©mente son nombre 
	 * d'actions puis joue la carte.
	 * 
	 * 3. (TrÃƒÆ’Ã‚Â©sor) Le joueur joue toutes les cartes TrÃƒÆ’Ã‚Â©sor de sa main 
	 * automatiquement (dans le jeu de base il n'y a aucune raison de ne pas 
	 * jouer tous les trÃƒÆ’Ã‚Â©sors automatiquement).
	 * 
	 * 4. (Achat) Tant que le joueur a au moins un achat disponible, on lui 
	 * demande de choisir le nom d'une carte de la rÃƒÆ’Ã‚Â©serve qu'il veut acheter. 
	 * Il ne peut acheter que des cartes dont le prix est infÃƒÆ’Ã‚Â©rieur ÃƒÆ’Ã‚Â  l'argent 
	 * dont il dispose. Le joueur peut passer (et terminer son tour) ÃƒÆ’Ã‚Â  tout 
	 * moment pendant cette phase.
	 * 
	 * 5. (Fin) La mÃƒÆ’Ã‚Â©thode {@code endTurn()} est appelÃƒÆ’Ã‚Â©e pour terminer le tour 
	 * du joueur
	 */

	public void playTurn() {
			this.startTurn();
		    while(this.actions > 0){
				this.actions--;
				String cardname = chooseCard("Action Phase", this.getActionCards(), true);
				if (cardname.equals("")){
					this.actions = 0;
				}
				else{
					this.playCard(cardname);
				}
	        }
	        for(Card c: getTreasureCards()){
		    	this.playCard(c);
	        }
	        CardList cardYouCanBuy = new CardList();
	        while(buys > 0){
				cardYouCanBuy.clear();
				for (Card c: this.game.availableSupplyCards()){
					if (c.getCost() <= this.money){
						cardYouCanBuy.add(c);
					}
				}
	        	// On vérifie si le joueur entre une carte trop chère ou s'il appuie uniquement sur ENTREE.
		    	if (this.buyCard(this.chooseCard("Buy Phase", cardYouCanBuy, true)) == null){
					this.buys = 0;
				}
			}
			endTurn();
		}

	
	//MÃ©thodes rajoutÃ©es
	
	public void drawNCard(int i) {
		
		for(int a=0;a<i;a++) {
			this.drawCard();
		}
	}
	
	
	public void discardCard(Card c) {//On suppose que la carte est déjà en main
		this.hand.remove(c);
		this.discard.add(c);
	}
	
	public void trashCard(Card c) {//On suppose c non null
		this.hand.remove(c);
		this.game.addToTrash(c);
	}
	

	
	public Card getFromTrash(Card c){//On suppose c non null
		this.game.removeFromTrash(c);
		return c;
	}
	
	public CardList getDraw() {
		return this.draw;
	}
	
	public void cardHandToTopDraw(Card c) {
		this.hand.remove(c);
		this.draw.add(0, c);
	}
	
	public void removeFromHand(Card c) {
		this.hand.remove(c);
	}
	

	
	public Card trashCard(String cardname) {
		Card c=this.hand.getCard(cardname);
		this.hand.remove(c);
		this.game.hadtrash(c);
		return c;
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
	
		public void trashcardinPlay(String cardname) {
		Card c=this.inPlay.remove(cardname);
		this.game.hadtrash(c);
	
	}
	
	public void defaussedeck() {
		Card c;
		while(!this.draw.isEmpty()) {
			c=this.draw.remove(0);
			this.discard.add(c);
		}
		
		
	}
	
	public Card gaininhand(String cardName) {
		Card c=this.game.getFromSupply(cardName);
		if(c!=null) {
		this.game.removeFromSupply(cardName);
		this.hand.add(c);
		return c;
	}else {
		return null;
	}
		
	}
	
	public Card putondeck(String c) {
		Card card=this.hand.remove(c);
		this.draw.add(0,card);
		return card;
	}
	
	public void revealdeck() {
		for(Card c:this.hand) {
			System.out.println("ln"+c.toString());
		}
	}
		
		public void revealcard(Card c) {

				System.out.println("ln"+c.toString());
			}
}
