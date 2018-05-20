package dominion;
import java.util.*;
import dominion.card.*;
import dominion.card.common.*;



/**
 * Class reprÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sentant une partie de Dominion
 */
public class Game {
	/**
	 * Tableau contenant les joueurs de la partie
	 */
	private Player[] players;
	
	/**
	 * Index du joueur dont c'est actuellement le tour
	 */
	private int currentPlayerIndex;
	
	/**
	 * Liste des piles dans la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve du jeu.
	 * 
	 * On suppose ici que toutes les listes contiennent des copies de la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Âªme
	 * carte.
	 * Ces piles peuvent ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Âªtre vides en cours de partie si toutes les cartes de 
	 * la pile ont ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© achetÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es ou gagnÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es par les joueurs.
	 */
	private List<CardList> supplyStacks;
	
	/**
	 * Liste des cartes qui ont ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cartÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es (trash)
	 */
	private CardList trashedCards;
	
	/**
	 * Scanner permettant de lire les entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es au clavier
	 */
	private Scanner scanner;
	
	/**
	 * Constructeur
	 * 
	 * @param playerNames liste des noms des joueurs qui participent ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  la 
	 * partie. Le constructeur doit crÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©er les objets correspondant aux joueurs
	 * @param kingdomStacks liste de piles de rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  utiliser correspondant 
	 * aux cartes "royaume" ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  utiliser dans la partie, auxquelles le 
	 * constructeur doit ajouter les piles "communes":
	 * - 60 Copper
	 * - 40 Silver
	 * - 30 Gold
	 * - 8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province 	 
	 * - 10 * (n-1) Curse oÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¹ n est le nombre de joueurs dans la partie
	 */
	public Game(String[] playerNames, List<CardList> kingdomStacks) {
		
		this.trashedCards=new CardList();
		
		this.scanner=new Scanner(System.in);
		
		this.supplyStacks= kingdomStacks;
		
		players = new Player[playerNames.length];
		
		for(int i=0;i<playerNames.length;i++){
			players[i]= new Player(playerNames[i],this);
		}
		
		
		//On instancie les listes de cartes ÃƒÂ  ajouter ÃƒÂ  supplyStacks
		CardList Copper=new CardList();
		CardList Silver=new CardList();
		CardList Gold=new CardList();
		CardList Duchy=new CardList();
		CardList Province=new CardList();
		CardList Curse=new CardList();
		CardList Estate=new CardList();
		
		//salut yo soy tu madre
		//On ajoute les cartes aux listes #CODE SALE
		
		for(int i=0;i<17;i++){
		this.supplyStacks.add(new CardList());
		}
			
		//Ajout de 60 copper
		for (int i=0;i<60;i++){
			Copper.add(new Copper());
		}
		
		
		//40 Silver
			for (int i=0;i<40;i++){
			Silver.add(new Silver());
		}
		
		//30 Gold
			for (int i=0;i<30;i++){
			Gold.add(new Gold());
		}
		
		//8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province
		
		if(this.players.length==2){
				for (int i=0;i<8;i++){
			Province.add(new Province());
			Duchy.add(new Duchy());
			Estate.add(new Estate());
				}
		}
		else{
			for (int i=0;i<12;i++){
				Province.add(new Province());
				Duchy.add(new Duchy());
				Estate.add(new Estate());
				}
		}
		
		//10 * (n-1) Curse oÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¹ n est le nombre de joueurs dans la partie
		for (int i=0;i<(10*(this.players.length-1));i++){
			Curse.add(new Curse());
		}
		
		
		
		
		this.supplyStacks.add(Copper);
		this.supplyStacks.add(Silver);
		this.supplyStacks.add(Gold);
		this.supplyStacks.add(Duchy);
		this.supplyStacks.add(Province);
		this.supplyStacks.add(Curse);
		this.supplyStacks.add(Estate);
		
		
	

		
	}


