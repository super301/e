package e.view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import e.common.Food;

import javax.swing.*;
class FoodViewTest {

	

	@Test
	void test() {
		HashMap<Food, Integer> foodMap = new HashMap<Food, Integer>();
	//	for(int i=0; i < 3; i++) {
			Food food = new Food("food", new ImageIcon("images/1.jpg"),2.0);
			foodMap.put(food, 5);
//		}
		FoodView foodView = null;
		JFrame jf = new JFrame();
		for(HashMap.Entry entry: foodMap.entrySet())
	//		foodView = new FoodView(entry);
		jf.add(foodView);
		jf.setVisible(true);
		jf.setSize(300, 400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while(true) {
			
		}
	}

}
