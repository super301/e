package e.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import e.servermodel.RequestListener;
import e.servermodel.Table;

public class TableDial extends JDialog implements ActionListener, RequestListener{

	private Table table;
	private FoodPanel history;
	private JScrollPane jsHistory;
	private FoodPanel askedFood;
	private JScrollPane jsAskedFood;
	private JPanel right;
	private JPanel bottom;
	private JButton takeOrder;
	private JButton takeCheck;
	public TableDial(Frame owner, String title) {
		super(owner, title);
		this.setName(title);
//		this.table = table;
//		history = new FoodPanel(table.getHistory());
//		askedFood = new FoodPanel(table.getFoodMap());
		history = new FoodPanel();
		jsHistory = new JScrollPane(history);
		askedFood = new FoodPanel();
		jsAskedFood = new JScrollPane(askedFood);
		this.setLayout(new GridLayout(1,2));
		this.add(jsHistory);
		
		//
		takeOrder = new JButton("Ω”µ•");
		takeCheck = new JButton("Ω·À„");
		bottom = new JPanel(new GridLayout(2, 1));
		bottom.add(takeOrder);
		bottom.add(takeCheck);
		right = new JPanel(new BorderLayout());
		right.add(jsAskedFood, BorderLayout.CENTER);
		right.add(bottom, BorderLayout.SOUTH);
		this.add(right);
		takeOrder.addActionListener(this);
		takeCheck.addActionListener(this);
	}
	public void setTable(Table table) {
		if(table == null){
			return;
		}
		this.table = table;
		table.addRequestListener(this);
		System.out.println(table.getHistory());
		history.setFoodMap(table.getHistory());
		askedFood.setFoodMap(table.getFoodMap());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == takeOrder){
			try {
				if(table != null)
					table.doSubmit();
			}catch(Exception ex) {
				throw new RuntimeException();
			}
			
		}else if(e.getSource() == takeCheck) {
			try{
				if(table != null)
					table.doCheck();
			}catch(Exception ex) {
				throw new RuntimeException();
			}
		}
	}
	@Override
	public void hasRequest() {
		// TODO Auto-generated method stub
		this.repaint();
	}
	public void repaint() {
		super.repaint();
		history.setFoodMap(table.getHistory());
		askedFood.setFoodMap(table.getFoodMap());
		this.validate();
	}
}
