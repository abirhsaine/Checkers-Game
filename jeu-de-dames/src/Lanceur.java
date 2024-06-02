import javax.swing.JFrame;
import javax.swing.UIManager;


public class Lanceur { 
	
	public static void main(String[] args) {
		Model modele;
		Controller controle;
		FirstWindow vuePlayer;
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e){}
		modele = new Model();
		controle = new Controller(modele);
		/*JFrame f = new JFrame();
		f.setSize(600, 600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Plateau(9));
		f.setVisible(true);*/
		vuePlayer = new FirstWindow("Table des joueurs",controle);
		vuePlayer.setVisible(true);

		controle.setVuePlayer(vuePlayer);

	}

}
////////////////////////////




