import java.util.Formatter;
import java.util.Scanner;


public class Preserve extends FoodItem {

	private int jarSize;


	public Preserve() {
		super();
		jarSize = 0;
	}

	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		boolean check = false;
		if (super.addItem(scanner, fromFile)) {
			// Input quantity
			while (!check) {
				if (!fromFile)
					System.out.print("Enter the size of the jar in millilitres: ");
				
				if (scanner.hasNextInt()) {
					jarSize = scanner.nextInt();
					
					if (jarSize < 0) {
						check = false;
						System.out.println("Invalid input");
						jarSize = 0;
					} 
					else {
						check = true;
					}
				} 
				else {
					System.out.println("Invalid input");
					scanner.next();
					check = false;
				}
			}
		}
		return true;
	}

	@Override
	public void outputItem(Formatter writer) {
		writer.format("p\n");
		super.outputItem(writer);
		writer.format("%d\n", jarSize);
	}

	@Override
	public String toString() {
		return super.toString() + " size: " + jarSize + "mL";
	}

}