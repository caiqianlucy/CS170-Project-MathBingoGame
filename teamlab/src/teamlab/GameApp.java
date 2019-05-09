package teamlab;
import javax.swing.*;

public class GameApp {
	public static void main( String args[] ) {
		JOptionPane.showMessageDialog(null, "Welcome to CountGame");
		GameFrame bingo = new GameFrame();
		bingo.setVisible(true);
	}
}
