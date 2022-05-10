import java.util.Formatter;
import java.util.Scanner;


public class Vegetable extends FoodItem {

	private String farmName;

	public Vegetable() {
		super();
		farmName = "";
	}

	@Override
	public boolean addItem(Scanner scanner, boolean file) {
		if (super.addItem(scanner, file)) {
			if (!file)
				System.out.print("Enter the name of the farm supplier: ");
			farmName = scanner.next();
		}
		return true;
	}

	@Override
	public void outputItem(Formatter writer) {
		writer.format("v\n");
		super.outputItem(writer);
		writer.format("%s\n", farmName);
	}

	@Override
	public String toString() {
		return super.toString() + " farm supplier: " + farmName;
	}
}