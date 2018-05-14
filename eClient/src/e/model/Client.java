package e.model;
import java.net.Socket;
import java.io.*;
import java.util.concurrent.*;
import java.util.*;
import e.common.*;
public class Client implements Runnable{

	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private PrintWriter writer;
	private BufferedReader in;
	private ExecutorService exectorService;
	private ArrayList<Food> foodList;
	private HashMap<Food, Integer> foodMap;
	private HashMap<Food, Integer> history;
	private String number;
	public static double price;
	public static double totalPrice;
	
	public Client() throws IOException{
		socket = new Socket("127.0.0.1", 2020);
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
		writer = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		exectorService = Executors.newCachedThreadPool();
		exectorService.execute(this);
		history = new HashMap<Food, Integer>();
		init();
	}
	public void init() {
		writer.println(Message.INIT);
		writer.flush();
	}
	public void submit() throws IOException{
		writer.println(Message.SUBMIT);
		writer.flush();
		System.out.println("submit has sent");
		oos.writeObject(foodMap);
		oos.flush();
	}
	@Override
	public void run(){
		// TODO Auto-generated method stub
		
		System.out.println("in run");
		
		while(true) {
			try {
				while(true) {
					String message = "";
					try {
						while(true) {
							if(in.ready()) {
								System.out.println("client ready()");
								message = in.readLine();
								System.out.println(message);
								break;
							}
						}
					}catch(IOException e) {
						e.printStackTrace();
					}
					switch(message) {
						case Message.INIT:
							foodList = (ArrayList<Food>)ois.readObject();
							initFoodMap();
							System.out.println("Client get" + foodList);
							break;
						case Message.GET_SUBMIT:
							addToHistory();
							break;
						default:
							break;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
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
	private void initFoodMap() {
		Iterator<Food> it = foodList.iterator();
		foodMap = new HashMap<Food, Integer>();
		while(it.hasNext()) {
			foodMap.put(it.next(), 0);
		}
	}
	public ArrayList<Food> getFoodList() {
		return foodList;
	}
	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public static double getPrice() {
		return price;
	}
	public static void setPrice(double price) {
		Client.price = price;
	}
	public static double getTotalPrice() {
		return totalPrice;
	}
	public static void setTotalPrice(double totalPrice) {
		Client.totalPrice = totalPrice;
	}
	
}
