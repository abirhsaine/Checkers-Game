import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Plateau extends JPanel {

	private static final long serialVersionUID = 6726708245444190460L;

	public static final int TAILLE=9;

	private Case caseActive;
	
	private boolean tourNoir;
	
	private Controller control;
	

	public Plateau(Controller control){
		this.control = control;
		
		tourNoir=false;
		setLayout(new GridLayout(TAILLE, TAILLE));
		for(int i=0; i<TAILLE; i++){
			for(int j=0; j<TAILLE; j++){
				if((j%2==0 && i%2==0) || (j%2!=0 && i%2!=0)){
					ajouterCase(Couleur.NOIR);
				}
				else{
					ajouterCase(Couleur.BLANC);
				}
			}
		}
		init();
	}

	private void ajouterCase(Couleur couleur){
		Case case1 = new Case(couleur);
		case1.addMouseListener(new ListenerCase(case1, this));
		add(case1);
	}

	private Pion creerPion(Couleur couleur, boolean monte){
		Pion pion = new Pion(couleur, monte);
		pion.addMouseListener(new ListenerPion(pion, this));
		return pion;
	}

	private void init(){
		//TAILLE*3 ===> display 3 rows only
		//j+=2 ===. J= J+2 ==> insert the pion in each 2 cases separated with one empty case
		for(int j=0; j<TAILLE*3; j+=2){
			getCase(j).add(creerPion(Couleur.NOIR, false));
			getCase(TAILLE*TAILLE-j-1).add(creerPion(Couleur.BLANC, true));
		}
	}

	public Case getCase(int i, int j){
		return (Case) getComponent(j+i*TAILLE);
	}// pour avoir l'indice de la case dans le plateau on commence à 0 jusqu'à 100

	public Case getCase(int i){
		return (Case) getComponent(i);
	}

	public void afficherPossibilites(Pion p){ //on commence par les pions blanc par défaut
		if((p.getCouleur().equals(Couleur.NOIR) && tourNoir) || (p.getCouleur().equals(Couleur.BLANC) && !tourNoir)){
			int i=0;
			int j=0;
			for(int k=0; k<TAILLE*TAILLE; k++){
				getCase(k).setSelectionnee(false);
				if(getCase(k).getComponentCount()!=0 && getCase(k).getComponent(0).equals(p)){
					caseActive=getCase(k);
					i=k/TAILLE;
					j=k%TAILLE;

				}
			}
			selectionnerCases(i, j, p.getCouleur());// il nous permet d'avoir les coordonées de la case repère où on peut se déplacer  du bas vers le haut à partir de la case 1 selectionnée
		}
	}

	public void selectionnerCases(int i, int j, Couleur couleur){
		Pion pion = (Pion)(getCase(i, j).getComponent(0));
		if(pion.isMonte()){
			if(i-1>=0 && j-1>=0 && getCase(i-1, j-1).getComponentCount()==0){
				getCase(i-1, j-1).setSelectionnee(true); // d'après la case  qu'on a sélectionné on se déplace diagonalement d'une case en haut vers la gauche dans le plateau
			}
			else if(i-2>=0 && j-2>=0 && getCase(i-2, j-2).getComponentCount()==0 && !((Pion)(getCase(i-1, j-1).getComponent(0))).getCouleur().equals(couleur)){
				getCase(i-2, j-2).setSelectionnee(true); // d'après la case  qu'on a sélectionné on se déplace diagonalement de deux cases en haut vers la gauche dans le plateau
			}
			if(i-1>=0 && j+1<TAILLE && getCase(i-1, j+1).getComponentCount()==0){
				getCase(i-1, j+1).setSelectionnee(true);// d'après la case  qu'on a sélectionné on se déplace diagonalement d'une case en haut vers la droite dans le plateau
			}
			else if(i-2>=0 && j+2<TAILLE && getCase(i-2, j+2).getComponentCount()==0 && !((Pion)(getCase(i-1, j+1).getComponent(0))).getCouleur().equals(couleur)){
				getCase(i-2, j+2).setSelectionnee(true); // d'après la case  qu'on a sélectionné on se déplace diagonalement de deux cases en haut vers la droite dans le plateau
			}
		}
		else{
			if(i+1<TAILLE && j+1<TAILLE && getCase(i+1, j+1).getComponentCount()==0){
				getCase(i+1, j+1).setSelectionnee(true); // d'après la case  qu'on a sélectionné on se déplace diagonalement d'une case en bas vers la droite dans le plateau
			}
			else if(i+2<TAILLE && j+2<TAILLE && getCase(i+2, j+2).getComponentCount()==0 && !((Pion)(getCase(i+1, j+1).getComponent(0))).getCouleur().equals(couleur)){
				getCase(i+2, j+2).setSelectionnee(true);// d'après la case  qu'on a sélectionné on se déplace diagonalement de deux cases en bas vers la droite dans le plateau
			}
			if(i+1<TAILLE && j-1>=0 && getCase(i+1, j-1).getComponentCount()==0){
				getCase(i+1, j-1).setSelectionnee(true);// d'après la case  qu'on a sélectionné on se déplace diagonalement d'une case en bas vers la gauche dans le plateau
			}
			else if(i+2<TAILLE && j-2>=0 && getCase(i+2, j-2).getComponentCount()==0 && !((Pion)(getCase(i+1, j-1).getComponent(0))).getCouleur().equals(couleur)){
				getCase(i+2, j-2).setSelectionnee(true);// d'après la case  qu'on a sélectionné on se déplace diagonalement de deux cases en bas vers la gauche dans le plateau
			}
			
		}
	}

	public void deplacer(Case case1){
		
		case1.add(caseActive.getComponent(0));
		for(int k=0; k<TAILLE*TAILLE; k++){
			getCase(k).setSelectionnee(false);
		}
		// case active est la case où on peut se déplacer diagonalement en sautant une case soit vers la gauche ou vers la droite
		if(Math.abs(getLigne(case1)-getLigne(caseActive))==2){ // on obtient les coordonnées(i,j) de la case à absorber
			// si elle a un pion de l'adversaire pour qu'on puisse se déplacer et elle est placée diagonalement entre la case 1 et la case active
			int i = (getLigne(case1)+getLigne(caseActive))/2;
			int j = (getColonne(case1)+getColonne(caseActive))/2;
				//selecting the pion that will be killed by the opponent ==> remove from the Plateau
				Pion p = ((Pion)getCase(i,j).getComponent(0));
				//the current pion has the same color as the first player -===> NOIR
				//decrement Pion Number and increment Kills number
				//if the current pion is a dame decrement the dame's number
				if(p.getCouleur() == Couleur.NOIR){
					control.getPlayer1().decNbrPion();
					control.getPlayer2().incNbrKills();
					if(p.isDame()) {
						control.getPlayer1().decNbrDame();
					}
					
					
					//refresh the information panel ===> update player1 information 
					control.getVuePlateau().refresh();
					

					
				}
				else if(p.getCouleur() == Couleur.BLANC){
					control.getPlayer1().incNbrKills();
					control.getPlayer2().decNbrPion();
					if(p.isDame()) {
						control.getPlayer2().decNbrDame();
					}
					
					control.getVuePlateau().refresh();
					
					
					System.out.println("getNbrPion : "+control.getPlayer1().getNbrPion());
					System.out.println("getNbrPion : "+control.getPlayer2().getNbrKills());
				}
				
				
				
				System.out.println("i:"+i+" j:"+j+" Color:"+p.getCouleur());
		 
			
			getCase(i, j).removeAll();  // effacer et valider l'évenement de la case absorbé (i,j)
			getCase(i, j).validate();
			getCase(i, j).repaint();
			
			//dec nbr pion
			
		}
		if(tourNoir) {
			control.getVuePlateau().activatePlayer1Role();
		}else {
			control.getVuePlateau().activatePlayer2Role();
		}
		
		tourNoir=!tourNoir;
		caseActive.removeAll();
		caseActive.repaint();
		caseActive=null;
		case1.repaint();
		// si la case 1 est dans la première ligne du plateau donc 
		//on peut pas faire monter le pion car on va sortir du plateau
		if(getLigne(case1) == 0){ 
			Pion p=(Pion)(case1.getComponent(0));
			
			if(p.getCouleur() == Couleur.BLANC){
				p.setDame();// p becomes dame
				control.getPlayer2().incNbrDame(); 
				control.getVuePlateau().refresh();
			}
			
			p.setMonte(false);
		}
		// si la case 1 est dans la dernière ligne du plateau donc 
		// on peut  faire monter le pion
		
		if(getLigne(case1)==TAILLE-1){
			Pion p=(Pion)(case1.getComponent(0));
			if(p.getCouleur() == Couleur.NOIR){
				p.setDame();// p becomes dame
				control.getPlayer1().incNbrDame(); 
				control.getVuePlateau().refresh();
			}
			p.setMonte(true);
		}
		
		 
		
		//===> winning case based on the PION number = 0
		 
		if(control.getPlayer1().getNbrPion()==0) {
			control.getPlayer2().incScore();
			int btn_Yes = JOptionPane.showConfirmDialog(this, 
				    "Le gagnant est : " + control.getPlayer2().getName()+"\nVoulez-vous recommencer une nouvelle partie ?",
				    "Congratulation",
				    JOptionPane.YES_NO_OPTION);
			if(btn_Yes == JOptionPane.YES_OPTION) {
				System.out.println("Yeas btn_Yes : " + btn_Yes);
				control.getVuePlateau().resetGame();
			}else {//exit
				control.getVuePlateau().exitWindow(); // close window
			}
			
		}else if(control.getPlayer2().getNbrPion()==0) {
			control.getPlayer2().incScore(); 
			
			int btn_Yes = JOptionPane.showConfirmDialog(this, 
				    "Le gagnant est : " + control.getPlayer1().getName()+"\nVoulez-vous recommencer une nouvelle partie ?", 
				    "Congratulation",
				    JOptionPane.YES_NO_OPTION);
			
			if(btn_Yes == JOptionPane.YES_OPTION) {
				System.out.println("Yeas btn_Yes : " + btn_Yes);
				control.getVuePlateau().resetGame();
			}else {//exit
				control.getVuePlateau().exitWindow(); // close window
			}
		} 
	}

	private int getLigne(Case case1){
		int res=0;
		for(int i=0; i<TAILLE*TAILLE; i+=2){ // le i est l'indice des cases dans le plateau (en comptant les cases)
			//
			if(getCase(i).equals(case1)){
				res=i/TAILLE; // et le res nous donne la position de la case choisie par rapport aux lignes du plateau
			}
		}
		return res;
	}

	private int getColonne(Case case1){
		int res=0;
		for(int i=0; i<TAILLE*TAILLE; i+=2){ // le i est l'indice des cases dans le plateau (en comptant les cases)
			if(getCase(i).equals(case1)){
				res=i%TAILLE; // et le res nous donne la position de la case choisie par rapport aux colonnes du plateau
			}
		}
		return res;
	}
	
	


}
