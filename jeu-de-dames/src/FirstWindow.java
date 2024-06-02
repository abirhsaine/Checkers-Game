import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FirstWindow  extends JFrame /* fenetre enfant de FenetreListe */
{
    private Joueur player1;
    private Joueur player2;
    private String title;
    private Controller control;
    private JLabel labelName1,labelName2;
    private JTextField textName1,textName2;
    private JButton boutonSauver;


    public FirstWindow(String title,Controller control)
    {
        this.title = title;
        //this.player1 = player1;
       // this.player2 = player2;

        this.control = control;

        this.setTitle(title);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(300,300));
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        construireFenetre();
    }



    public void construireFenetre()
    {
        
    	
    	labelName1 = new JLabel("Joueur 1 (Blanc): ");
        labelName1.setLocation(10,10);
        labelName1.setSize(140,20);
        this.add(labelName1);

        textName1 = new JTextField(100);
        textName1.setText("pseudo 1");
        textName1.setLocation(120,10);
        textName1.setSize(140,20);
        textName1.setEnabled(true);
        this.add(textName1);

        labelName2 = new JLabel("Joueur 2 (Noir): ");
        labelName2.setLocation(10,60);
        labelName2.setSize(140,20);
        this.add(labelName2);

        textName2= new JTextField(100);
        textName2.setText("pseudo 2");
        textName2.setLocation(120,60);
        textName2.setSize(140,20);
        this.add(textName2);




        boutonSauver = new JButton("Sauver");
        boutonSauver.setLocation(90,210);
        boutonSauver.setSize(140,50);
        boutonSauver.addActionListener(new MonEcouteur(this));

        this.add(boutonSauver);

        this.pack();
    }

    public class MonEcouteur implements ActionListener
    {
        FirstWindow fenetre;

        public MonEcouteur(FirstWindow fenetre)
        {
            this.fenetre=fenetre;
        }

        public void actionPerformed(ActionEvent e)
        {  
        	//creation des deux joueurs
        	player1 = new Joueur(textName1.getText(), Couleur.NOIR);
        	player2 = new Joueur(textName2.getText(), Couleur.BLANC);
        	
            control.saveJoueur(player1,player2);
        }
    }
}


