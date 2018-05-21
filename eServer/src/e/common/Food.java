/*
 * 定义Food类，实现序列化
 */
package e.common;
import java.io.Serializable;
import javax.swing.ImageIcon;
public class Food implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double price;
	private ImageIcon imageIcon;
	
	public Food() {	
	}
	public Food(String name, ImageIcon image, double price) {
		this.name = name;
		this.imageIcon = image;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ImageIcon getImageIcon() {
		return imageIcon;
	}
	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public boolean equals(Object object) {
		return (object instanceof Food) && this.name.equals((Food)object);
	}
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + name.hashCode();
		return result;
	}
	@Override
	public String toString() {
		return name + ": " + price;
	}
}
