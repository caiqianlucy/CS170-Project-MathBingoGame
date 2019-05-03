package teamlab;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.*;

public class PlayerInfo{

	String name, playerName, scoreDisplay;
	int score = 0;
	String message ="Name" +"   " + "Score";
	TreeMap<Integer, Set<String>> players = new TreeMap<Integer, Set<String>>(new Comparator<Integer>(){
		public int compare(Integer a, Integer b) {
			return b-a;
		}
	});
	List<String> topFive;
	
	public void addName() {
		
		playerName = Validator.validateName("What is your first name(Capital first letter)?");		
	}

	public int getScore() {
		return score;
	}
	
	//Score when player gets the right answer
	public void updateScore() {
		score = score + 1;
		
	}
	
	//Score when player achieves bingo
	public void bingoScore(int time, int size) {
		score += (size-2)*50 + 500/time;
		//System.out.println(score);
	}
	// find the top five player info
	public List<String> findTopFive(TreeMap<Integer, Set<String>> players){
		List<String> res = new ArrayList<>();
		int count = 0;
		for (Integer s: players.keySet()) {
			for (String player: players.get(s)) {
				String info = player + " " + s;
				res.add(info);
				count++;
				if (count == 5) return res;
			}
		}
		return res;
		
	}
	
	public void readDirectory() throws IOException{
		//Create a file object
		File playerData = new File ("TopFiveScore.txt");
		BufferedReader in = new BufferedReader(new FileReader(playerData));
		String line = in.readLine();
		while(line != null) {
			String[] entireLine = line.split(" ");
			name = entireLine[0]; 
			scoreDisplay = entireLine[1];
			int curScore = Integer.valueOf(scoreDisplay);
			if (!players.containsKey(curScore)) players.put(curScore, new HashSet<String>());
			players.get(curScore).add(name);
			line = in.readLine();
		} //End of while
		
	    in.close();
	    //Put current player's info into TreeMap
	    if (!players.containsKey(score)) players.put(score, new HashSet<String>());
	    players.get(score).add(playerName);
    }
	

	//Method for checking score and writing to file
	public void checkScore() throws IOException{
		readDirectory();
		displaySortedMap();

	}//end of method
	
	
    /*This method passes the players' scores to sort by value, display results and write
	to TopFiveScore text file*/
    
	public void displaySortedMap() throws IOException {
		topFive = findTopFive(players);
		PrintWriter out = new PrintWriter(new FileWriter("TopFiveScore.txt", false));
		for(String info: topFive ) {	
		    out.println(info);
			message = message + "\n" + info;
		}
			out.close();
		JOptionPane.showMessageDialog(null, message);		
	}
	
}//end of class
