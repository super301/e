package e.view;
import javax.swing.*;

import e.servermodel.RecipeModel;
import e.sql.SqlHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class UpdateDial extends JDialog implements ActionListener{

	private JLabel name;
	private JTextField nameField;
	private JLabel price;
	private JTextField priceField;
	private JLabel uri;
	private JTextField uriField;
	private JButton confirm;
	private JButton cancel;
	private String sql;
	private String [] params;
	private RecipeModel model;
	private String index;
	public UpdateDial(JFrame owner, boolean m, String sql, RecipeModel model) {
		super(owner, m);
		this.sql = sql;
		this.model = model;
		init();
		this.setSize(400, 300);
		this.setVisible(true);
	}
	public UpdateDial(JFrame owner, boolean m, String sql, String[] info, RecipeModel model) {
		super(owner, m);
		this.sql = sql;
		this.model = model;
		init();
		index = info[0];
		nameField.setText(info[1]);
		priceField.setText(info[2]);
		uriField.setText(info[3]);
		this.setSize(400, 300);
		this.setVisible(true);
	}
	private void init() {
		name = new JLabel("名称");
		nameField = new JTextField();
		price = new JLabel("价格");
		priceField = new JTextField();
		uri = new JLabel("路径");
		uriField = new JTextField();
		confirm = new JButton("确认");
		confirm.addActionListener(this);
		cancel = new JButton("取消");
		cancel.addActionListener(this);
		this.setLayout(new GridLayout(4, 2));
		this.add(name);
		this.add(nameField);
		this.add(price);
		this.add(priceField);
		this.add(uri);
		this.add(uriField);
		this.add(confirm);
		this.add(cancel);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == confirm) {
			String name = nameField.getText().trim();
			String price = priceField.getText().trim();
			String uri = uriField.getText().trim();
			if(name.length() == 0 || price.length() == 0 || uri.length() == 0) {
				JOptionPane.showMessageDialog(this, "各字段不能为空");
				return ;
			}
			String [] params;
			if(index == null) {
				params = new String[]{name, price, uri};
			}else {
				params = new String[]{name, price, uri, index};
			}
			System.out.println(sql + params);
			model.update(sql, params);
			this.dispose();
		}else if(e.getSource() == cancel) {
			this.dispose();
		}
		
	}
	public UpdateDial() {
		
	}
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UpdateDial ud = new UpdateDial();
		ud.setSize(300, 400);
		ud.setVisible(true);
	}
}
