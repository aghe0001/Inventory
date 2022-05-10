import java.util.Formatter;
import java.util.Scanner;

public class Fruit extends FoodItem {
	/**
	 * Name of the orchard the fruit is from
	 */
	private String orchardName;

	/**
	 * Default Constructor
	 */
	public Fruit() {
		super();
		orchardName = "";
	}

	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		if (super.addItem(scanner, fromFile)) {
			if (!fromFile)
				System.out.print("Enter the name of the orchard supplier: ");
				orchardName = scanner.next();
		}
		return true;
	}

	@Override
	public void outputItem(Formatter writer) {
		writer.format("f\n");
		super.outputItem(writer);
		writer.format("%s\n", orchardName);
	}

	@Override
	public String toString() {
		return super.toString() + " orchard supplier: " + orchardName;
	}
}