package e.servermodel;
import java.util.concurrent.*;

import javax.swing.ImageIcon;

import java.net.*;
import java.sql.ResultSet;
import java.util.*;
import java.io.*;
import e.common.*;
import e.sql.SqlHelper;
public class Server implements Runnable{
	private ServerSocket server;
	public static ArrayList<Food> foodList;
	private ExecutorService executorService;
	private HashMap<String, Table> tables = new HashMap<String, Table>();
	public static ArrayList<String> tableNames = new ArrayList<String>();
	static {
		foodList = new ArrayList<Food>();
//		for(int i=0; i < 8; i++) {
//			Food food = new Food("food"+i, new ImageIcon("images/"+i+".jpg"),i+10);
//			foodList.add(food);
//		}
		for(int i=0; i < 6; i++) {
			tableNames.add("Table"+(i+1));
		}
		updateFoodList();
	}
	public static void updateFoodList() {
		foodList.clear();
		SqlHelper sp = new SqlHelper();
		ResultSet rs = sp.queryExecute("select * from foods");
		try {
			while(rs.next()) {
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				String uri = rs.getString(4);
				System.out.println(uri);
				Food food = new Food(name, new ImageIcon(uri), price);
				System.out.println(food);
				foodList.add(food);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private ConnectListener conListener;
	public Server() {
		executorService = Executors.newCachedThreadPool();
		executorService.submit(this);
	}
	public void run() {
		try {
			server = new ServerSocket(2020);
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			while(true) {
				Socket socket = server.accept();
				Table table = new Table(executorService, socket, this);
				System.out.println("get a socket");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void put(String number, Table table) {
		this.tables.put(number, table);
		conListener.hasConnect(table);
	}
	public void addConnectListener(ConnectListener conListener) {
		this.conListener = conListener;
	}
	public HashMap<String, Table> getTables() {
		return tables;
	}
	public void setTables(HashMap<String, Table> tables) {
		this.tables = tables;
	}
	public static ArrayList<String> getTableNames() {
		return tableNames;
	}
	public static void setTableNames(ArrayList<String> tableNames) {
		Server.tableNames = tableNames;
	}
	
}
