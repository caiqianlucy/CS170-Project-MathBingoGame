package teamlab;
import javax.swing.*;

public class GameApp {
	public static void main( String args[] ) {
		JOptionPane.showMessageDialog(null, "Welcome to CountGame");
		String choice = "y";
		int size = 3;
		int range = 9;
		GameFrame bingo = new GameFrame(size, range);
		bingo.setVisible(true);
	    /*while (choice.equalsIgnoreCase("y")) {
	    	System.out.println(bingo.isBingo);
			if (bingo.isBingo) {
				bingo.dispose();
				size++;
				choice = Validator.validateYN("Do you want to continue to play?y/n");
				if (choice.equalsIgnoreCase("y")) {
					bingo = new GameFrame(size, range);
					bingo.setVisible(true);
				}
			}
		}*/		
	}
}
