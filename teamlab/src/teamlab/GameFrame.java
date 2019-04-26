package teamlab;

import java.awt.*;


import javax.swing.*;



public class GameFrame extends JFrame{
	public GameFrame() {
		setTitle("Game name?");
		setSize(356,300);
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
