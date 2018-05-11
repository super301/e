package e.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import javax.swing.ImageIcon;
class FoodTest {

	HashMap<Food, Integer> foodMap;
	Food[] food = new Food[5];
	@BeforeEach
	void setUp() {
		foodMap = new HashMap<Food, Integer>();
		for(int i=0; i < 5; i++) {
			food[i] = new Food("food"+i, new ImageIcon("images/"+i+".jpg"),i);
			foodMap.put(food[i], i);
		
		}
	}
	@Test
	void testLength() {
		assertEquals(5,foodMap.size());
	}
	@Test
	void testKey() {
		for(int i = 0; i < 5; i++) {
			assertTrue(foodMap.containsKey(food[i]));
		}
	}
	@Test
	void testMessage() {
		String init = "init client";
		String submit = "submit foodMap";
		String get_submit = "server has got submit";
		String check = "ask fo check";
		String get_check = "server has got check";
		switch(init) {
		case Message.INIT:
			System.out.println("match init");
			break;
			default:
				System.out.println("no match");
		}
	}

}
