package e.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import e.common.*;
import java.util.*;
import javax.swing.ImageIcon;


class ClientTest {

	@Test
	void test() throws Exception{
		Client client = new Client("Table1");
		Thread.sleep(200);
		HashMap<Food, Integer> foodMap = new HashMap<Food, Integer>();
		Food [] foods= new Food[3];
		for(int i=0; i < 3; i++) {
			foods[i] = new Food("food"+i, new ImageIcon("images/"+i+".jpg"),i);
			client.addFood(foods[i]);
		}
		client.addFood(foods[2]);
		client.addFood(foods[2]);
		client.addFood(foods[2]);
		client.submit();
		Thread.currentThread().sleep(2000);
		System.out.println("client submit: " + client.getAskFoodMap());
		System.out.println("after submit the history: " + client.getHistory());
		System.out.println("FoodMap: " + client.getFoodMap());
		
		Thread.currentThread().sleep(2000);
		client.check();
		Thread.currentThread().sleep(2000);
		System.out.println("after check foodMap: " + client.getFoodMap());
		System.out.println("after check: " + client.getHistory());
		System.out.println("after check askFoodMap: " + client.getAskFoodMap());
		
		Food food = new Food("food4", new ImageIcon("images/1.jpg"),4);
		client.addFood(food);
		client.submit();
		Thread.currentThread().sleep(2000);
		System.out.println("after submit the history: " + client.getHistory());
		System.out.println("after submit the askFoodMap: " + client.getAskFoodMap());
		System.out.println("after submit the FoodMap: " + client.getFoodMap());
		

		
		Thread.currentThread().sleep(2000);
		client.submit();
		Thread.currentThread().sleep(2000);
		System.out.println("after submit the history: " + client.getHistory());
		while(true) {
			
		}
	}

}
