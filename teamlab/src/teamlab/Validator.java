package teamlab;
/* author@ Qian Cai
 * class title and section#: CS-170-01
 * the assignment: Project
 * validator class
 */
//Validation class with  y/n and valid email address for using JOptionPane and try-catch mechanism


import javax.swing.JOptionPane;


public class Validator {
	
	/**
	 * Validate yes or no options
	 */
	public static String validateYN(String prompt) {
		boolean isValid = false; 
		String choice = "";
		while (!isValid) {
			try {
				choice = JOptionPane.showInputDialog(prompt);
				if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n"))
					isValid = true;
				else
					throw new Exception("Invalid choice and try again...");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				continue;
			}
		}   //end of while loop
		return choice;
	}  //end of validateYN()
	
	/**
	 * Validate name input
	 */
	public static String validateName(String prompt) {
		boolean isValid = false; 
		String name = "";
		while (!isValid) {
			try {
				name = JOptionPane.showInputDialog(prompt);
				if (name.matches("[A-Z][a-z]*"))
					isValid = true;
				else
					throw new Exception("Invalid name and try again...");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				continue;
			}
		}   //end of while loop
		return name;
	}  //end of validateName()
}	   //End of Validtor
