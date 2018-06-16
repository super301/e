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
	private HashMap<Food, Integer> askFoodMap;
	private HashMap<Food, Integer> history;
	private String number;
	public static double price;
	public static double totalPrice;
	private BehaviorListener listener;
	
	public Client(String number) throws IOException{
		this.number = number;
		socket = new Socket("127.0.0.1", 2020);
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
		writer = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
		history = new HashMap<Food, Integer>();
		foodMap = new HashMap<Food, Integer>();
		askFoodMap = new HashMap<Food, Integer>();
		try {
			init();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exectorService = Executors.newCachedThreadPool();
		exectorService.execute(this);
	}
	public void init() throws IOException, ClassNotFoundException{
		writer.println(Message.INIT);
		writer.flush();
		writer.println(number);
		writer.flush();
		String message = in.readLine();
		foodList = (ArrayList<Food>)ois.readObject();
		System.out.println("get food done");
		initFoodMap();
	}
	public void submit() throws IOException,Exception{
		if(askFoodMap.size() > 0) {
			writer.println(Message.SUBMIT);
			writer.flush();
			Thread.currentThread().sleep(40);
			oos.reset();
			oos.writeObject(askFoodMap);
			oos.flush();
			System.out.println("submit has sent");
			recevieSubmint();
		}
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
							Thread.currentThread().sleep(100);
							break;
						}
					}
					switch(message) {
						case Message.INIT:
//							Thread.currentThread().sleep(4000);
//							System.out.println(System.currentTimeMillis());
//							foodList = (ArrayList<Food>)ois.readObject();
//							System.out.println("get food done");
//							initFoodMap();
//							System.out.println("Client get" + foodList);
							break;
						case Message.GET_SUBMIT:
						//	recevieSubmint();
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
		Iterator<HashMap.Entry<Food, Integer>> it = foodMap.entrySet().iterator();
		while(it.hasNext()) {
			it.next().setValue(0);
		}
		askFoodMap.clear();
		history.clear();
		listener.action();
	}
	private void addToHistory(HashMap<Food, Integer> dest, HashMap<Food, Integer> sour) {  //–Ë“™≤‚ ‘
		for(Map.Entry<Food, Integer> entry: sour.entrySet()) {
			Food food = entry.getKey();
			if(dest.containsKey(food)) {
				dest.put(food, dest.get(food) + entry.getValue());
			}else {
				dest.put(food, entry.getValue());
			}
		}
	}
	private void recevieSubmint() {
		addToHistory(history, askFoodMap);
		Iterator<HashMap.Entry<Food, Integer>> it = foodMap.entrySet().iterator();
		while(it.hasNext()) {
			it.next().setValue(0);
		}
		askFoodMap.clear();
		listener.action();
	}
	public void addFood(Food food) {
		if(askFoodMap.containsKey(food)) {
			int i = askFoodMap.get(food) + 1;
			askFoodMap.put(food, i);
			foodMap.put(food, i);
		}else {
			askFoodMap.put(food, 1);
			foodMap.put(food, 1);
		}
		listener.action();
	}
	public void delFood(Food food) {
		if(!askFoodMap.containsKey(food)) {
			return ;
		}else {
			int i = askFoodMap.get(food);
			if( i > 0) {
				askFoodMap.put(food, i-1);
				foodMap.put(food, i-1);
				if(i-1 == 0)
					askFoodMap.remove(food);
			}
		}
		listener.action();
	}
	private void initFoodMap() {
		Iterator<Food> it = foodList.iterator();
		while(it.hasNext()) {
			foodMap.put(it.next(), 0);
		}
	}
	
	public HashMap<Food, Integer> getAskFoodMap() {
		return askFoodMap;
	}
	public void setAskFoodMap(HashMap<Food, Integer> askFoodMap) {
		this.askFoodMap = askFoodMap;
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
	public BehaviorListener getListener() {
		return listener;
	}
	public void setListener(BehaviorListener listener) {
		this.listener = listener;
	}
	public void close() {
		try {
			in.close();
			writer.close();
			ois.close();
			oos.close();
			if(!socket.isClosed()) {
				socket.close();
			}
		}catch(IOException e) {
			System.out.println("close error");
		}
	}
	
}
