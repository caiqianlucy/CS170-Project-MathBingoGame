package teamlab;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * @author Yiwen <br>
 * This MusicPlayer class contains two methods that involves in playing all sound clips
 * in the game, including background music, correct answer, wrong answer and bingo sound. 
 * The other method is to change the volume of the sound clips in the game.
 */
class MusicPlayer{
	
	static boolean changeVolume;
	
	/**
	 * This method plays all music in the game. If the boolean variable evaluates to
	 * true, then it calls the set volume method to adjust volume and loop music.
	 */
	public static void playMusic(String fileLocation, boolean changeVol) {
		try{
			File filePath = new File(fileLocation);
			if(filePath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(filePath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				if (changeVol) {
					setVol(clip);
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
				else { 
					clip.start();
				}
			}
			
			else {
				JOptionPane.showMessageDialog(null,"Can't find file");		
			}
		}//end of try
		
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
}
	/** This method changes volume of sound clip.
	 * 
	 */
	public static void setVol(Clip clip) {

		FloatControl gain = (FloatControl) 
		clip.getControl(FloatControl.Type.MASTER_GAIN);
		gain.setValue(-10.0f);		
	}

}
