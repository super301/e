package e.view;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginWindow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String number = JOptionPane.showInputDialog("Number", "1-6");
		int i = Integer.parseInt(number);
		if(i > 0 && i <7) {
			String name = "Table" + number;
			System.out.println(name);
			try {
				ClientWindow clientWindow = new ClientWindow(name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "请输入正确的数字");
		}
	}

}
