package e.view;
import java.awt.*;
import javax.swing.*;
public class CreateImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame();
		JLabel jl = new JLabel();
		jl.setIcon(new ImageIcon("E:/java-eclipse/se/eServer/images/3.jpg"));
		jf.add(jl);
		jf.setSize(400, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

}
