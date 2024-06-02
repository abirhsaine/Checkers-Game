public class Joueur {

    private String name;
    private int nbrPion;
    private int nbrDame;
    private int nbrKills;//nbr de pion que l'adversaire a perdu
    private int Score;
    private Couleur color;//noir ou blanc
    
    
    public Joueur(){}
    public Joueur(String name, Couleur color)
    { 
        this.name = name;
        this.color = color;//Couleur.NOIR  .BLANC
        
        //Default values 
        //taille = 9 cases par ligne ===> 9/2 ~ 4 ==> 4+1 = 5  ==> 5*3 = 15 ===> 15 - 1 = 14 pions 
        this.nbrPion = (Plateau.TAILLE / 2 + 1)* 3 - 1; 
        this.nbrDame = 0;
        this.nbrKills = 0;
        this.Score=0;
    }
    
    public void reset() {
    	//Default values 
        //taille = 9 cases par ligne ===> 9/2 ~ 4 ==> 4+1 = 5  ==> 5*3 = 15 ===> 15 - 1 = 14 pions 
        this.nbrPion = (Plateau.TAILLE / 2 + 1)* 3 - 1; 
        this.nbrDame = 0;
        this.nbrKills = 0;
    }

    // defining getter and setter methods

    public Couleur getCouleur()
    {
        return this.color;
    }

    public void setCouleur(Couleur color)
    {
        this.color = color;
    }
    
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
	public int getNbrPion() {
		return nbrPion;
	}
	public void setNbrPion(int nbrPion) {
		this.nbrPion = nbrPion;
	}
	public int getNbrDame() {
		return nbrDame;
	}
	public void setNbrDame(int nbrDame) {
		this.nbrDame = nbrDame;
	}
	public int getNbrKills() {
		return nbrKills;
	}
	public void setNbrKills(int nbrKills) {
		this.nbrKills = nbrKills;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
    
    
	

	public void incNbrKills() {
		this.nbrKills++;
	}
	

	public void incNbrDame() {
		this.nbrDame++;
	}
	public void decNbrDame() {
		this.nbrDame--;
	}
	

	public void decNbrPion() {
		this.nbrPion--;
	}
	
	public void incScore() {
		this.Score++;
	} 
	
	
	


}
