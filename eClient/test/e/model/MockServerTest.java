package e.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.*;
import java.util.concurrent.*;

import javax.swing.ImageIcon;

import java.io.*;
import java.net.*;
import e.common.*;
class MockServerTest {

	
	private ServerSocket server;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private PrintWriter writer;
	private BufferedReader in;
	private ArrayList<Food> foodList;
	private HashMap<Food, Integer> foodMap;
	private HashMap<Food, Integer> history;
	
	@Test
	void build() throws Exception {
		foodList = new ArrayList<Food>();
		for(int i=0; i < 5; i++) {
			Food food = new Food("food"+i, new ImageIcon("images/"+i+".jpg"),i);
			foodList.add(food);
		}
		try {
			server = new ServerSocket(2020);
			socket = server.accept();
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			writer = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			foodMap = new HashMap<Food, Integer>();
			history = new HashMap<Food, Integer>();
			while(true) {
				String message = "";
				while(true) {
					if(in.ready()) {
						message = in.readLine();
						System.out.println(message);
						break;
					}
				}
				switch(message) {
					case Message.INIT:
						writer.println(Message.INIT);
						writer.flush();
						oos.writeObject(foodList);
						oos.flush();
						break;
					case Message.SUBMIT:
						if(!foodMap.isEmpty()) {
							addToHistory();
						}
						foodMap = (HashMap<Food, Integer>) ois.readObject();
						System.out.println("get foodMap: "+ foodMap);
						addToHistory();
						System.out.println("addToHistory: " + history);
						writer.println(Message.GET_SUBMIT);
						writer.flush();
						break;
					
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void addToHistory() {  //–Ë“™≤‚ ‘
		for(Map.Entry<Food, Integer> entry: foodMap.entrySet()) {
			Food food = entry.getKey();
			if(history.containsKey(food)) {
				history.put(food, history.get(food) + entry.getValue());
			}else {
				history.put(food, entry.getValue());
			}
		}
	}
}
