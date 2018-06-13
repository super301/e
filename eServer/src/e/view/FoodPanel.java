package e.view;
import javax.swing.*;
import java.awt.GridLayout;
import java.util.*;
import e.common.*;
public class FoodPanel extends JPanel{

	private HashMap<Food, Integer> foodMap;
	private JScrollPane jsc;
	public FoodPanel(HashMap<Food, Integer> foodMap) {
		this();
		if(foodMap != null) {
			this.foodMap = foodMap;
			for(HashMap.Entry<Food, Integer> entry: foodMap.entrySet()) {
				FoodView foodView = new FoodView(entry);
				this.add(foodView);
			}
		}
	}
	public FoodPanel() {
		this.setLayout(new GridLayout(0,1));
	}
	
	public void setFoodMap(HashMap<Food, Integer> foodMap) {
		this.foodMap = foodMap;
		this.removeAll();
		for(HashMap.Entry<Food, Integer> entry: foodMap.entrySet()) {
			FoodView foodView = new FoodView(entry);
			this.add(foodView);
		}
	}
	public HashMap<Food, Integer> getFoodMap(){
		return foodMap;
	}
}
