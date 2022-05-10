import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Inventory {

	private ArrayList<FoodItem> inventory;


	public Inventory() {
		inventory = new ArrayList<FoodItem>(20);
	}


	public boolean addItem(Scanner scanner, boolean fromFile) {
		boolean check = false;
		FoodItem item = null;
		while (!check) {
			if (!fromFile)
				System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
			if (scanner.hasNext(Pattern.compile("[fFvVpP]"))) {
				String choice = scanner.next();
				switch (choice.toLowerCase()) {
					case "f":{
						item = new Fruit();
						break;
					}
					case "v":{
						item = new Vegetable();
						break;
					}
					case "p":{
						item = new Preserve();
						break;
					}
					default:{
						item = new FoodItem();
						break;
					}
				}
				check = true;
			} else {
				System.out.println("Invalid entry");
				scanner.next();
				check = false;
			}
		}
		if (item.inputCode(scanner, fromFile)) {
			if (alreadyExists(item) < 0) {
				if (item.addItem(scanner, fromFile)) {
					insertItem(item);
					return true;
				}
				return false;
			} else {
				System.out.println("Item code already exists");
				return false;
			}
		}
		return true;
	}

	public int alreadyExists(FoodItem item) {
		return binarySearch(item.getItemCode(), 0, inventory.size()-1);
	}

	private int binarySearch(int itemCode, int start, int end) {
		int middle = (start + end) / 2;
		if (start > end) {
			return -1;
		}
		if (inventory.isEmpty()) {
			return -1;
		}
		if (inventory.get(middle).getItemCode() == itemCode) {
			return middle;
		}
		if (inventory.get(middle).getItemCode() > itemCode) {
			return binarySearch(itemCode, start, middle - 1);
		}
		if (inventory.get(middle).getItemCode() < itemCode) {
			return binarySearch(itemCode, middle + 1, end);
		}
		return -1;
	}


	private void insertItem(FoodItem item) {
		// Used to compare FoodItems
		FoodItemComparator comp = new FoodItemComparator();
		for (int i = 0; i < inventory.size(); i++) {
			
			// If the item is greater than the one in inventory, insert, insert here and
			if (comp.compare(inventory.get(i), item) >= 0) {
				inventory.add(i, item);
				return;
			}
		}
		inventory.add(item);
	}


	public void readFromFile(Scanner scanner) {
		try {
			System.out.print("Enter the filename to read from: ");
			String filename = scanner.next();

			File file = new File(filename);
			if (file.exists()) {
				Scanner fileReader = new Scanner(file);
				fileReader.useDelimiter("[\\r\\n]+");
				while (fileReader.hasNext()) {
					if (!addItem(fileReader, true)) {
						System.out.println("Error Found when the file was being read, aborted ...");
						break;
					}
				}
			}
			else
			{
				System.out.println("File Not Found");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}


	public void saveToFile(Scanner scanner) {
		try {
			System.out.print("Enter the filename to save to: ");
			String filename = scanner.next();

			File file = new File(filename);
			file.createNewFile();
			file.setWritable(true);
			Formatter writer = new Formatter(file);
			ListIterator<FoodItem> iter = inventory.listIterator();
			while (iter.hasNext()) {
				iter.next().outputItem(writer);
			}
			writer.flush();
			writer.close();
		} 
		catch (IOException e) {
			System.out.println("Could not create file, " + e.getMessage());
		} 
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}


	public void searchForItem(Scanner scanner) {
		FoodItem itemToSearchFor = new FoodItem();
		itemToSearchFor.inputCode(scanner, false);
		int index = binarySearch(itemToSearchFor.getItemCode(), 0, inventory.size()-1);
		
		if (index == -1)
			System.out.println("Code not found in inventory...");
		else
			System.out.println(inventory.get(index).toString());
	}

	@Override
	public String toString() {
		String returnString = "Inventory:\n";
		ListIterator<FoodItem> items = inventory.listIterator();
		while (items.hasNext())
			returnString += items.next().toString() + "\n";
		return returnString;
	}


	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		// If there are no items then we can't update
		if (inventory.isEmpty())
			return false;

		FoodItem temp = new FoodItem();
		temp.inputCode(scanner, false);
		int index = alreadyExists(temp);
		
		if (index != -1) {
			String buySell = buyOrSell ? "buy" : "sell";
			System.out.print("Enter valid quantity to " + buySell + ": ");

			if (scanner.hasNextInt()) {
				int amount = scanner.nextInt();
				if (amount > 0) {
					return inventory.get(index).updateItem(buyOrSell ? amount : amount * -1);
				}
				else {
					System.out.println("Invalid quantity...");
				}
			}
			else {
				System.out.println("Invalid quantity...");
			}
		}
		return false;
	}
}