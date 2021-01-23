import java.awt.Color;
import java.awt.Graphics;

public class BallArea {
	
	int minX; //batas kotak di samping kiri
	int maxX; //batas kotak samping kanan
	int minY; //batas kotak atas
	int maxY; //batas kotak bawah
	private Color colorFilled; //warna latar belakang
	private Color colorBorder; //warna border disekitarnya
	
	//constructor sebagai setter dari fungsi void main
	public BallArea(int CoorX, int CoorY, int width, int height, Color colorFilled, Color colorBorder) 
	{
		this.minX = CoorX;
		this.maxX = CoorX + width -1; //-1 karena sebagai bordernya, untuk memberi jarak pada bolanya dengan tepian
		this.minY = CoorY;
		this.maxY = CoorY + height -1; //-1 karena sebagai bordernya, untuk memberi jarak pada bolanya dengan tepian
		this.colorFilled = colorFilled;
		this.colorBorder = colorBorder;
	}
	
	public void set (int x, int y, int width, int height)
	{
		//mendeklarasikan posisi awal bola
		minX = x; 
		minY = y;
		maxX = x + width -1; //-1 karena sebagai bordernya, untuk memberi jarak pada bolanya dengan tepian
		maxY = y + height -1; //-1 karena sebagai bordernya, untuk memberi jarak pada bolanya dengan tepian
	}
	
	//Fungsi untuk merender latar belakang pada Frame
	public void draw(Graphics g)
	{
		g.setColor(colorFilled); //memberi warna pada 
		g.fillRect(minX, minY, maxX-minX-1, maxY-minY-1); //membuat persegi dengan posisi dan ukuran, kemudian memberi jarak pada bagian kanan dan bawah
		g.setColor(colorBorder); //memberi warna pada garisnya
		g.drawRect(minX, minY, maxX-minX-1, maxY-minY-1); 
	}
	

}
