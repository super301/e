package e.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import e.common.*;
import java.util.*;

import javax.swing.ImageIcon;


class ClientTest {

	@Test
	void test() throws Exception{
		Client client = new Client();
		Thread.sleep(200);
		HashMap<Food, Integer> foodMap = new HashMap<Food, Integer>();
		for(int i=0; i < 5; i++) {
			Food food = new Food("food"+i, new ImageIcon("images/"+i+".jpg"),i);
			foodMap.put(food, i);
		}
		client.setFoodMap(foodMap);
		client.submit();
		Thread.currentThread().sleep(5000);
		System.out.println("client submit: " + client.getFoodMap());
		System.out.println("after submit the history: " + client.getHistory());
	
		while(true) {
			
		}
	}

}