	/**
	 * Renvoie le joueur correspondant ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  l'indice passÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© en argument
	 * On suppose {@code index} est un indice valide du tableau 
	 * {@code this.players}
	 * 
	 * @param index indice dans le tableau des joueurs du joueur ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  renvoyer
	 */
	public Player getPlayer(int index) {
		return this.players[index];
	}
	
	/**
	 * Renvoie le nombre de joueurs participant ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  la partie
	 */
	public int numberOfPlayers() {
		return this.players.length;
	}
	
	/**
	 * Renvoie l'indice du joueur passÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© en argument dans le tableau des 
	 * joueurs, ou -1 si le joueur n'est pas dans le tableau.
	 */
	private int indexOfPlayer(Player p) {
		int index=-1;
		for(int i=0;i<numberOfPlayers();i++){
			if (p.equals(this.players[i])){
				index=i;	 
			}
		}
		return index;
			
	}
	
	/**
	 * Renvoie la liste des adversaires du joueur passÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© en argument, dans 
	 * l'ordre dans lequel ils apparaissent ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  partir du joueur {@code p}.
	 * 
	 * @param p joueur dont on veut renvoyer la liste des adversaires. On 
	 * suppose que {@code p} est bien dans le tableau des joueurs.
	 * @return un {@code ArrayList} contenant les autres joueurs de la partie 
	 * en commenÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â§ant par celui qui se trouve juste aprÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨s {@code p} et en 
	 * terminant par celui qui se trouve juste avant (le tableau est considÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© 
	 * comme cyclique c'est-ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â -dire qu'aprÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨s le premier ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©lÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©ment on revient au 
	 * premier).
	 */
	public List<Player> otherPlayers(Player p) {
		List<Player> otherPlayers=new ArrayList<Player>();
		
		int i=this.indexOfPlayer(p);
		
		while(otherPlayers.size()<this.players.length) {
			
			if(i==this.players.length) {
				i=0;
			}
			
				otherPlayers.add(this.players[i]);
				i++;
		}
		
		otherPlayers.remove(0);
		
		return otherPlayers;
	}
	
	/**
	 * Renvoie la liste des cartes qui sont disponibles ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  l'achat dans la 
	 * rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve.
	 * 
	 * @return une liste de cartes contenant la premiÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨re carte de chaque pile 
	 * non-vide de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve (cartes royaume et cartes communes)
	 */
	public CardList availableSupplyCards() {
		CardList avaivableSupplyCards=new CardList();
		for(CardList cL:this.supplyStacks){
			if(cL.size()>0){
				avaivableSupplyCards.add(cL.get(0));
			}
		}
		return avaivableSupplyCards;
	}
	
	/**
	 * Renvoie une reprÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sentation de l'ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©tat de la partie sous forme d'une chaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®ne
	 * de caractÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨res.
	 * 
	 * Cette reprÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©sentation comporte
	 * - le nom du joueur dont c'est le tour
	 * - la liste des piles de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve en indiquant pour chacune :
	 *   - le nom de la carte
	 *   - le nombre de copies disponibles
	 *   - le prix de la carte
	 *   si la pile n'est pas vide, ou "Empty stack" si la pile est vide
	 */
	public String toString() {
		Player currentPlayer = this.players[this.currentPlayerIndex];
		String r = String.format("     -- %s's Turn --\n", currentPlayer.getName());
		for (List<Card> stack: this.supplyStacks) {
			if (stack.isEmpty()) {
				r += "[Empty stack]   ";
			} else {
				Card c = stack.get(0);
				r += String.format("%s x%d(%d)   ", c.getName(), stack.size(), c.getCost());
			}
		}
		r += "\n";
		return r;
	}
	
	/**
	 * Renvoie une carte de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve dont le nom est passÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© en argument.
	 * 
	 * @param cardName nom de la carte ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  trouver dans la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve
	 * @return la carte trouvÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e dans la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve ou {@code null} si aucune carte 
	 * ne correspond
	 */
	public Card getFromSupply(String cardName) {
		
		Card c=null;
		for(CardList cL:this.supplyStacks){
			if(!(cL.getCard(cardName)==null)){//Si une carte non null est rÃƒÂ©tournÃƒÂ©e, on la renvoie
				c=cL.getCard(cardName);
				break;
			}
		}
		return c;
	}
	
