import java.util.Comparator;


public class FoodItemComparator implements Comparator<FoodItem> {

	@Override
	public int compare(FoodItem f1, FoodItem f2) {
		
		return f1.getItemCode() - f2.getItemCode();
	}

}