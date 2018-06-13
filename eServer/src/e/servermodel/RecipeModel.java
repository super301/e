package e.servermodel;
import java.awt.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;
import e.sql.*;
public class RecipeModel extends AbstractTableModel{

	public ArrayList<String> colums;
	public ArrayList<ArrayList<String>> rows;
	
	public void query(String sql, String[] params) {
		colums = new ArrayList<String>();
		rows = new ArrayList<ArrayList<String>>();
		colums.add("序号");
		colums.add("名称");
		colums.add("价格");
		colums.add("位置");
		rows.add(colums);
		SqlHelper hp = new SqlHelper();
		ResultSet rs = hp.queryExecute(sql, params);
		
		try {
			ResultSetMetaData meteDate = rs.getMetaData();
			while(rs.next()) {
				ArrayList<String> temp = new ArrayList<>();
				for(int i = 0; i < meteDate.getColumnCount(); i++) {
					temp.add(rs.getString(i+1));
				}
				rows.add(temp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void query(String sql) {
		colums = new ArrayList<String>();
		rows = new ArrayList<ArrayList<String>>();
		colums.add("序号");
		colums.add("名称");
		colums.add("价格");
		colums.add("位置");
		rows.add(colums);
		SqlHelper hp = new SqlHelper();
		ResultSet rs = hp.queryExecute(sql);
		
		try {
			ResultSetMetaData meteDate = rs.getMetaData();
			while(rs.next()) {
				ArrayList<String> temp = new ArrayList<>();
				for(int i = 0; i < meteDate.getColumnCount(); i++) {
					temp.add(rs.getString(i+1));
				}
				rows.add(temp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean update(String sql, String[] params) {
		SqlHelper hp = new SqlHelper();
		return hp.updateExecute(sql, params);
		
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colums.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return rows.get(rowIndex).get(columnIndex);
	}

}
