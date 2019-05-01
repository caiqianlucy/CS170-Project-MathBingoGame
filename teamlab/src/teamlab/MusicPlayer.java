package teamlab;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

class MusicPlayer{
	
	static boolean changeVolume;
	//Play music 
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
	//Method to change volume
	public static void setVol(Clip clip) {

		FloatControl gain = (FloatControl) 
		clip.getControl(FloatControl.Type.MASTER_GAIN);
		gain.setValue(-10.0f);		
	}

}
