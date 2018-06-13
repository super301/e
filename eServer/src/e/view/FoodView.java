package e.view;
import javax.swing.*;
import e.common.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class FoodView extends JPanel implements ActionListener{

	private HashMap.Entry<Food, Integer> entry;
	private JLabel image;
	private JLabel name;
	private JLabel price;
//	private JButton add;
//	private JButton del;
	private JLabel count;
	private JPanel center;
	private JPanel east;
	private JPanel south;
	private JPanel north;
	public FoodView(HashMap.Entry<Food, Integer> entry) {
		this.entry = entry;
	//	this.setLayout(new BorderLayout());
		Food food = entry.getKey();
		image = new JLabel(new ImageIcon(food.getImageIcon().getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT)));
		name = new JLabel(food.getName());
		price = new JLabel(Double.toString(food.getPrice()));
//		add = new JButton("+");
//		del = new JButton("-");
		count = new JLabel(Integer.toString(entry.getValue()));
		
		this.add(image, BorderLayout.WEST);
		
		center = new JPanel(new GridLayout(2, 1));
		center.add(name);
		center.add(price);
		this.add(center, BorderLayout.CENTER);
		
		east = new JPanel();
//		east.add(del);
		east.add(count);
//		east.add(add);
		this.add(east, BorderLayout.EAST);
		south = new JPanel();
		north = new JPanel();
		this.add(south, BorderLayout.SOUTH);
		this.add(north, BorderLayout.NORTH);
//		del.addActionListener(this);
//		add.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	
}
