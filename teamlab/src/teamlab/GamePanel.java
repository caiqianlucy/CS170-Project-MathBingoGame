package teamlab;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.StyleConstants;

public class GamePanel extends JPanel implements ActionListener{
	GameInfo bingo = new GameInfo();
	JButton[][]  matrix = new JButton[bingo.size][bingo.size];	//make space for buttons
	JPanel Buttons,questions; //buttons place and questions place
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
		background = new JTextArea(9,10);
		background.setText(bingo.ImageNum + " images");
		background.setEditable(false);
		questions.add(background);
	}
	private void SetResultButton()
	{
		Buttons = new JPanel(new GridLayout(bingo.size, bingo.size));//set buttons Layout
		for(int i = 0; i < bingo.size; i++) 
		{
			for(int j = 0; j < bingo.size; j++)
			{
				matrix[i][j] = new JButton(bingo.ButtonResult[i][j]); 	//make new button with name on it
				Buttons.add(matrix[i][j]);	//push on to frame
				//Display style
				matrix[i][j].setBackground(Color.MAGENTA);
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
						matrix[i][j].setBackground(Color.red);
						//if correct change swap tapped[][] to true, reset image number, reset question, check bingo
						if(Integer.valueOf(bingo.ButtonResult[i][j])==bingo.ImageNum)
						{
							JOptionPane.showMessageDialog(null, "correct!");
							bingo.tapped[i][j] = true;		//swap tapped place to true
							bingo.ButtonResult[i][j] = Integer.toString(bingo.range+1);	//set the result to avoid duplicate
							bingo.SetImageNum();	//reset image number
							background.setText(bingo.ImageNum + " images");	//reset question,
							//check if bingo
							if(bingo.CheckBingo())
								JOptionPane.showMessageDialog(null, "bingo");
						}
						else	//else tell player wrong and swap back color
						{
							JOptionPane.showMessageDialog(null, "please try again!");
							matrix[i][j].setBackground(Color.MAGENTA);
						}
				   }
			}
		}
	}
}
