package e.view;
import javax.swing.*;
import e.model.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.*;
import e.common.*;
public class FoodPanel extends JPanel{

	private HashMap<Food, Integer> foodMap;
	private Client client;
	public FoodPanel(HashMap<Food, Integer> foodMap, Client client) {
		this.foodMap = foodMap;
		this.client = client;
		this.setLayout(new GridLayout(0,1));
		for(HashMap.Entry<Food, Integer> entry: foodMap.entrySet()) {
			FoodView foodView = new FoodView(entry, client);
			this.add(foodView);
		}
	}
	public FoodPanel() {
		
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void setFoodMap(HashMap<Food, Integer> foodMap) {
		this.foodMap = foodMap;
		this.removeAll();
		for(HashMap.Entry<Food, Integer> entry: foodMap.entrySet()) {
			FoodView foodView = new FoodView(entry, client);
			this.add(foodView);
		}
	}
	public HashMap<Food, Integer> getFoodMap(){
		return foodMap;
	}
}
