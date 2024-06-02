import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
   private Controller control;
   private JLabel labelName1,labelName2,
   labelNbrPion1, nbrPion1, labelNbrPion2, nbrPion2, 
   labelNbrDame1, nbrDame1, labelNbrDame2, nbrDame2, 
   labelNbrKills1, nbrKills1, labelNbrKills2, nbrKills2,
   labelScore1, Score1, labelScore2, Score2;
   
   //led to show the activated Player
   private JLabel lblLedActivateJoueur1, lblLedActivateJoueur2;
 
   
   private JPanel panel, panelInfo;
   private Plateau plateau;

   private JButton buttonExit; 
   
    public GameWindow (String titre,Controller control)
    {
        this.control = control;

        this.setTitle(titre);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setMinimumSize(new Dimension(400,400));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.construireFenetre();
    }
   public void construireFenetre(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch(Exception e){}
        	//w : 600+300
        	//h : 600
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		//create the main panel to add Plateau and the players infos  
		panel = new JPanel();  
		
		panel.setLayout(new GridLayout(1, 2));
	
		//creating new plateau
		plateau = new Plateau(control);
		plateau.setSize(600,600); 
		
		panel.add(plateau); 
		
		construirePanelInfo();
		
	    this.setContentPane(panel);
        
        this.setVisible(true);

    }
   
   
   public void construirePanelInfo() {
	 //create players info panels which will be added to the the main panel
	 		panelInfo = new JPanel();
	 		
	 	   // create a separator
	        JSeparator sep = new JSeparator();
	         
	        // set layout as Horizontal
	        sep.setOrientation(SwingConstants.HORIZONTAL);
	 		
	 		panelInfo.setLayout(new GridLayout(24, 1, 10, 10));
			//add padding to the panel 
	 		panelInfo.setBorder(new EmptyBorder(0, 50, 0, 0));
			
			//create labels
	        labelName1 = new JLabel("Joueur 1 "+control.getPlayer1().getCouleur()+" : "+control.getPlayer1().getName());
	        //add LED to activate player
	        lblLedActivateJoueur1 = new JLabel("•");
	        lblLedActivateJoueur1.setForeground(Color.RED);
	        lblLedActivateJoueur1.setFont(new Font("Verdana", Font.PLAIN, 30));
	        
	        labelNbrPion1 = new JLabel("Nombre de pions : "); 

	        nbrPion1 = new JLabel(""+control.getPlayer1().getNbrPion());  
	        
	        labelNbrDame1 = new JLabel("Nombre de Dame : "); 

	        nbrDame1 = new JLabel(""+control.getPlayer1().getNbrDame());
	        
	        labelNbrKills1 = new JLabel("Nombre de Kills : "); 

	        nbrKills1 = new JLabel(""+control.getPlayer1().getNbrKills()); 

	        labelScore1 = new JLabel("Score : ");  
	        Score1 = new JLabel(""+control.getPlayer1().getScore()); 
	        
	        
	        ///====> second player information
	        labelName2 = new JLabel("Joueur 2 "+control.getPlayer2().getCouleur()+": "+control.getPlayer2().getName());
	        lblLedActivateJoueur2 = new JLabel("•");
	        lblLedActivateJoueur2.setFont(new Font("Verdana", Font.PLAIN, 30));
	        
	        lblLedActivateJoueur2.setForeground(Color.GREEN);
	        
	        labelNbrPion2 = new JLabel("Nombre de pions : ");
	        
	        nbrPion2 = new JLabel(""+control.getPlayer2().getNbrPion()); 
	        
	        labelNbrDame2 = new JLabel("Nombre de Dame : "); 

	        nbrDame2 = new JLabel(""+control.getPlayer2().getNbrDame());

	        labelNbrKills2 = new JLabel("Nombre de Kills : "); 

	        nbrKills2 = new JLabel(""+control.getPlayer2().getNbrKills()); 
	        

	        labelScore2 = new JLabel("Score : "); 

	        Score2 = new JLabel(""+control.getPlayer2().getScore()); 

	        //setup the exit button
	        buttonExit = new JButton("Quitter la partie"); 
	        buttonExit.setSize(140,50);
	        buttonExit.addMouseListener(new ListenerGameWindow(this));

	         

	        //adding components to the panelInfo
	        panelInfo.add(labelName1);
	        panelInfo.add(lblLedActivateJoueur1);
	        
	        
	        panelInfo.add(labelNbrPion1);
	        panelInfo.add(nbrPion1);
	        

	        panelInfo.add(labelNbrDame1);
	        panelInfo.add(nbrDame1);

	        panelInfo.add(labelNbrKills1);
	        panelInfo.add(nbrKills1);
	        

	        panelInfo.add(labelScore1);
	        panelInfo.add(Score1);
	        
	        
	        panelInfo.add(sep);//======================Separator====================

	        panelInfo.add(labelName2); 

	        panelInfo.add(lblLedActivateJoueur2);
	        panelInfo.add(labelNbrPion2); 
	        panelInfo.add(nbrPion2);
	        

	        panelInfo.add(labelNbrDame2);
	        panelInfo.add(nbrDame2);

	        panelInfo.add(labelNbrKills2);
	        panelInfo.add(nbrKills2);
	        

	        panelInfo.add(labelScore2);
	        panelInfo.add(Score2);
	        
	        
	        
	        panelInfo.add(buttonExit);
	        //adding the panels(info and plateau) to the main one
	        panel.add(panelInfo);
   }
   
   private void borderLayoutTpCtrBtm(JPanel Top, JPanel Center, JPanel Bottom){
	   this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
	   this.add(Top);
	   this.add(Center);
	   this.add(Bottom);
	   this.validate();

	   
	   
   }
   
   
   
   public void refresh() {
	   updatePlayer1();
	   updatePlayer2();
	   
   }
   
   
   
   
   
   public void updatePlayer1(){
	   this.nbrPion1.setText(""+control.getPlayer1().getNbrPion());
	   this.nbrKills1.setText(""+control.getPlayer1().getNbrKills());
	   this.nbrDame1.setText(""+control.getPlayer1().getNbrDame());
	    
		 
	   
   }//Fin de la méthode setScore
   
   public void updatePlayer2(){
	   this.nbrPion2.setText(""+control.getPlayer2().getNbrPion()); 
	   this.nbrKills2.setText(""+control.getPlayer2().getNbrKills());
	   this.nbrDame2.setText(""+control.getPlayer2().getNbrDame());
	   
   }//Fin de la méthode setScore
   

   public void activatePlayer1Role() {
	   lblLedActivateJoueur1.setForeground(Color.RED); 
	   lblLedActivateJoueur2.setForeground(Color.GREEN);
   }
   public void activatePlayer2Role() {

	   lblLedActivateJoueur1.setForeground(Color.GREEN); 
	   lblLedActivateJoueur2.setForeground(Color.RED);
   }
   
   public void resetGame() {
		control.getPlayer1().reset();
		control.getPlayer2().reset();
		this.dispose();
		this.construireFenetre();
		
}
   
   public void exitWindow() {
	   System.exit(0); // stop program
		this.dispose(); // close window
		this.setVisible(false); // hide window 
   }

}





