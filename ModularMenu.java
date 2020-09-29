/*
 * Purpose: Modular menu prompt
 * Status: Complete and thoroughly tested
 * Last update: 11 Oct 2019
 * Submitted: 11 Oct 2019
 * Comment: For future drivers. Display settings in driver, simple config.
 * @author: Christopher Herras-Antig
 * @version: 11 Oct 2019
 */
public class ModularMenu {
	
	public void startupPrompt(int firstNum, String input[]) {
		System.out.print("Select from the following menu: ");
		
		for (int x = 0; x < input.length; x++ ) {
			System.out.print("\n   " + (x+firstNum) + ". " + input[x]);
		}
		
		System.out.println("\n");
	}
	
	
}
