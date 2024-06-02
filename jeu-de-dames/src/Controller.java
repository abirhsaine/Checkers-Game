public class Controller
{
    // declaring the variables model and view

    private Model modele;
    private Joueur player1;
    private Joueur player2;
    private GameWindow vuePlateau; //vueL
    private FirstWindow vuePlayer;  //vueM

    // constructor with model

    public Controller(Model modele)
    {
        this.modele = modele;
        this.player1= new Joueur();
        this.player2= new Joueur();
        this.vuePlateau = null;//new GameWindow("jeu de dame",this);
       // this.vuePlayer = new  FirstWindow();

    }

    // ajout de la vue player (m�re de la vue modification)

    public void setVuePlayer(FirstWindow vuePlayer)
    {
        this.vuePlayer = vuePlayer;
    }
    
    

	public Joueur getPlayer1() {
		return player1;
	}

	public Joueur getPlayer2() {
		return player2;
	}

	public void saveJoueur(Joueur player1,Joueur player2)
    {
        // mise a jour du modele : maj de joueur selectionne

        this.player1= player1;
        this.player2 = player2;
        
        this.vuePlateau = new GameWindow("jeu de dame",this);

        this.vuePlayer.dispose();//causes the JFrame window to be destroyed and cleaned up by the operating system
        this.vuePlateau.construireFenetre();
    }
	
	
	

	public GameWindow getVuePlateau() {
		// TODO Auto-generated method stub
		return vuePlateau;
	}
	

}

