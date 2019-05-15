package teamlab;
///*
// * name: Qian Cai,Yuance Lin,Yiwen Moo
// * class: CS170 #01
// * due: 10:00 pm, Wednesday, May 15
// * description: 
// * 
// */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class GamePanel extends JPanel implements ActionListener{
	GameInfo bingo;		//gameinfo object
	PlayerInfo player;	//playerinfo object
	JButton[][]  matrix;	//a matrix of buttons
	JPanel Buttons,Qboard, background, imagePanel, scorePanel,playPanel; //buttons place and questions place (including imagePanel and resultPanel)
	JTextArea question;	//question's background
	JButton results, playMethod;	//two button below the game
	JTextField rightButtonTextField;	//the textfield will display how many correct tapped
	JLabel rightClickLabel;
	String instruction;	//store the text on how to play
	
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
	
	/**
	 * GamePanel constructor
	 */
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
	
	/**Method for player to check on scoreboard and scores for right attempt
	 * 
	 */
	public void scoreResultButton() {
		scorePanel = new JPanel();
		results = new JButton("Check score:");
		scorePanel.add(results);
		results.setFont(new Font("Arial", Font.PLAIN, 30));
		results.addActionListener(this);
			
		rightClickLabel = new JLabel("Score: ");
		rightClickLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		scorePanel.add(rightClickLabel);

		rightButtonTextField = new JTextField(5);
		rightButtonTextField.setEditable(false);
		rightButtonTextField.setFocusable(false);
		rightButtonTextField.setText(Integer.toString(0));
		rightButtonTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		scorePanel.add(rightButtonTextField);	
	}
	
	/**
	 * Display instructions to play the game
	 */
	public void howToPlay() {
		playPanel = new JPanel();
		playMethod = new JButton("How To Play");
		playMethod.setFont(new Font("Arial", Font.PLAIN, 30));
		playPanel.add(playMethod);
		playMethod.addActionListener(this);
	}
	
	/**set up question board with styles
	*/
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
		question.setFont(new Font("Arial", Font.PLAIN, 27));
		question.setLineWrap(true);
		question.setPreferredSize(new Dimension(1*framewidth/4,60));
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
	
	/**reset the number of images for count
	 * 
	 */
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
	/**resize image with size needed
	 * 
	 */
	private ImageIcon setimage(int x , int y ,String path)
	{
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(x, y,  Image.SCALE_DEFAULT));
	}
	/**set up result button which is matrix on the left of frame
	*/
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
				String button = buttons[Integer.valueOf(bingo.ButtonResult[i][j]) - 1];
				matrix[i][j] = new JButton(setimage(by/bingo.size , by/bingo.size, button));
				Buttons.add(matrix[i][j]);
				//Display style
				matrix[i][j].setBackground(Color.PINK);
				matrix[i][j].setOpaque(true);
				//event activity
				matrix[i][j].addActionListener(this);
			}
		}
	}
	/**Operations <br>
	* start the game with everything set up
	*/
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
		//set how to play instructions
		howToPlay();
		this.add(playPanel);
	}
	
	/**ask user to play again
	*/
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
	
	/**clean frame
	*/
	public void clean()
	{
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	/**Open rank board
	*/
	public void OpenRankboard()
	{
		try {
			player.checkScore();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/** Reflects the actions
	 *
	 */
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
							    	JOptionPane.showMessageDialog(null, "bingo! Congratulations, you earned "+ player.getScore() + " score!");
							    	//increase size
							    	size++;
							    	//start with new size
							    	startGame(size, 9);	
							    } 
							    else {
							    	OpenRankboard();
							    	size = 3;
							    	resetGame(size, 9);
							    }
							}
					    }
						else	//else tell player wrong and swap back color
						{   
							//play error sound
							MusicPlayer.playMusic(error,false);
							matrix[i][j].setBackground(Color.PINK);
							JOptionPane.showMessageDialog(null, "","try again" ,0 ,new ImageIcon("images/try_again.jpg"));
						}
				   }
					
			}//for loop j
		}//for loop i
		if(source == results)
		{
			OpenRankboard();
		}	
		
		if(source == playMethod)
		{
			instruction ="1. Count the number of fruits available on the right.\n" + 
				"2. Find the matching number on the left.\n" + 
				"3. Player who achieves bingo using the shortest time wins!";
			JTextArea txtArea= new JTextArea("How To Play\n" + instruction);
			txtArea.setFont(new Font("Arial", Font.PLAIN, 16));
			txtArea.setLineWrap(true);
			UIManager.put("OptionPane.minimumSize",new Dimension(410,200)); 
			JOptionPane.showMessageDialog(null,txtArea);
		}
	}
}

