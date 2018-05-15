package dominion;
import java.util.*;
import dominion.card.*;
import dominion.card.common.*;



/**
 * Class reprÃ©sentant une partie de Dominion
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
	 * Liste des piles dans la rÃ©serve du jeu.
	 * 
	 * On suppose ici que toutes les listes contiennent des copies de la mÃªme
	 * carte.
	 * Ces piles peuvent Ãªtre vides en cours de partie si toutes les cartes de 
	 * la pile ont Ã©tÃ© achetÃ©es ou gagnÃ©es par les joueurs.
	 */
	private List<CardList> supplyStacks;
	
	/**
	 * Liste des cartes qui ont Ã©tÃ© Ã©cartÃ©es (trash)
	 */
	private CardList trashedCards;
	
	/**
	 * Scanner permettant de lire les entrÃ©es au clavier
	 */
	private Scanner scanner;
	
	/**
	 * Constructeur
	 * 
	 * @param playerNames liste des noms des joueurs qui participent Ã  la 
	 * partie. Le constructeur doit crÃ©er les objets correspondant aux joueurs
	 * @param kingdomStacks liste de piles de rÃ©serve Ã  utiliser correspondant 
	 * aux cartes "royaume" Ã  utiliser dans la partie, auxquelles le 
	 * constructeur doit ajouter les piles "communes":
	 * - 60 Copper
	 * - 40 Silver
	 * - 30 Gold
	 * - 8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province 	 
	 * - 10 * (n-1) Curse oÃ¹ n est le nombre de joueurs dans la partie
	 */
	public Game(String[] playerNames, List<CardList> kingdomStacks) {
		
		players = new Player[playerNames.length];
		
		for(int i=0;i<playerNames.length;i++){
			players[i]= new Player(playerNames[i],this);
		}
			
		//Ajout de 60 copper
		for (int i=0;i<60;i++){
			this.supplyStacks.get(0).add(new Copper());
		}
		
		
		//40 Silver
			for (int i=0;i<40;i++){
			this.supplyStacks.get(1).add(new Silver());
		}
		
		//30 Gold
			for (int i=0;i<30;i++){
			this.supplyStacks.get(2).add(new Gold());
		}
		
		//8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province
		
		if(this.players.length==2){
				for (int i=0;i<8;i++){
			this.supplyStacks.get(4).add(new Province());
			this.supplyStacks.get(5).add(new Duchy());
			this.supplyStacks.get(6).add(new Estate());
				}
		}
		else{
			for (int i=0;i<12;i++){
			this.supplyStacks.get(4).add(new Province());
			this.supplyStacks.get(5).add(new Duchy());
			this.supplyStacks.get(6).add(new Estate());
				}
		}
		
		//10 * (n-1) Curse oÃ¹ n est le nombre de joueurs dans la partie
		for (int i=0;i<(10*(this.players.length-1));i++){
			this.supplyStacks.get(3).add(new Curse());
		}
		
	

		
	}
	
	/**
	 * Renvoie le joueur correspondant Ã  l'indice passÃ© en argument
	 * On suppose {@code index} est un indice valide du tableau 
	 * {@code this.players}
	 * 
	 * @param index indice dans le tableau des joueurs du joueur Ã  renvoyer
	 */
	public Player getPlayer(int index) {
		return this.players[index];
	}
	
	/**
	 * Renvoie le nombre de joueurs participant Ã  la partie
	 */
	public int numberOfPlayers() {
		return this.players.length;
	}
	
	/**
	 * Renvoie l'indice du joueur passÃ© en argument dans le tableau des 
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
	 * Renvoie la liste des adversaires du joueur passÃ© en argument, dans 
	 * l'ordre dans lequel ils apparaissent Ã  partir du joueur {@code p}.
	 * 
	 * @param p joueur dont on veut renvoyer la liste des adversaires. On 
	 * suppose que {@code p} est bien dans le tableau des joueurs.
	 * @return un {@code ArrayList} contenant les autres joueurs de la partie 
	 * en commenÃ§ant par celui qui se trouve juste aprÃ¨s {@code p} et en 
	 * terminant par celui qui se trouve juste avant (le tableau est considÃ©rÃ© 
	 * comme cyclique c'est-Ã -dire qu'aprÃ¨s le premier Ã©lÃ©ment on revient au 
	 * premier).
	 */
	public List<Player> otherPlayers(Player p) {
		List<Player> otherPlayers=new ArrayList<Player>();
		int i=indexOfPlayer(p)+1;
		while (i<this.players.length){ //penser � check la condition
			if(i==indexOfPlayer(p)){ // Si on retourne � l'index du joueur p, on sort de la boucle, sinon, on ajoute le joueur � l'index i � notre liste otherPlayers
				break;
			}
			else{
				otherPlayers.add(this.players[i]);
				i++;
			}
			
			if(this.players.length==i+1){//Si on arrive en bout de tableau, ben on retourne au d�but :o
				i=0;
			}
		}
		return otherPlayers;
	}
	
	/**
	 * Renvoie la liste des cartes qui sont disponibles Ã  l'achat dans la 
	 * rÃ©serve.
	 * 
	 * @return une liste de cartes contenant la premiÃ¨re carte de chaque pile 
	 * non-vide de la rÃ©serve (cartes royaume et cartes communes)
	 */
	public CardList availableSupplyCards() {
		CardList avaivableSupplyCards=new CardList();
		for(CardList cL:this.supplyStacks){
			if(!(cL.size()==0)){//Si la pile en cours n'est pas vide, on ajoute la premi�re carte � avaivableSupplyCards
				avaivableSupplyCards.add(cL.get(0));
			}
		}
		return avaivableSupplyCards;
	}
	
	/**
	 * Renvoie une reprÃ©sentation de l'Ã©tat de la partie sous forme d'une chaÃ®ne
	 * de caractÃ¨res.
	 * 
	 * Cette reprÃ©sentation comporte
	 * - le nom du joueur dont c'est le tour
	 * - la liste des piles de la rÃ©serve en indiquant pour chacune :
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
	 * Renvoie une carte de la rÃ©serve dont le nom est passÃ© en argument.
	 * 
	 * @param cardName nom de la carte Ã  trouver dans la rÃ©serve
	 * @return la carte trouvÃ©e dans la rÃ©serve ou {@code null} si aucune carte 
	 * ne correspond
	 */
	public Card getFromSupply(String cardName) {
		
		Card c=null;
		for(CardList cL:this.supplyStacks){
			if(!(cL.getCard(cardName).equals(null))){//Si une carte non null est r�tourn�e, on la renvoie
				c=cL.getCard(cardName);
				break;
			}
		}
		return c;
	}
	
	/**
	 * Retire et renvoie une carte de la rÃ©serve
	 * 
	 * @param cardName nom de la carte Ã  retirer de la rÃ©serve
	 * @return la carte retirÃ©e de la rÃ©serve ou {@code null} si aucune carte
	 * ne correspond au nom passÃ© en argument
	 */
	public Card removeFromSupply(String cardName) {
		Card c=null;
		for(CardList cL:this.supplyStacks){
			if(!(cL.getCard(cardName).equals(null))){//Si une carte non null est r�tourn�e, on la renvoie
				c=cL.getCard(cardName);
				cL.remove(c);
				break;
			}
		}
		return c;
	}
	
	/**
	 * Teste si la partie est terminÃ©e
	 * 
	 * @return un boolÃ©en indiquant si la partie est terminÃ©e, c'est-Ã -dire si
	 * au moins l'unedes deux conditions de fin suivantes est vraie
	 *  - 3 piles ou plus de la rÃ©serve sont vides
	 *  - la pile de Provinces de la rÃ©serve est vide
	 * (on suppose que toute partie contient une pile de Provinces, et donc si 
	 * aucune des piles non-vides de la rÃ©serve n'est une pile de Provinces, 
	 * c'est que la partie est terminÃ©e)
	 */
	public boolean isFinished() {
		
		boolean isFinished=false;
		int emptyLists=0;
		for(CardList cL:this.supplyStacks){
			if(cL.size()==0){//Si une carte non null est r�tourn�e, on la renvoie
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
	 * Boucle d'exÃ©cution d'une partie.
	 * 
	 * Cette mÃ©thode exÃ©cute les tours des joueurs jusqu'Ã  ce que la partie soit
	 * terminÃ©e. Lorsque la partie se termine, la mÃ©thode affiche le score 
	 * final et les cartes possÃ©dÃ©es par chacun des joueurs.
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
	 * Lit une ligne de l'entrÃ©e standard
	 * 
	 * C'est cette mÃ©thode qui doit Ãªtre appelÃ©e Ã  chaque fois qu'on veut lire
	 * l'entrÃ©e clavier de l'utilisateur (par exemple dans Player.choose), ce
	 * qui permet de n'avoir qu'un seul Scanner pour tout le programme
	 * 
	 * @return une chaÃ®ne de caractÃ¨res correspondant Ã  la ligne suivante de
	 * l'entrÃ©e standard (sans le retour Ã  la ligne final)
	 */
	public String readLine() {
		return this.scanner.nextLine();
	}
	
}