	/**
	 * Retire et renvoie une carte de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve
	 * 
	 * @param cardName nom de la carte ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  retirer de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve
	 * @return la carte retirÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve ou {@code null} si aucune carte
	 * ne correspond au nom passÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â© en argument
	 */
	public Card removeFromSupply(String cardName) {
		Card c=null;
		for(CardList cL:this.supplyStacks){
			if(!(cL.getCard(cardName)==null)){//Si une carte non null est rÃƒÂ©tournÃƒÂ©e, on la renvoie
				c=cL.getCard(cardName);
				cL.remove(c);
				break;
			}
		}
		return c;
	}
	
	/**
	 * Teste si la partie est terminÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e
	 * 
	 * @return un boolÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©en indiquant si la partie est terminÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e, c'est-ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â -dire si
	 * au moins l'unedes deux conditions de fin suivantes est vraie
	 *  - 3 piles ou plus de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve sont vides
	 *  - la pile de Provinces de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve est vide
	 * (on suppose que toute partie contient une pile de Provinces, et donc si 
	 * aucune des piles non-vides de la rÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©serve n'est une pile de Provinces, 
	 * c'est que la partie est terminÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e)
	 */
	public boolean isFinished() {
		
		if(this.availableSupplyCards().getCard("Province")==null){
			System.out.println("Plus de cartes Province en réserve");
			return true;
		}
		
		int i=0;
		
		for(CardList cL:this.supplyStacks){
			
			
			if(cL.isEmpty()){
				i++;
			}
		}
		
		if(i>=3) {
			System.out.println("Plus de 3 piles vides");
			return true;
		}
		
		return false;
	
	}
	/**
	 * Boucle d'exÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cution d'une partie.
	 * 
	 * Cette mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode exÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©cute les tours des joueurs jusqu'ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  ce que la partie soit
	 * terminÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e. Lorsque la partie se termine, la mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode affiche le score 
	 * final et les cartes possÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©dÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©es par chacun des joueurs.
	 */
	public void run() {
		while (! this.isFinished()) {
			// joue le tour du joueur courant
			this.players[this.currentPlayerIndex].playTurn();
			// passe au joueur suivant
			this.currentPlayerIndex += 1;
			if (this.currentPlayerIndex >= this.players.length) {
				this.currentPlayerIndex = 0;
			}
		}
		System.out.println("Game over.");
		// Affiche le score et les cartes de chaque joueur
		for (int i = 0; i < this.players.length; i++) {
			Player p = this.players[i];
			System.out.println(String.format("%s: %d Points.\n%s\n", p.getName(), p.victoryPoints(), p.totalCards().toString()));
		}
	}
	
	/**
	 * Lit une ligne de l'entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e standard
	 * 
	 * C'est cette mÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©thode qui doit ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Âªtre appelÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  chaque fois qu'on veut lire
	 * l'entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e clavier de l'utilisateur (par exemple dans Player.choose), ce
	 * qui permet de n'avoir qu'un seul Scanner pour tout le programme
	 * 
	 * @return une chaÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â®ne de caractÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â¨res correspondant ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  la ligne suivante de
	 * l'entrÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â©e standard (sans le retour ÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â  la ligne final)
	 */
	public String readLine() {
		return this.scanner.nextLine();
	}


	
	
	//Méthodes ajoutées
	
	public CardList getTrashedCards() {
		return trashedCards;
	}
	
	public void addToTrash(Card c){
		this.trashedCards.add(c);
	}
	
	public Card removeFromTrash(Card c){
		this.trashedCards.remove(c);
		return c;
	}
	
	public void addToTrash(CardList cL) {//On suppose cL non null
		this.trashedCards.addAll(cL);
	}

	public Player[] getPlayers() {
		return this.players;
	}
	public void hadtrash(Card c) {
		this.trashedCards.add(c);
	}

	
}
