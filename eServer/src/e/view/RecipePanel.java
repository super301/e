package e.view;
import javax.swing.*;

import e.servermodel.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RecipePanel extends JPanel implements ActionListener{

	private JTable recipes;
	private JPanel bottom;
	private JButton refresh;
	private JButton insert;
	private JButton update;
	private JButton delete;
	private RecipeModel model;
	private final String SELECT_BY_ID = "select * from foods";
	private final String INSERT = "insert into foods(name, price, uri) values(?, ?, ?)";
	private final String UPDATE_BY_ID = "update foods set name=?, price=?, uri=? where id=?";
	private final String DELETE_BY_ID = "delete from foods where id=?";
	public RecipePanel() {
		
		this.setLayout(new BorderLayout());
		recipes = new JTable();
		this.add(recipes, BorderLayout.CENTER);
		refresh = new JButton("刷新");
		insert = new JButton("添加");
		update = new JButton("修改");
		delete = new JButton("删除");
		refresh.addActionListener(this);
		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		bottom = new JPanel();
		bottom.add(refresh);
		bottom.add(insert);
		bottom.add(update);
		bottom.add(delete);
		this.add(bottom, BorderLayout.SOUTH);
		model = new RecipeModel();
		model.query(SELECT_BY_ID);
		recipes.setModel(model);
	}
	
	
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		RecipePanel rp = new RecipePanel();
		jf.add(rp);
		jf.setSize(480, 640);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == refresh) {
			model.query(SELECT_BY_ID);
			Server.updateFoodList();
			recipes.validate();
			recipes.updateUI();
		}else if(e.getSource() == insert) {
			new UpdateDial(null, true, INSERT, model);
			model.query(SELECT_BY_ID);
			recipes.validate();
			recipes.updateUI();
		}else if(e.getSource() == update) {
			if(recipes.getSelectedRow() > 0) {
				String []info = new String[4];
				info[0] = (String)recipes.getValueAt(recipes.getSelectedRow(), 0);
				info[1] = (String)recipes.getValueAt(recipes.getSelectedRow(), 1);
				info[2] = (String)recipes.getValueAt(recipes.getSelectedRow(), 2);
				info[3] = (String)recipes.getValueAt(recipes.getSelectedRow(), 3);
				System.out.println(info[0]);
				new UpdateDial(null, true, UPDATE_BY_ID, info, model);
				model.query(SELECT_BY_ID);
				recipes.validate();
				recipes.updateUI();
			}else {
				JOptionPane.showMessageDialog(this, "请选择要修改的行");
			}
			
		}else if(e.getSource() == delete) {
			if(recipes.getSelectedRow() > 0) {
				String index = (String)recipes.getValueAt(recipes.getSelectedRow(), 0);
				model.update(DELETE_BY_ID, new String[] {index});
				model.query(SELECT_BY_ID);
				recipes.validate();
				recipes.updateUI();
			}else {
				JOptionPane.showMessageDialog(this, "请选择要删除的行");
			}			
		}
	}
}
