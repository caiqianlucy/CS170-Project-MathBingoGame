/* author@ Qian Cai
 Clock class to display timer
 */
package teamlab;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Clock {
	Timer timer = new Timer(); //set a timer to rank player's performance
	int sec = 0; //how much the time past;
	JLabel clockLabel;
	public Clock() {
		timer = new Timer();
		sec = 0;
		clockLabel = new JLabel(sec + "seconds");
	}
	TimerTask task = new TimerTask() {
		public void run() {
			String s = "Timer:" + sec + "seconds";
			clockLabel.setText(s);
			sec++;			
			//JOptionPane.showMessageDialog(null, s);
		}
	};
	public void start() {
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}
	public void stop() {
		timer.cancel();
	}
}
