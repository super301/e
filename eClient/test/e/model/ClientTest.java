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
		for(int i=0; i < 3; i++) {
			Food food = new Food("food"+i, new ImageIcon("images/"+i+".jpg"),i);
			foodMap.put(food, i);
		}
		client.setFoodMap(foodMap);
		client.submit();
		Thread.currentThread().sleep(2000);
		System.out.println("client submit: " + client.getFoodMap());
		System.out.println("after submit the history: " + client.getHistory());
	/*
		client.submit();
		Thread.currentThread().sleep(1000);
		System.out.println("after submit the history: " + client.getHistory());
	*/
		Thread.currentThread().sleep(2000);
		client.check();
		Thread.currentThread().sleep(2000);
	//	assertTrue(client.getFoodMap().isEmpty());
	//	assertTrue(client.getFoodMap().isEmpty());
		System.out.println("after check foddMap: " + client.getFoodMap());
		System.out.println("after check: " + client.getHistory());
		
		for(int i=0; i < 3; i++) {
			Food food = new Food("food"+i, new ImageIcon("images/"+i+".jpg"),i);
			foodMap.put(food, i+2);
		}
		client.setFoodMap(foodMap);
		client.submit();
		Thread.currentThread().sleep(1000);
		System.out.println("after submit the history: " + client.getHistory());
		

		Thread.currentThread().sleep(2000);
		client.submit();
		Thread.currentThread().sleep(2000);
		System.out.println("after submit the history: " + client.getHistory());
		while(true) {
			
		}
	}

}
