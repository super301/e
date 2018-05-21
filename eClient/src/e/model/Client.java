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
	
	public Client(String number) throws IOException{
		this.number = number;
		socket = new Socket("127.0.0.1", 2020);
		System.out.println("unlock");
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		writer = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("IO unlock");
		exectorService = Executors.newCachedThreadPool();
		exectorService.execute(this);
		history = new HashMap<Food, Integer>();
		foodMap = new HashMap<Food, Integer>();
		init();
	}
	public void init() throws IOException{
		writer.println(Message.INIT);
		writer.flush();
		writer.println(number);
		writer.flush();
	}
	public void submit() throws IOException,Exception{
		writer.println(Message.SUBMIT);
		writer.flush();
		Thread.currentThread().sleep(20);
		oos.writeObject(foodMap);
		oos.flush();
		System.out.println("submit has sent");
	}
	public void check() throws IOException{
		writer.println(Message.CHECK);
		writer.flush();
		
	}
	@Override
	public void run(){
		// TODO Auto-generated method stub
		
		System.out.println("in run");
		
		while(true) {
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
							foodList = (ArrayList<Food>)ois.readObject();
							initFoodMap();
							System.out.println("Client get" + foodList);
							break;
						case Message.GET_SUBMIT:
							System.out.println(this.foodMap);
							addToHistory();
							break;
						case Message.GET_CHECK:
							clearMapAndHistory();
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
	private void clearMapAndHistory() {
		foodMap.clear();
		history.clear();
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

		System.out.println(this.history);
	}
	private void initFoodMap() {
		Iterator<Food> it = foodList.iterator();
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
