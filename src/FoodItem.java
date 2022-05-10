import java.util.Formatter;
import java.util.Scanner;


public class FoodItem {

	private int itemCode;

	private float itemCost;

	private String itemName;

	private float itemPrice;

	private int itemQuantityInStock;

	public FoodItem() {
		itemCode = 0;
		itemName = "";
		itemPrice = 0.0f;
		itemCost = 0.0f;
		itemQuantityInStock = 0;
	}

	public boolean addItem(Scanner scanner, boolean fromFile) {
		boolean check = false;

		if (!fromFile)
			System.out.print("Enter the name for the item: ");
			itemName = scanner.next();
		// Input quantity
		while (!check) {
			if (!fromFile)
				System.out.print("Enter the quantity for the item: ");
			if (scanner.hasNextInt()) {
				itemQuantityInStock = scanner.nextInt();
				if (itemQuantityInStock < 0) {
					check = false;
					System.out.println("Invalid input");
					itemQuantityInStock = 0;
				} 
				else
					check = true;
			} 
			else {
				System.out.println("Invalid input");
				scanner.next();
				check = false;
			}
		}

		// Input the cost
		check = false;
		while (!check) {
			if (!fromFile)
				System.out.print("Enter the cost of the item: ");
			if (scanner.hasNextFloat()) {
				itemCost = scanner.nextFloat();
				if (itemCost < 0) {
					check = false;
					System.out.println("Invalid input");
					itemCost = 0;
				} else
					check = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				check = false;
			}
		}

		// Input the price
		check = false;
		while (!check) {
			if (!fromFile)
				System.out.print("Enter the sales price of the item: ");
			if (scanner.hasNextFloat()) {
				itemPrice = scanner.nextFloat();
				if (itemPrice < 0) {
					check = false;
					System.out.println("Invalid input");
					itemPrice = 0;
				} else
					check = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				check = false;
			}
		}
		return true;
	}


	public int getItemCode() {
		return itemCode;
	}


	public boolean inputCode(Scanner scanner, boolean fromFile) {
		boolean validInput = false;
		while (!validInput) {
			if (!fromFile)
				System.out.print("Enter the code for the item: ");
			if (scanner.hasNextInt()) {
				itemCode = scanner.nextInt();
				validInput = true;
			} else {
				System.out.println("Invalid code");
				// Clear the invalid input
				scanner.next();
			}
		}
		return validInput;
	}


	public boolean isEqual(FoodItem item) {
		return itemCode == item.itemCode;
	}


	public void outputItem(Formatter writer) {
		writer.format("%d\n", itemCode);
		writer.format("%s\n", itemName);
		writer.format("%d\n", itemQuantityInStock);
		writer.format("%.2f\n", itemCost);
		writer.format("%.2f\n", itemPrice);
	}

	@Override
	public String toString() {
		return "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " price: $"
				+ String.format("%.2f", itemPrice) + " cost: $" + String.format("%.2f", itemCost);
	}


	public boolean updateItem(int amount) {
		// If you are removing stock, then check that we have enough stock
		if(amount < 0 && itemQuantityInStock < (amount*-1)) {
			return false;
		}
		itemQuantityInStock += amount;
		return true;
	}

}