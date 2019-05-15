package teamlab;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;





public class GameFrame extends JFrame{
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize();
    int x = 3*d.width/4,y = 4*d.height/5;
    
    /**Constructor <br>
     * Set up game frame and push panel on it
     *
     */
    
	public GameFrame() {

		setTitle("CountGame");
		setSize(x,y);
		setResizable(true);
		centerWindow(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel panel = new GamePanel(x,y);		//Create the panel
		panel.setLayout(new FlowLayout());
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.add(panel);
		
	}
	
	/**
	 * This method sets frame to the center of the screen
	 *
	 */
	private void centerWindow(Window w)
	{
	    setLocation((d.width-w.getWidth())/2,
	        (d.height-w.getHeight())/2);
	}
}
