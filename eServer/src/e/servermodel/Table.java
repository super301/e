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
	private Server server;
	private boolean isUsing;
	private boolean isConnect;
	private RequestListener listener;
	public Table() {
	}
	public Table(ExecutorService exec, Socket socket, Server server) throws IOException{
		this.executorService = exec;
		this.socket = socket;
		this.server = server;
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
						System.out.println(message);
						break;
					}
				}
				switch(message) {
				case Message.INIT:
					String number = in.readLine();
					if(Server.tableNames.contains(number)) {
						writer.println(Message.INIT);
						writer.flush();
						System.out.println(System.currentTimeMillis());
						oos.writeObject(Server.foodList);
						oos.flush();
						this.number = number;
						server.put(number, this);
					}
					break;
				case Message.SUBMIT:
					HashMap<Food, Integer> temp;
					temp = (HashMap<Food, Integer>) ois.readObject();
					System.out.println("Table temp: " + temp);
					addFoods(foodMap, temp);
					if(listener != null)
						listener.hasRequest();
					break;
				case Message.CHECK:
					doCheck();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void clearMap(HashMap<Food, Integer> map) {
		map.clear();
	}
	public void doSubmit() throws Exception{
		addFoods(history, foodMap);
		clearMap(foodMap);
		writer.println(Message.GET_SUBMIT);
		writer.flush();
		if(listener != null)
			listener.hasRequest();
	}
	public void doCheck() throws Exception{
		clearMap(history);
		clearMap(foodMap);
		writer.println(Message.GET_CHECK);
		writer.flush();
		if(listener != null)
			listener.hasRequest();
	}
	private void addFoods(HashMap<Food, Integer> dest, HashMap<Food, Integer> sour ) {
		for(Map.Entry<Food, Integer> entry: sour.entrySet()) {
			Food food = entry.getKey();
			if(dest.containsKey(food)) {
				dest.put(food, dest.get(food) + entry.getValue());
			}else {
				dest.put(food, entry.getValue());
			}
		}
	}
	public String getNumber() {
		return number;
	}
	public HashMap<Food, Integer> getFoodMap() {
		return foodMap;
	}
	public void setFoodMap(HashMap<Food, Integer> foodMap) {
		this.foodMap = foodMap;
	}
	public HashMap<Food, Integer> getHistory() {
		return history;
	}
	public void setHistory(HashMap<Food, Integer> history) {
		this.history = history;
	}
	public void addRequestListener(RequestListener listener) {
		this.listener = listener;
	}
	
}
