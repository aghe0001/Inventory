import java.util.Scanner;
import java.util.regex.Pattern;

/** 
 * Student Name: Kunjesh Aghera
 * Student Number: 040980391 
 * Course: CST8130 - Data Structures     :  CET-CS-Level 3
 * @author/Professor James Mwangi PhD. 
 * @author Linda Crane
 * @author Melissa Sienkiewicz
 */
public class Assign1 {
	/**
	 * Helper method to display menu
	 */
	public static void menu() {
		System.out.println("Please select one of the following:");
		System.out.println("1: Add Item to Inventory");
		System.out.println("2: Display Current Inventory");
		System.out.println("3: Buy Item(s)");
		System.out.println("4: Sell Item(s)");
		System.out.println("5: Search for Item");
		System.out.println("6: Save Inventory to File");
		System.out.println("7: Read Inventory from File");
		System.out.println("8: To Exit");
		System.out.print("> ");
	}

	/**
	 * Main Menu
	 * @param args - Parameters passed into the application
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		input.useDelimiter(Pattern.compile("[\\r\\n]+"));
		Inventory inventory = new Inventory();
		int kunj = 0;
		while (kunj != 8) {
			try {
				menu();
				
				if (input.hasNext(Pattern.compile("[1-8]"))) {
					kunj = input.nextInt();
					switch (kunj) {
					// Add Item
					case 1:
						if (!inventory.addItem(input, false))
							System.out.println("Error...could not add item");
						break;
					// Display Current Inventory
					case 2: 
						System.out.println(inventory);
						break;
					// Buy Item(s)
					case 3:
						if (!inventory.updateQuantity(input, true))
							System.out.println("Error...could not buy item");
						break;
					// Sell Item(s)	
					case 4:
						if (!inventory.updateQuantity(input, false))
							System.out.println("Error...could not sell item");
						break;
					// Search for Item
					case 5: 
						inventory.searchForItem(input);
						break;
					// Save Inventory to File
					case 6: 
						inventory.saveToFile(input);
						break;
					// Read Inventory from File
					case 7: 
						inventory.readFromFile(input);
						break;
					// To Exit
					case 8:
						System.out.println("Exiting...");
						break;
					default:
						System.out.println("Something went wrong");
						break;
					}
				} else {
					System.out.println("In-valid value entered");
					input.next();
				}
			} catch (Exception e) {
				System.out.println("Error Occurred: " + e.getMessage());
			}
		}
		input.close();
	}
}