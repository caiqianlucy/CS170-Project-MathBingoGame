package teamlab;

import java.awt.*;
import javax.sound.sampled.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class GamePanel extends JPanel implements ActionListener{
	GameInfo bingo;
	PlayerInfo player;
	JButton[][]  matrix;
	JPanel Buttons,Qboard, background, imagePanel, scorePanel; //buttons place and questions place (including imagePanel and resultPanel)
	JTextArea question;	//question's background
	JButton results;	
	JTextField rightButtonTextField;	
	JLabel rightClickLabel;
	Clock clock; // record the time for the player to get bingo
	int size = 3, range = 9, framewidth,framelength;
	//music path
	String 	bgm = "sound/ukulele.wav",
			Bingo = "sound/bingo.wav",
			correct = "sound/correct.wav",
			error = "sound/error.wav";
	//image path
	String 	reward = "images/haha.png";
			//button = "images/original.png";
	//set up image path for count
	String[] counts = new String[] {
			"images/f1.png", "images/f2.png", "images/f3.png", "images/f4.png",
			"images/f5.png", "images/f6.png", "images/f7.png", "images/f8.png",
			"images/f9.png"
	};
	//image path for button
	String[] buttons = new String[] {
			"images/1.png", "images/2.png", "images/3.png", "images/4.png",
			"images/5.png", "images/6.png", "images/7.png", "images/8.png",
			"images/9.png"
	};
	//GamePanel constructor
	public GamePanel(int x,int y)
	{   
		player = new PlayerInfo();
	    this.framewidth = x;
	    this.framelength = y;
	    player.addName(); // add player for the first time
		startGame(size, range);
		//play background music
		MusicPlayer.playMusic(bgm, true);
	}
	
	//Method for player to check on scoreboard and scores for right attempt
	public void scoreResultButton() {
		scorePanel = new JPanel();
		results = new JButton("Check score:");
		scorePanel.add(results);
		results.addActionListener(this);
			
		rightClickLabel = new JLabel("Score: ");
		scorePanel.add(rightClickLabel);

		rightButtonTextField = new JTextField(5);
		rightButtonTextField.setEditable(false);
		rightButtonTextField.setFocusable(false);
		rightButtonTextField.setText(Integer.toString(0));
		scorePanel.add(rightButtonTextField);	
	}
	private void Setquestionboard()
	{
		//questions
		Qboard = new JPanel();
		Qboard.setLayout(new BoxLayout(Qboard, BoxLayout.PAGE_AXIS));
		//set up timer
		clock.start();
		Qboard.add(clock.clockLabel);
		//set background
		background = new JPanel();
		background.setPreferredSize(new Dimension(1*framewidth/4,4*framelength/5));
		background.setBackground(Color.white);
		//set up questions
		question = new JTextArea();
		question.setText("How many kinds of fruits are here?");
		question.setPreferredSize(new Dimension(1*framewidth/4,30));
		question.setEditable(false);
		background.add(question);	
		//add images
		imagePanel = new JPanel();
		imagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		imagePanel.setPreferredSize(new Dimension(1*framewidth/4,4*framelength/5 - 30));
		resetImagePanel();
		background.add(imagePanel);
		//background in board
		Qboard.add(background);
	}
	//reset the number of images for count
	private void resetImagePanel() {
		imagePanel.removeAll();
		imagePanel.revalidate();
		imagePanel.repaint();
		imagePanel.setBackground(Color.white);
		
		for (int i = 0; i < bingo.ImageNum; i++) {
			ImageIcon fruit = setimage(64, 64, counts[i]);
			imagePanel.add(new JLabel(fruit));
		}
	}
	//resize image with size needed
	private ImageIcon setimage(int x , int y ,String path)
	{
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(x, y,  Image.SCALE_DEFAULT));
	}
	//set up result button which is matrix on the left of frame
	private void SetResultButton()
	{
		//button x and y
		int bx = 2*framewidth/3, by = 4*framelength/5;
		Buttons = new JPanel(new GridLayout(bingo.size, bingo.size));//set buttons Layout
		Buttons.setPreferredSize(new Dimension(bx,by));	//set button panel size
		for(int i = 0; i < bingo.size; i++) 
		{
			for(int j = 0; j < bingo.size; j++)
			{   
				//matrix[i][j] = new JButton(bingo.ButtonResult[i][j],setimage(bx/size , by/size ,button));
				String button = buttons[Integer.valueOf(bingo.ButtonResult[i][j]) - 1];
				matrix[i][j] = new JButton(setimage(by/bingo.size , by/bingo.size, button));
				//matrix[i][j] = new JButton(bingo.ButtonResult[i][j],new ImageIcon(button));
				Buttons.add(matrix[i][j]);	//push on to frame
				//Display style
				matrix[i][j].setBackground(Color.BLUE);
				matrix[i][j].setOpaque(true);
				matrix[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
				//event activity
				matrix[i][j].addActionListener(this);
			}
		}
	}
	//Operations
	//start the game with everything set up
	private void startGame(int s, int r) {
		clean();
		bingo = new GameInfo(s, r);
		clock = new Clock();
		matrix = new JButton[bingo.size][bingo.size];	//make space for buttons
		bingo.SetButton();
		bingo.SetImageNum();
		//9 buttons of answers;
		SetResultButton();
		this.add(Buttons);
		
		//set questions
		Setquestionboard();
		this.add(Qboard);
		//set button to check score
		scoreResultButton();
		this.add(scorePanel);	
	}
	//ask user to play again
	private void resetGame(int s, int r) {
		clean();
		String choice = Validator.validateYN("Do you want to play a new game?y/n");
		if (choice.equalsIgnoreCase("y")) {
			player = new PlayerInfo();
			player.addName(); // add player for the first time
			startGame(s, r);
		} else {
			//JFrame parent = (JFrame) this.getRootPane().getParent();
			//parent.dispose();
			System.exit(0);
		}
	}
	//clean frame
	public void clean()
	{
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	//Open rank board
	public void OpenRankboard()
	{
		try {
			player.checkScore();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		int by = 4*framelength/5;
		for(int i = 0; i < bingo.size; i++) 
		{
			for(int j = 0; j < bingo.size; j++)
			{   
				if (source == matrix[i][j] && !bingo.tapped[i][j])
				   {
						//tapped change color
						matrix[i][j].setBackground(Color.RED);
						//if correct change swap tapped[][] to true, reset image number, reset question, check bingo
						if(Integer.valueOf(bingo.ButtonResult[i][j])==bingo.ImageNum)
						{   
							//play correct sound
							MusicPlayer.playMusic(correct,false);
							//update how many correct tapped and show to user
							player.updateScore();
							rightButtonTextField.setText(Integer.toString(player.getScore()));
							//set correct reward
						    matrix[i][j].setIcon(setimage(by/(2*bingo.size) , by/(2*bingo.size), reward));
							bingo.tapped[i][j] = true;		//swap tapped place to true
							bingo.ButtonResult[i][j] = Integer.toString(bingo.range+1);	//set the result to avoid duplicate
							bingo.SetImageNum();	//reset image number
							resetImagePanel();	//reset question
							//check if bingo
							if(bingo.CheckBingo()) {
								//play bingo sound
							    MusicPlayer.playMusic(Bingo,false);
							    clock.stop();
						    	player.bingoScore(clock.sec, size);	
							    //repeat game until to 5x5 matrix then check score
							    if (size < 5) {		
							    	JOptionPane.showMessageDialog(null, "bingo! congratuation your earned "+ player.getScore() + " score!");
							    	//increase size
							    	size++;
							    	//start with new size
							    	startGame(size, 9);	
							    } 
							    else {
							    	OpenRankboard();
							    	resetGame(3, 9);
							    }
							}
					    }
						else	//else tell player wrong and swap back color
						{   
							//play error sound
							MusicPlayer.playMusic(error,false);
							matrix[i][j].setBackground(Color.BLUE);
						}
				   }
					
			}//for loop j
		}//for loop i
		if(source == results)
		{
			OpenRankboard();
		}	
	}
}

