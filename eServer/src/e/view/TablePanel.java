package e.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import e.servermodel.Table;

public class TablePanel extends JPanel implements ActionListener{

	private JButton[] jbuttons = new JButton[6];
	private TableDial [] tableDials;
	private HashMap<String, Table> tables;
/*	private JPanel east;
	private JPanel west;
	private JPanel south;
	private JPanel north;*/
	private JPanel center;
	public TablePanel(TableDial[] tableDials, HashMap<String, Table> tables) {
		this.tableDials = tableDials;
		this.tables = tables;
		center = new JPanel(new GridLayout(2, 3));
		for(int i = 0; i < 6; i++) {
			jbuttons[i] = new JButton(tableDials[i].getTitle());
			jbuttons[i].addActionListener(this);
			center.add(jbuttons[i]);
		}
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.WEST);
		this.add(new JPanel(), BorderLayout.SOUTH);
		this.add(new JPanel(), BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 6; i++) {
			if(e.getSource() == jbuttons[i]) {
				if(tables.get(tableDials[i].getName()) != null) {
					tableDials[i].setTable(tables.get(tableDials[i].getName()));
				}
				
				System.out.println(tableDials[i].getName());
				tableDials[i].setSize(400,500);
				tableDials[i].setVisible(true);
			}
		}
	}
}
