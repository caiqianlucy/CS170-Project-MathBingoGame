package teamlab;
import javax.swing.*;

public class GameApp {
	public static void main( String args[] ) {
		JOptionPane.showMessageDialog(null, "Welcome to CountAndBingo!");
		GameFrame bingo = new GameFrame();
		bingo.setVisible(true);
	}
}
