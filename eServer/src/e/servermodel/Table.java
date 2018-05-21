package e.servermodel;
import java.util.*;
import java.util.concurrent.*;
import java.net.*;
import java.io.*;
import e.common.*;
public class Table implements Runnable{

	private String number;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private PrintWriter writer;
	private BufferedReader in;
	private HashMap<Food, Integer> foodMap;
	private HashMap<Food, Integer> history;
	private ExecutorService executorService;
	private boolean isUsing;
	private boolean isConnect;
	public Table() {
	}
	public Table(ExecutorService exec, Socket socket) throws IOException{
		this.executorService = exec;
		this.socket = socket;
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		writer = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		foodMap = new HashMap<Food, Integer>();
		history = new HashMap<Food, Integer>();
		executorService = exec;
		executorService.submit(this);
	}
	public void run() {
		try {
			while(true) {
				String message = "";
				while(true) {
					if(in.ready()) {
						message = in.readLine();
						break;
					}
				}
				switch(message) {
				case Message.INIT:
					String number = in.readLine();
					if(Server.tableNames.contains(number)) {
						writer.println(Message.INIT);
						writer.flush();
						oos.writeObject(Server.foodList);
						oos.flush();
						Server.tables.put(number, this);
					}
					break;
				case Message.SUBMIT:
					HashMap<Food, Integer> temp;
					temp = (HashMap<Food, Integer>) ois.readObject();
					if(!foodMap.isEmpty()) {
						addFoods(temp, foodMap);
					}
					addFoods(foodMap, history);
					writer.println(Message.GET_SUBMIT);
					writer.flush();
					break;
				case Message.CHECK:
					writer.println(Message.GET_CHECK);
					writer.flush();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void addFoods(HashMap<Food, Integer> sour, HashMap<Food, Integer> dest) {
		for(Map.Entry<Food, Integer> entry: sour.entrySet()) {
			Food food = entry.getKey();
			if(dest.containsKey(food)) {
				dest.put(food, dest.get(food) + entry.getValue());
			}else {
				dest.put(food, entry.getValue());
			}
		}
	}
}
