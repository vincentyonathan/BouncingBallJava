import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

public class BallPanel extends JPanel implements KeyListener {
	private static final int R_Rate = 50; //menentukan refresh rate
	private Ball ball; //membuat variabel baru dari class Ball
	private BallArea box; //membuat variabel baru dari class BallArea
	private int areaWidth; //membuat variabel untuk menampung ukuran frame (lebar)
	private int areaHeight; //membuat variabel untuk menampung ukuran frame (tinggi)
	ArrayList<Ball> balls = new ArrayList<Ball>();
	Random rand = new Random(); //inisialisasi variabel rand bertipe kelas Random
	int radius = 50; //besar bola
	
	public BallPanel(int width, int height)
	{
		this.areaWidth = width;
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight)); //membuat frame baru dengan ukuran yang diinput
		int x = rand.nextInt(width - radius * 2 - 20)+ radius + 10; //merandom titik awal bola di sumbu x
		int y = rand.nextInt(height - radius * 2 - 20)+ radius + 10; //random titik awal bola di sumbu y
		int speed = 20; // input kecepatan
		int angleDegree = rand.nextInt(360); //input kecepatan dengan dengan random, tipe data int dan maksimum 360(360 derajat)
		//angleDegree sebagai bagaimana bola bergerak, ke sudut kecepatan jalan bola, jika sudutnya istimewa ( 90, 270) maka akan bergerak atas bawah
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		//apabila (180, 360, maka akan bergerak ke kanan dan ke kiri)
		ball = new Ball(x, y, speed, radius, angleDegree, new Color(r,g,b)); //memanggil constructor Ball
		balls.add(ball);
		box = new BallArea(0, 0, width, height, Color.BLACK, Color.RED); //memanggil constructor BallArea
		this.addComponentListener(new ComponentAdapter() { //Component Listener berlaku seperti penambahan aksi ketika terjadi sesuatu
			@Override
			public void componentResized(ComponentEvent e) //apabila frame di resize,
			//fungsi ini mendapatkan ukuran area latar belakangnya dan menset box yang baru
			{
				Component c = (Component)e.getSource();
				Dimension dim = c.getSize();
				areaWidth = dim.width;
				areaHeight = dim.height;
				box.set(0, 0, width, height);
			}
		});
		this.addKeyListener(this);
		this.setFocusable(true);
		startThread();
	}

	private void startThread() {
		// TODO Auto-generated method stub
		//Thread untuk mendeteksi tabrakan bola dengan box
		Thread gameThread = new Thread() 
		{
			public void run() 
			{
				while (true)
				{
					for(Ball bola1 : balls) //membandingkan bola
					{
						for(Ball bola : balls) //dengan bola setelahnya
						{
							bola1.ballCollide(bola); //apakah bola bertabrakan
						}
						bola1.collide(box); //apabila menabrak
					}
					repaint(); //fungsi repaint akan memanggil fungsi painComponent() dibawah
					try {
						Thread.sleep(1000/ R_Rate); //Memberi Delay
					}
					catch(InterruptedException ex) {}
				}
			}
		};
		gameThread.start();
	}
	
	//Untuk merender / menggambar komponen pada Frame nanti, dia mengoverride fungsi
	//paint Component pada JPanel.
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); //menggambar gambar
		box.draw(g); //menggambar box
		
		for(Ball balls : balls)
		{
			balls.draw(g); //menggambar bola
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			balls.add(new Ball(rand.nextInt(areaWidth - radius * 2 - 20)+ radius + 10, rand.nextInt(areaHeight - radius * 2 - 20)+ radius + 10
					, 20, 50, rand.nextInt(360), new Color(r,g,b)));
			repaint();
		}
	}
}
