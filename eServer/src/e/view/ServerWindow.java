package e.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import e.servermodel.*;
public class ServerWindow extends JFrame implements ActionListener{

	private Server server;
	private JSplitPane jsp;
	private JButton tableManage;
	private JButton empManage;
	private TablePanel tablePanel;
//	private JPanel menue;
	private RecipePanel menue;
	private JPanel right;
	private JPanel left;
	private CardLayout cardLayout;
	
	private TableDial[] tableDials = new TableDial[6];
	public ServerWindow() {
		server = new Server();
		server.addConnectListener(new ConnectListener() {
			public void hasConnect(Table table) {
				System.out.println(table.getNumber());
			}
		});
		for(int i = 0; i < 6; i++) {
			tableDials[i] = new TableDial(this, "Table"+(i+1));
		}
		tablePanel = new TablePanel(tableDials, server.getTables());
//		menue = new JPanel();
		menue = new RecipePanel();
		menue.setBackground(Color.blue);
		cardLayout = new CardLayout();
		right = new JPanel(cardLayout);
		right.add(tablePanel, "tablePanel");
		right.add(menue, "menue");
		
		left = new JPanel(new GridLayout(2, 1));
		tableManage = new JButton("订单管理");
		tableManage.addActionListener(this);
		empManage = new JButton("菜单管理");
		empManage.addActionListener(this);
		left.add(tableManage);
		left.add(empManage);
		
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		this.add(jsp);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerWindow sw = new ServerWindow();
		sw.setSize(480, 640);
		sw.setVisible(true);
		sw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == tableManage) {
			cardLayout.first(right);
		}else if(e.getSource() == empManage) {
			cardLayout.last(right);
		}
	}

}
