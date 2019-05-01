package teamlab;

import java.io.IOException;
import java.util.*;

public class GameInfo {
	int size = 3, range = 9;	//the size of matrix and the range of random number
	boolean[][] tapped = new boolean[size][size];
	String[][] ButtonResult = new String[size][size];
	int ImageNum = 0;	//to store question which is number of image
    
	PlayerInfo players = new PlayerInfo();
	public void addPlayer()
	{		
		players.addName();
		//playerMap.put (players.getName(),players.getScore());
	}
	
	public void checkPlayerScore() throws IOException {
		players.checkScore();
	}
	
	public void updatePlayerScore() {
		players.updateScore();
	}
	
	public void updateBingoScore(int s) {
		players.bingoScore(s);
	}
	
	public void SetImageNum()
	{
		//check duplicate
		do {
			int col = (int)(Math.random() * size);
			int row = (int)(Math.random() * size);
			ImageNum = Integer.valueOf(ButtonResult[col][row]);
		}while(ImageNum == range+1);	//looping until not duplicate
	}
	public void SetButton()
	{
		int num = 0;
		for (int col = 0; col < size; col ++)
		{
            for (int row = 0; row < size; row++)
            {
            	num = (int)(Math.random() * range + 1);		//convert double random number to integer
            	ButtonResult[col][row] = Integer.toString(num);	//convert integer to string
            }
		}
	}
	
	public boolean CheckBingo()
	{
		boolean colbingo,rowbingo,diagonal1 = true,diagonal2 = true;
		//check col
		for(int i = 0; i < size; i++)
		{
			colbingo = true;rowbingo = true;	//reset true for col and row
			for(int j = 0; j < size; j++)
			{
				if(!tapped[i][j])		//if one column exist false then bingo false
				{
					colbingo = false;
					break;
				}
			}
			if(colbingo)
				return true;
		}
		//check row
		for(int i = 0; i < size; i++)
		{
			rowbingo = true;	//reset true for row
			for(int j = 0; j < size; j++)
			{
				if(!tapped[j][i])		//if one row exist false then bingo false
				{
					rowbingo = false;
					break;
				}
			}
			if(rowbingo)
				return true;
		}
		//check diagonal
		for(int i = 0; i < size; i++)
		{
			colbingo = true;rowbingo = true;	//reset true for col and row
			for(int j = 0; j < size; j++)
			{
				if(i==j&&!tapped[i][j])
				{
					diagonal1 = false;
				}
				if(i == (size-1)-j &&!tapped[i][j])
				{
					diagonal2 = false;
				}
			}	
		}
		if(diagonal1||diagonal2)
			return true;
		
		return false;
	}
}
