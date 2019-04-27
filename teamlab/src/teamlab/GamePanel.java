package teamlab;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class GamePanel extends JPanel implements ActionListener{
	GameInfo bingo = new GameInfo();
	JButton[][]  matrix = new JButton[bingo.size][bingo.size];	//make space for buttons
	JPanel Buttons,questions, imagePanel; //buttons place and questions place
	JTextArea background;	//question's background
	GamePanel()
	{
		bingo.SetButton();
		bingo.SetImageNum();
		SetResultButton();
		this.add(Buttons);
		
		Setquestionboard();
		this.add(questions);
	}
	private void Setquestionboard()
	{
		//questions
		questions = new JPanel();
		questions.setLayout(new BoxLayout(questions, BoxLayout.PAGE_AXIS));
		background = new JTextArea(2,3);
		background.setText("How many peguins are here?");
		background.setEditable(false);
		questions.add(background);	
		imagePanel = new JPanel();
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));
		resetImagePanel();
		questions.add(imagePanel);
		JButton check = new JButton("check");
		check.setBackground(Color.GREEN);
		check.setOpaque(true);
		questions.add(check);
	}
	//reset the number of images for count
	private void resetImagePanel() {
		imagePanel.removeAll();
		imagePanel.revalidate();
		imagePanel.repaint();
		ImageIcon peguin = new ImageIcon("images/peguin.png");
		//JOptionPane.showMessageDialog(null, ""+bingo.ImageNum);
		for (int i = 0; i < bingo.ImageNum; i++) {
			imagePanel.add(new JLabel(peguin));
		}
		
	}
	private void SetResultButton()
	{
		Buttons = new JPanel(new GridLayout(bingo.size, bingo.size));//set buttons Layout
		for(int i = 0; i < bingo.size; i++) 
		{
			for(int j = 0; j < bingo.size; j++)
			{   
				ImageIcon original = new ImageIcon("images/original.png"); //origianl image
				matrix[i][j] = new JButton(bingo.ButtonResult[i][j], original); 	//make new button with name and original image on it
				Buttons.add(matrix[i][j]);	//push on to frame
				//Display style
				matrix[i][j].setBackground(Color.BLUE);
				//matrix[i][j].setForeground(Color.BLUE);
				//matrix[i][j].setFocusPainted(false);
				matrix[i][j].setOpaque(true);
				matrix[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
				//event activity
				matrix[i][j].addActionListener(this);
			}
		}
		//Buttons.setSize(1000,1000);
	}
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		for(int i = 0; i < bingo.size; i++) 
		{
			for(int j = 0; j < bingo.size; j++)
			{
				if (source == matrix[i][j])
				   {
						//tapped change color
						matrix[i][j].setBackground(Color.RED);
						//if correct change swap tapped[][] to true, reset image number, reset question, check bingo
						if(Integer.valueOf(bingo.ButtonResult[i][j])==bingo.ImageNum)
						{   
						   matrix[i][j].setIcon(new ImageIcon("images/haha.png"));
							//JOptionPane.showMessageDialog(null, "correct!");
							bingo.tapped[i][j] = true;		//swap tapped place to true
							bingo.ButtonResult[i][j] = Integer.toString(bingo.range+1);	//set the result to avoid duplicate
							bingo.SetImageNum();	//reset image number
							resetImagePanel();	//reset question
							//check if bingo
							if(bingo.CheckBingo())
								JOptionPane.showMessageDialog(null, "bingo！");
						}
						else	//else tell player wrong and swap back color
						{
							JOptionPane.showMessageDialog(null, "please try again!");
							matrix[i][j].setBackground(Color.BLUE);
							matrix[i][j].setIcon(new ImageIcon("images/tryAgain.png"));
						}
				   }
			}
		}
	}
}
