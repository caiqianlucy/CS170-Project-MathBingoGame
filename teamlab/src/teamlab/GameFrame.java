package teamlab;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GameFrame extends JFrame{
	public GameFrame() {
		setTitle("CountGame");
		setSize(800,800);
		setResizable(false);
		centerWindow(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new GamePanel();		//Create the panel
		panel.setLayout(new FlowLayout());
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.add(panel);
	}
	private void centerWindow(Window w)
	{
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension d = tk.getScreenSize();
	    setLocation((d.width-w.getWidth())/2,
	        (d.height-w.getHeight())/2);
	}
}
