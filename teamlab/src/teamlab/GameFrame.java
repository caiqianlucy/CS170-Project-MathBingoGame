package teamlab;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GameFrame extends JFrame{
	//public boolean isBingo;
	public GameFrame(int size, int range) {
		//isBingo = false;
		setTitle("CountGame");
		setSize(600,400);
		setResizable(false);
		centerWindow(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel panel = new GamePanel(size, range);		//Create the panel
		panel.setLayout(new FlowLayout());
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.add(panel);
		//if (panel.checkBingo()) isBingo = true;
		
	}
	private void centerWindow(Window w)
	{
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
	    setLocation((d.width-w.getWidth())/2,
	        (d.height-w.getHeight())/2);
	}
}
