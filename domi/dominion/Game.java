package dominion;
import java.util.*;
import dominion.card.*;
import dominion.card.common.*;



/**
 * Class reprÃƒÂ©sentant une partie de Dominion
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
	 * Liste des piles dans la rÃƒÂ©serve du jeu.
	 * 
	 * On suppose ici que toutes les listes contiennent des copies de la mÃƒÂªme
	 * carte.
	 * Ces piles peuvent ÃƒÂªtre vides en cours de partie si toutes les cartes de 
	 * la pile ont ÃƒÂ©tÃƒÂ© achetÃƒÂ©es ou gagnÃƒÂ©es par les joueurs.
	 */
	private List<CardList> supplyStacks;
	
	/**
	 * Liste des cartes qui ont ÃƒÂ©tÃƒÂ© ÃƒÂ©cartÃƒÂ©es (trash)
	 */
	private CardList trashedCards;
	
	/**
	 * Scanner permettant de lire les entrÃƒÂ©es au clavier
	 */
	private Scanner scanner;
	
	/**
	 * Constructeur
	 * 
	 * @param playerNames liste des noms des joueurs qui participent ÃƒÂ  la 
	 * partie. Le constructeur doit crÃƒÂ©er les objets correspondant aux joueurs
	 * @param kingdomStacks liste de piles de rÃƒÂ©serve ÃƒÂ  utiliser correspondant 
	 * aux cartes "royaume" ÃƒÂ  utiliser dans la partie, auxquelles le 
	 * constructeur doit ajouter les piles "communes":
	 * - 60 Copper
	 * - 40 Silver
	 * - 30 Gold
	 * - 8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province 	 
	 * - 10 * (n-1) Curse oÃƒÂ¹ n est le nombre de joueurs dans la partie
	 */
	public Game(String[] playerNames, List<CardList> kingdomStacks) {
		
		this.scanner=new Scanner(System.in);
		
		this.supplyStacks= kingdomStacks;
		
		players = new Player[playerNames.length];
		
		for(int i=0;i<playerNames.length;i++){
			players[i]= new Player(playerNames[i],this);
		}
		
		
		//On instancie les listes de cartes à ajouter à supplyStacks
		CardList Copper=new CardList();
		CardList Silver=new CardList();
		CardList Gold=new CardList();
		CardList Duchy=new CardList();
		CardList Province=new CardList();
		CardList Curse=new CardList();
		CardList Estate=new CardList();
		
		
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
		
		//10 * (n-1) Curse oÃƒÂ¹ n est le nombre de joueurs dans la partie
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
	 * Renvoie le joueur correspondant ÃƒÂ  l'indice passÃƒÂ© en argument
	 * On suppose {@code index} est un indice valide du tableau 
	 * {@code this.players}
	 * 
	 * @param index indice dans le tableau des joueurs du joueur ÃƒÂ  renvoyer
	 */
	public Player getPlayer(int index) {
		return this.players[index];
	}
	
	/**
	 * Renvoie le nombre de joueurs participant ÃƒÂ  la partie
	 */
	public int numberOfPlayers() {
		return this.players.length;
	}
	
	/**
	 * Renvoie l'indice du joueur passÃƒÂ© en argument dans le tableau des 
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
	 * Renvoie la liste des adversaires du joueur passÃƒÂ© en argument, dans 
	 * l'ordre dans lequel ils apparaissent ÃƒÂ  partir du joueur {@code p}.
	 * 
	 * @param p joueur dont on veut renvoyer la liste des adversaires. On 
	 * suppose que {@code p} est bien dans le tableau des joueurs.
	 * @return un {@code ArrayList} contenant les autres joueurs de la partie 
	 * en commenÃƒÂ§ant par celui qui se trouve juste aprÃƒÂ¨s {@code p} et en 
	 * terminant par celui qui se trouve juste avant (le tableau est considÃƒÂ©rÃƒÂ© 
	 * comme cyclique c'est-ÃƒÂ -dire qu'aprÃƒÂ¨s le premier ÃƒÂ©lÃƒÂ©ment on revient au 
	 * premier).
	 */
	public List<Player> otherPlayers(Player p) {
		List<Player> otherPlayers=new ArrayList<Player>();
		int i=indexOfPlayer(p)+1;
		while (i<this.players.length){ //penser à check la condition
			if(i==indexOfPlayer(p)){ // Si on retourne à l'index du joueur p, on sort de la boucle, sinon, on ajoute le joueur à l'index i à notre liste otherPlayers
				break;
			}
			else{
				otherPlayers.add(this.players[i]);
				i++;
			}
			
			if(this.players.length==i+1){//Si on arrive en bout de tableau, ben on retourne au début :o
				i=0;
			}
		}
		return otherPlayers;
	}
	
	/**
	 * Renvoie la liste des cartes qui sont disponibles ÃƒÂ  l'achat dans la 
	 * rÃƒÂ©serve.
	 * 
	 * @return une liste de cartes contenant la premiÃƒÂ¨re carte de chaque pile 
	 * non-vide de la rÃƒÂ©serve (cartes royaume et cartes communes)
	 */
	public CardList availableSupplyCards() {
		CardList avaivableSupplyCards=new CardList();
		for(CardList cL:this.supplyStacks){
			if(!(cL.size()==0)){//Si la pile en cours n'est pas vide, on ajoute la première carte à avaivableSupplyCards
				avaivableSupplyCards.add(cL.get(0));
			}
		}
		return avaivableSupplyCards;
	}
	
	/**
	 * Renvoie une reprÃƒÂ©sentation de l'ÃƒÂ©tat de la partie sous forme d'une chaÃƒÂ®ne
	 * de caractÃƒÂ¨res.
	 * 
	 * Cette reprÃƒÂ©sentation comporte
	 * - le nom du joueur dont c'est le tour
	 * - la liste des piles de la rÃƒÂ©serve en indiquant pour chacune :
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
	 * Renvoie une carte de la rÃƒÂ©serve dont le nom est passÃƒÂ© en argument.
	 * 
	 * @param cardName nom de la carte ÃƒÂ  trouver dans la rÃƒÂ©serve
	 * @return la carte trouvÃƒÂ©e dans la rÃƒÂ©serve ou {@code null} si aucune carte 
	 * ne correspond
	 */
	public Card getFromSupply(String cardName) {
		
		Card c=null;
		for(CardList cL:this.supplyStacks){
			if(!(cL.getCard(cardName).equals(null))){//Si une carte non null est rétournée, on la renvoie
				c=cL.getCard(cardName);
				break;
			}
		}
		return c;
	}
	
	/**
	 * Retire et renvoie une carte de la rÃƒÂ©serve
	 * 
	 * @param cardName nom de la carte ÃƒÂ  retirer de la rÃƒÂ©serve
	 * @return la carte retirÃƒÂ©e de la rÃƒÂ©serve ou {@code null} si aucune carte
	 * ne correspond au nom passÃƒÂ© en argument
	 */
	public Card removeFromSupply(String cardName) {
		Card c=null;
		for(CardList cL:this.supplyStacks){
			if(!(cL.getCard(cardName).equals(null))){//Si une carte non null est rétournée, on la renvoie
				c=cL.getCard(cardName);
				cL.remove(c);
				break;
			}
		}
		return c;
	}
	
	/**
	 * Teste si la partie est terminÃƒÂ©e
	 * 
	 * @return un boolÃƒÂ©en indiquant si la partie est terminÃƒÂ©e, c'est-ÃƒÂ -dire si
	 * au moins l'unedes deux conditions de fin suivantes est vraie
	 *  - 3 piles ou plus de la rÃƒÂ©serve sont vides
	 *  - la pile de Provinces de la rÃƒÂ©serve est vide
	 * (on suppose que toute partie contient une pile de Provinces, et donc si 
	 * aucune des piles non-vides de la rÃƒÂ©serve n'est une pile de Provinces, 
	 * c'est que la partie est terminÃƒÂ©e)
	 */
	public boolean isFinished() {
		
		boolean isFinished=false;
		int emptyLists=0;
		for(CardList cL:this.supplyStacks){
			if(cL.size()==0){//Si une carte non null est rétournée, on la renvoie
				emptyLists++;
			}
			if(emptyLists>=3){
				isFinished=true;
				break;
			}
		}
		if(supplyStacks.get(4).size()==0){
			isFinished=true;
		}
		
		return isFinished;
	}
	/**
	 * Boucle d'exÃƒÂ©cution d'une partie.
	 * 
	 * Cette mÃƒÂ©thode exÃƒÂ©cute les tours des joueurs jusqu'ÃƒÂ  ce que la partie soit
	 * terminÃƒÂ©e. Lorsque la partie se termine, la mÃƒÂ©thode affiche le score 
	 * final et les cartes possÃƒÂ©dÃƒÂ©es par chacun des joueurs.
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
	 * Lit une ligne de l'entrÃƒÂ©e standard
	 * 
	 * C'est cette mÃƒÂ©thode qui doit ÃƒÂªtre appelÃƒÂ©e ÃƒÂ  chaque fois qu'on veut lire
	 * l'entrÃƒÂ©e clavier de l'utilisateur (par exemple dans Player.choose), ce
	 * qui permet de n'avoir qu'un seul Scanner pour tout le programme
	 * 
	 * @return une chaÃƒÂ®ne de caractÃƒÂ¨res correspondant ÃƒÂ  la ligne suivante de
	 * l'entrÃƒÂ©e standard (sans le retour ÃƒÂ  la ligne final)
	 */
	public String readLine() {
		return this.scanner.nextLine();
	}
	
}
