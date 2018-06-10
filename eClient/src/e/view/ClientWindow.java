package e.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.HashMap;

import e.common.Food;
import e.model.BehaviorListener;
import e.model.Client;
public class ClientWindow extends JFrame implements BehaviorListener, WindowListener, ActionListener{

	private JSplitPane jsp;
	private FoodPanel foodPanel;
	private JScrollPane jsc;
	private JPanel right;
	//
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private JPanel bottom;
	private FoodPanel historyPanel;
	private JScrollPane jsHistoryPanel;
	private FoodPanel askPanel;
	private JScrollPane jsAskPanel;
	private JButton check;
	private JButton submit;
	private JButton askPane;
	private JButton hisPane;
	private Client client;
	HashMap<Food, Integer> foodMap = new HashMap<Food, Integer>();
	
	public ClientWindow() throws IOException{
		this.setVisible(true);
		this.setSize(640, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		client = new Client("Table1");
		client.setListener(this);
		try {
			Thread.currentThread().sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		foodPanel = new FoodPanel(client.getFoodMap(), client);
		foodPanel.setVisible(true);
		jsc = new JScrollPane(foodPanel);
		initRight();
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jsc, right);
		jsp.setDividerLocation(0.5);
		this.add(jsp);
	}
	private void initRight() {
		bottom();
		historyPanel = new FoodPanel(client.getHistory(), client);
		jsHistoryPanel = new JScrollPane(historyPanel);
		askPanel = new FoodPanel(client.getAskFoodMap(), client);
		jsAskPanel = new JScrollPane(askPanel);
		right = new JPanel(new BorderLayout());
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.add(jsAskPanel, "shopCar");
		cardPanel.add(jsHistoryPanel, "history");
		right.add(cardPanel, BorderLayout.CENTER);
		right.add(bottom, BorderLayout.SOUTH);
		
	}
	private void bottom() {
		bottom = new JPanel(new GridLayout(2, 2));
		check = new JButton("结算");
		submit = new JButton("提交");
		askPane = new JButton("购物车");
		hisPane = new JButton("历史");
		bottom.add(askPane);
		bottom.add(submit);
		bottom.add(hisPane);
		bottom.add(check);
		check.addActionListener(this);
		submit.addActionListener(this);
		askPane.addActionListener(this);
		hisPane.addActionListener(this);
		/*
		check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					client.check();
				}catch(IOException ee) {
					ee.printStackTrace();
				}
			}
		});
		askPane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.first(cardPanel);
				repaint();
			}
		});
		hisPane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.last(cardPanel);
				repaint();
			}
			
		});
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					client.submit();
					validate();
				}catch(Exception ee) {
					ee.printStackTrace();
				}
			}
		});*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new ClientWindow();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void repaint() {
		super.repaint();
		System.out.println("repaint");
		historyPanel.setFoodMap(client.getHistory());
		System.out.println(client.getAskFoodMap()+"repaint");
		askPanel.setFoodMap(client.getAskFoodMap());
		foodPanel.setFoodMap(client.getFoodMap());
		this.validate();
	}
	public void action() {
		System.out.println("action");
		cardLayout.first(cardPanel);
		repaint();
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		client.close();
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == submit) {
			try {
				client.submit();
				cardLayout.first(cardPanel);
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}else if(e.getSource() == check) {
			try {
				client.check();
			}catch(IOException ee) {
				ee.printStackTrace();
			}
		}else if(e.getSource() == askPane) {
			cardLayout.first(cardPanel);
			repaint();
		}else if(e.getSource() == hisPane) {
			cardLayout.last(cardPanel);
			repaint();
		}
	}
	
}
