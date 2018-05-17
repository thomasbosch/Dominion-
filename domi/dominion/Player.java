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
	 * Nombre de piÃƒÂ¨ces disponibles pour acheter des cartes
	 */
	private int money;
	
	/**
	 * Nombre d'achats disponibles
	 */
	private int buys;
	
	/**
	 * RÃƒÂ©fÃƒÂ©rence vers la partie en cours
	 */
	private Game game;
	
	/**
	 * Liste des cartes dans la main du joueur
	 */
	private CardList hand;
	
	/**
	 * Liste des cartes dans la dÃƒÂ©fausse du joueur
	 */
	private CardList discard;
	
	/**
	 * Liste des cartes dans la pioche du joueur
	 */
	private CardList draw;
	
	/**
	 * Listes des cartes qui ont ÃƒÂ©tÃƒÂ© jouÃƒÂ©es pendant le tour courant
	 */
	private CardList inPlay;
	
	/**
	 * Constructeur
	 * 
	 * Initialise les diffÃƒÂ©rentes piles de cartes du joueur, place 3 cartes
	 * Estate et 7 cartes Copper dans la dÃƒÂ©fausse du joueur puis fait piocher 5
	 * cartes en main au joueur.
	 * 
	 * @param name: le nom du joueur
	 * @param game: le jeu en cours
	 * 
	 * Indications: On peut utiliser la mÃƒÂ©thode {@code this.endTurn()} pour 
	 * prÃƒÂ©parer la main du joueur aprÃƒÂ¨s avoir placÃƒÂ© les cartes dans la dÃƒÂ©fausse.
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
	 * IncrÃƒÂ©mente le nombre d'actions du joueur
	 * 
	 * @param n nombre d'actions ÃƒÂ  ajouter (ce nombre peut ÃƒÂªtre nÃƒÂ©gatif si l'on
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
	 * IncrÃƒÂ©mente le nombre de piÃƒÂ¨ces du joueur
	 * 
	 * @param n nombre de piÃƒÂ¨ces ÃƒÂ  ajouter (ce nombre peut ÃƒÂªtre nÃƒÂ©gatif si l'on
	 * souhaite diminuer le nombre de piÃƒÂ¨ces)
	 */
	public void incrementMoney(int n) {
		this.money=this.money+n;
		if (this.money<0) {
			this.money=0;}
	}
	
	/**
	 * IncrÃƒÂ©mente le nombre d'achats disponibles du joueur
	 * 
	 * @param n nombre d'achats ÃƒÂ  ajouter (ce nombre peut ÃƒÂªtre nÃƒÂ©gatif si l'on
	 * souhaite diminuer le nombre d'achats)
	 */
	public void incrementBuys(int n) {
		this.buys=this.buys+n;
		if (this.buys<0) {
			this.buys=0;}
	}

	/**
	 * Renvoie une liste des cartes que le joueur a en main.
	 * La liste renvoyÃƒÂ©e doit ÃƒÂªtre une nouvelle {@code CardList} dont les 
	 * ÃƒÂ©lÃƒÂ©ments sont les mÃƒÂªmes que ceux de {@code this.hand}.
	 */
	public CardList cardsInHand() {
		CardList c=(CardList) this.hand.clone();
		return c;
	}
	
	/**
	 * Renvoie une liste de toutes les cartes possÃƒÂ©dÃƒÂ©es par le joueur
	 * (le deck complet c'est-ÃƒÂ -dire toutes les cartes dans la main, la
	 * dÃƒÂ©fausse, la pioche et en jeu)
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
	 * Ce total est calculÃƒÂ© en ajoutant les valeurs individuelles de toutes les
	 * cartes dans le deck du joueur (en utilisant la mÃƒÂ©thode
	 * {@code victoryValue()}) des cartes
	 */
	public int victoryPoints() {
		int victoryPoints=0;
		for(Card a:this.getVictoryCards()){
			victoryPoints=+a.victoryValue(this);
		}
		return victoryPoints;
	}
	
	/**
	 * Renvoie une liste des autres joueurs de la partie.
	 * 
	 * Les adversaires sont listÃƒÂ©s dans l'ordre de jeu, c'est-ÃƒÂ -dire que le
	 * premier de la liste est celui qui joue immÃƒÂ©diatement aprÃƒÂ¨s le joueur,
	 * puis le suivant, et ainsi de suite jusqu'au joueur qui joue immÃƒÂ©diatement
	 * avant le joueur.
	 * 
	 * Rmq: Cette mÃƒÂ©thode fait appel ÃƒÂ  la mÃƒÂ©thode {@code otherPlayers(Player p)}
	 * de la classe {@code Game}.
	 */
	public List<Player> otherPlayers() {
		return this.game.otherPlayers(this);
	}
	
	/**
	 * Pioche une carte dans la pioche du joueur.
	 * 
	 * Si la pioche du joueur est vide, on commence par mÃƒÂ©langer la dÃƒÂ©fausse
	 * et transfÃƒÂ©rer toutes les cartes de la dÃƒÂ©fausse dans la pioche.
	 * On retire et renvoie ensuite la premiÃƒÂ¨re carte de la pioche si elle n'est
	 * pas vide (sinon la mÃƒÂ©thode ne fait rien et renvoie {@code null})
	 * 
	 * @return la carte piochÃƒÂ©e, {@code null} si aucune carte disponible
	 */
	public Card drawCard() {
		
			if(this.draw.size()==0){ //Si la pioche est vide		
			this.discard.shuffle(); //On mÃ¯Â¿Â½lange la dÃ¯Â¿Â½fausse
			this.draw.addAll(this.discard);// On ajoute toute la dÃ¯Â¿Â½fausse Ã¯Â¿Â½ la pioche
			this.discard.clear();//On assigne une nouvelle CardList vide Ã¯Â¿Â½ la dÃ¯Â¿Â½fausse
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
	 * Renvoie une reprÃƒÂ©sentation de l'ÃƒÂ©tat du joueur sous forme d'une chaÃƒÂ®ne
	 * de caractÃƒÂ¨res.
	 * 
	 * Cette reprÃƒÂ©sentation comporte
	 * - le nom du joueur
	 * - le nombre d'actions, de piÃƒÂ¨ces et d'achats du joueur
	 * - le nombre de cartes dans la pioche et dans la dÃƒÂ©fausse du joueur
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
	 * Renvoie la liste de toutes les cartes TrÃƒÂ©sor dans la main du joueur
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
	 * @param c carte ÃƒÂ  jouer
	 * 
	 * Cette mÃƒÂ©thode ne vÃƒÂ©rifie pas que le joueur a le droit de jouer la carte,
	 * ni mÃƒÂªme que la carte se trouve effectivement dans sa main.
	 * La mÃƒÂ©thode retire la carte de la main du joueur, la place dans la liste
	 * {@code inPlay} et exÃƒÂ©cute la mÃƒÂ©thode {@code play(Player p)} de la carte.
	 */
	public void playCard(Card c) {
		this.hand.remove(c);
		this.inPlay.add(c);
		c.play(this);
	}
	
	/**
	 * Joue une carte de la main du joueur.
	 * 
	 * @param cardName nom de la carte ÃƒÂ  jouer
	 * 
	 * S'il existe une carte dans la main du joueur dont le nom est ÃƒÂ©gal au
	 * paramÃƒÂ¨tre, la carte est jouÃƒÂ©e ÃƒÂ  l'aide de la mÃƒÂ©thode 
	 * {@code playCard(Card c)}. Si aucune carte ne correspond, la mÃƒÂ©thode ne
	 * fait rien.
	 */
	public void playCard(String cardName) {
		Card playedCard=this.hand.getCard(cardName);
		this.playCard(playedCard);
	}
	
	/**
	 * Le joueur gagne une carte.
	 * 
	 * @param c carte ÃƒÂ  gagner (ÃƒÂ©ventuellement {@code null})
	 * 
	 * Si la carte n'est pas {@code null}, elle est placÃƒÂ©e sur la dÃƒÂ©fausse du
	 * joueur. On suppose que la carte a correctement ÃƒÂ©tÃƒÂ© retirÃƒÂ©e de son 
	 * emplacement prÃƒÂ©cÃƒÂ©dent au prÃƒÂ©alable.
	 */
	public void gain(Card c) {
		if(c!=null){
			this.discard.add(c);
		}
	}
	
	/**
	 * Le joueur gagne une carte de la rÃƒÂ©serve
	 * 
	 * @param cardName nom de la carte ÃƒÂ  gagner. S'il existe une carte dans la 
	 * rÃƒÂ©serve ayant ce nom, cette carte est retirÃƒÂ©e de la rÃƒÂ©serve et placÃƒÂ©e 
	 * sur la dÃƒÂ©fausse du joueur.
	 * @return la carte qui a ÃƒÂ©tÃƒÂ© ajoutÃƒÂ©e ÃƒÂ  la dÃƒÂ©fausse du joueur, ou {@code 
	 * null} si aucune carte n'a ÃƒÂ©tÃƒÂ© prise dans la rÃƒÂ©serve.
	 */
	public Card gain(String cardName) {
		Card c=this.draw.getCard(cardName);
		this.draw.remove(c);
		this.discard.add(c);
		return c;
	}
	
	/**
	 * Le joueur achÃƒÂ¨te une carte de la rÃƒÂ©serve
	 * 
	 * La mÃƒÂ©thode cherche une carte dans la rÃƒÂ©serve dont le nom est ÃƒÂ©gal au
	 * paramÃƒÂ¨tre, puis vÃƒÂ©rifie que le joueur a assez de piÃƒÂ¨ces pour l'acheter 
	 * et au moins un achat disponible.
	 * Si le joueur peut acheter la carte, le coÃƒÂ»t de la carte est soustrait ÃƒÂ 
	 * l'argent du joueur, le nombre d'achats disponibles est dÃƒÂ©crÃƒÂ©mentÃƒÂ© de 1 
	 * et la carte est gagnÃƒÂ©e par le joueur.
	 * 
	 * @param cardName nom de la carte ÃƒÂ  acheter
	 * @return la carte qui a ÃƒÂ©tÃƒÂ© gagnÃƒÂ©e ou {@code null} si l'achat n'a pas eu 
	 * lieu
	 */
	public Card buyCard(String cardName) {
		CardList c=this.game.availableSupplyCards();
		for(int i=0;i<c.size();i++){
			if (c.get(i).getName().equals(cardName)){
				if(this.money>=c.get(i).getCost()&& this.buys<0){
					Card card =this.game.removeFromSupply(cardName);
					this.draw.add(card);
					return card;
				}
			}
		}

		return null;	
	}
	
	/**
	 * Attend une entrÃƒÂ©e de la part du joueur (au clavier) et renvoie le choix
	 *  du joueur.
	 * 
	 * @param instruction message ÃƒÂ  afficher ÃƒÂ  l'ÃƒÂ©cran pour indiquer au joueur
	 * la nature du choix qui est attendu
	 * @param choices une liste de chaÃƒÂ®nes de caractÃƒÂ¨res correspondant aux
	 * choix valides attendus du joueur (la liste sera convertie en ensemble 
	 * par la fonction pour ÃƒÂ©liminer les doublons, ce qui permet de compter 
	 * correctement le nombre d'options disponibles)
	 * @param canPass boolÃƒÂ©en indiquant si le joueur a le droit de passer sans
	 * faire de choix. S'il est autorisÃƒÂ© ÃƒÂ  passer, c'est la chaÃƒÂ®ne de
	 * caractÃƒÂ¨res vide ("") qui signifie qu'il dÃƒÂ©sire passer.
	 * 
	 * @return la mÃƒÂ©thode lit l'entrÃƒÂ©e clavier jusqu'ÃƒÂ  ce qu'un choix valide
	 * soit entrÃƒÂ© par l'utilisateur (un ÃƒÂ©lÃƒÂ©ment de {@code choices} ou
	 * ÃƒÂ©ventuellement la chaÃƒÂ®ne vide si l'utilisateur est autorisÃƒÂ© ÃƒÂ  passer).
	 * Lorsqu'un choix valide est obtenu, il est renvoyÃƒÂ©.
	 * 
	 * Si l'ensemble {@code choices} ne comporte qu'un seul ÃƒÂ©lÃƒÂ©ment et que
	 * {@code canPass} est faux, l'unique choix valide est automatiquement
	 * renvoyÃƒÂ© sans lire l'entrÃƒÂ©e de l'utilisateur.
	 * 
	 * Si l'ensemble des choix est vide, la chaÃƒÂ®ne vide ("") est 
	 * automatiquement renvoyÃƒÂ©e par la mÃƒÂ©thode (indÃƒÂ©pendamment de la valeur de 
	 * {@code canPass}).
	 * 
	 * Exemple d'utilisation pour demander ÃƒÂ  un joueur de rÃƒÂ©pondre ÃƒÂ  une 
	 * question :
	 * <pre>
	 * {@code
	 * List<String> choices = Arrays.asList("y", "n");
	 * String input = p.choose("Do you want to ...? (y/n)", choices, false);
	 * }
	 * </pre>
	 */
	public String choose(String instruction, List<String> choices, boolean canPass) {
		// La liste de choix est convertie en ensemble pour ÃƒÂ©viter les doublons
		Set<String> choiceSet = new HashSet<String>();
		for (String c: choices) {
			choiceSet.add(c);
		}
		if (choiceSet.isEmpty()) {
			// Aucun choix disponible
			return "";
		} else if (choiceSet.size() == 1 && !canPass) {
			// Un seul choix possible (renvoyer cet unique ÃƒÂ©lÃƒÂ©ment)
			return choiceSet.iterator().next();
		} else {
			String input;
			// Lit l'entrÃƒÂ©e de l'utilisateur jusqu'ÃƒÂ  obtenir un choix valide
			while (true) {
				System.out.print("\n\n");
				// affiche l'ÃƒÂ©tat du jeu
				System.out.print(this.game);
				System.out.print("\n");
				// affiche l'ÃƒÂ©tat du joueur
				System.out.print(this);
				System.out.print("\n");
				// affiche l'instruction
				System.out.println(">>> " + instruction);
				System.out.print("> ");
				// lit l'entrÃƒÂ©e de l'utilisateur au clavier
				input = this.game.readLine();
				if (choiceSet.contains(input) || (canPass && input.equals(""))){
					// si une rÃƒÂ©ponse valide est obtenue, elle est renvoyÃƒÂ©e
					return input;
				}
			}
		}
	}
	
	/**
	 * Attend une entrÃƒÂ©e de la part du joueur et renvoie le choix du joueur.
	 * Dans cette mÃƒÂ©thode, la liste des choix est donnÃƒÂ©e sous la forme d'une 
	 * liste de cartes et le joueur doit choisir le nom d'une de ces cartes.
	 * 
	 * @param instruction message ÃƒÂ  afficher ÃƒÂ  l'ÃƒÂ©cran pour indiquer au joueur
	 * la nature du choix qui est attendu
	 * @param choices liste de cartes parmi lesquelles il faut en choisir une
	 * parmi lesquelles l'utilisateur doit choisir
	 * @param canPass boolÃƒÂ©en indiquant si le joueur a le droit de passer sans
	 * faire de choix. S'il est autorisÃƒÂ© ÃƒÂ  passer, c'est la chaÃƒÂ®ne de
	 * caractÃƒÂ¨res vide ("") qui signifie qu'il dÃƒÂ©sire passer.
	 * 
	 * La mÃƒÂ©thode commence par construire une liste de tous les noms des cartes 
	 * dans {@code choices} puis appelle la mÃƒÂ©thode prÃƒÂ©cÃƒÂ©dente pour faire 
	 * choisir un nom parmi cette liste ÃƒÂ  l'utilisateur.
	 * 
	 * Exemple d'utilisation pour faire choisir le nom d'une carte Action de sa
	 * main ÃƒÂ  un joueur (dans cet exemple le joueur n'a pas le droit de passer 
	 * s'il a au moins une carte Action en main, mais la mÃƒÂ©thode peut quand 
	 * mÃƒÂªme renvoyer {@code ""} s'il n'a aucune carte Action en main) :
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
			// tous les noms sont ajoutÃƒÂ©s ÃƒÂ  l'ensemble
			stringChoices.add(c.getName());
		}
		// appel de la mÃƒÂ©thode prÃƒÂ©cÃƒÂ©dente en passant l'ensemble de noms
		return this.choose(instruction, stringChoices, canPass);
	}
	
	/**
	 * DÃƒÂ©marre le tour du joueur
	 * 
	 * Les compteurs d'actions et achats sont mis ÃƒÂ  1
	 */
	public void startTurn() {
		this.buys=1;
		this.actions=1;
	}
	
	/**
	 * Termine le tour du joueur
	 * 
	 * - Les compteurs d'actions, argent et achats du joueur sont remis ÃƒÂ  0
	 * - Les cartes en main et en jeu sont dÃƒÂ©faussÃƒÂ©es
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
	 * ExÃƒÂ©cute le tour d'un joueur
	 * 
	 * Cette mÃƒÂ©thode exÃƒÂ©cute successivement les 5 phases du tour d'un joueur:
	 * 
	 * 1. (PrÃƒÂ©paration) la mÃƒÂ©thode {@code startTurn()} est appelÃƒÂ©e
	 * 
	 * 2. (Action) Tant que le joueur a des actions disponibles, on lui demande 
	 * de choisir le nom d'une carte Action de sa main ÃƒÂ  jouer. Il peut passer ÃƒÂ 
	 * tout moment ÃƒÂ  la phase suivante (soit de maniÃƒÂ¨re forcÃƒÂ©e s'il n'a plus de 
	 * carte Action en main soit volontairement en entrant la chaÃƒÂ®ne vide). 
	 * Lorsqu'il joue une carte Action, la fonction dÃƒÂ©crÃƒÂ©mente son nombre 
	 * d'actions puis joue la carte.
	 * 
	 * 3. (TrÃƒÂ©sor) Le joueur joue toutes les cartes TrÃƒÂ©sor de sa main 
	 * automatiquement (dans le jeu de base il n'y a aucune raison de ne pas 
	 * jouer tous les trÃƒÂ©sors automatiquement).
	 * 
	 * 4. (Achat) Tant que le joueur a au moins un achat disponible, on lui 
	 * demande de choisir le nom d'une carte de la rÃƒÂ©serve qu'il veut acheter. 
	 * Il ne peut acheter que des cartes dont le prix est infÃƒÂ©rieur ÃƒÂ  l'argent 
	 * dont il dispose. Le joueur peut passer (et terminer son tour) ÃƒÂ  tout 
	 * moment pendant cette phase.
	 * 
	 * 5. (Fin) La mÃƒÂ©thode {@code endTurn()} est appelÃƒÂ©e pour terminer le tour 
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

	
	//Méthodes rajoutées
	
	public void drawNCard(int i) {
		
		for(int a=0;a<i;a++) {
			this.drawCard();
		}
	}
}
