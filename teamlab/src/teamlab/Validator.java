package teamlab;
/* author@ Qian Cai
 * class title and section#: CS-170-01
 * the assignment: lab 4
 * validator class
 */
//Validation class with verification of integer with range, y/n and valid email address for using JOptionPane and try-catch mechanism


import javax.swing.JOptionPane;

public class Validator {
	
	public static int validateIntWithRange(String prompt, int min, int max) {
		boolean isValid = false;
		String strData = null;
		int data = 0;

		 while(!isValid) {
		  try {
	 		strData = JOptionPane.showInputDialog(prompt);
			data = Integer.parseInt(strData);  //will automatically throw NumberFormat Exception	

			if (data < min || data > max) 				//less than min
					throw new Exception("Data is out of range " + min + " - " + max);		//Throw the exception
			isValid = true;   //otherwise data is correct
		  }		//End of try
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "You must enter an integer and please try again...");
		 }						//End of catch
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		 }
		} 						//End of while
		return data;
	 }	//End of validateIntWithRange()


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
