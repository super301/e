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
		init();
	}
	public void init() {
		writer.println(Message.INIT);
		writer.flush();
	}
	public void submit() throws IOException{
		writer.print(Message.SUBMIT);
		writer.flush();
		oos.writeObject(foodMap);
	}
	@Override
	public void run(){
		// TODO Auto-generated method stub
		System.out.println("in run");
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
						System.out.println("Client get" + foodList);
						break;
					default:
						break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void addToHistory() {  //–Ë“™≤‚ ‘
		
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
