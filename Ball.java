import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	//Titik Awal Bola
	float CoorX, CoorY;
	//Kecepatan Bola
	float speedX, speedY;
	//Jari-jari Bola/ Besar Bola
	float radius;
	//Warna Bola
	private Color color;
	//Membuat Constructor sebagai pengambil input di kelas void main.
	public Ball(float coorX, float coorY, float speed, float radius,
			float angleDegree, Color color) 
	{
		CoorX = coorX;
		CoorY = coorY;
		this.speedX = (float)(speed * Math.cos(Math.toRadians(angleDegree))); //ada fungsi cos karena menentukan sudut kecepatan bola pada sumbu x
		this.speedY = (float)(-speed * Math.sin(Math.toRadians(angleDegree))); //ada fungsi sin karena menentukan sudut kecepatan bola pada sumbu y
		this.radius = radius;
		this.color = color;
	}
	//Untuk menggambar atau merender bolanya pada Java Swing
	public void draw(Graphics g)
	{
		g.setColor(color); //mengisi dengan warna yang diinput
		g.fillOval((int)(CoorX-radius), (int)(CoorY-radius), (int)(2 * radius), (int)(2*radius));
		//Karena lingkaran bukan titik, maka titik koorx dan y sebagai
		//titik pusat lingkaran harus dikurangi jari-jari sebagai titik
		//pusat yang baru.
	}
	
	//fungsi untuk mendeteksi bola yang menabrak area
	public void collide(BallArea box) {
		//set ukuran dari tepian yang menjadi batas bola
		//ditambah radius karena sekali lagi, bola punya ukuran,
		//bukan hanya titik pusatnya
		float ballMinX = box.minX + radius; //batas kiri, ditambah radius karena bola memiliki ukuran
		float ballMinY = box.minY + radius; // batas atas, ditambah radius karena bola memiliki ukuran
		float ballMaxX = box.maxX - radius; //batas kanan, ditambah radius karena bola memiliki ukuran
		float ballMaxY = box.maxY - radius;  //batas bawah,  ditambah radius karena bola memiliki ukuran
		
		//mengupdate posisi bola dengan menambahkan speed
		CoorX += speedX;
		CoorY += speedY;
		//ketika menabrak batas kiri
		if(CoorX < ballMinX ) 
		{
			speedX = -speedX; //kecepatan ditukar ke arah berlawanan (dikurangi)
			CoorX = ballMinX; //update posisi bola di koordinat x
		}
		else if (CoorX > ballMaxX) //menabrak batas kanan
		{
			speedX = -speedX; //kecepatan ditukar ke arah berlawanan (dikurangi)
			CoorX = ballMaxX; //update posisi bola di koordinat x
		}
		
		if(CoorY < ballMinY) //menabrak batas atas
		{
			speedY = -speedY; //kecepatan ditukar ke arah berlawanan (dikurangi)
			CoorY = ballMinY; //update posisi bola di koordinat y
		}
		else if (CoorY > ballMaxY) //menabrak batas bawah
		{
			speedY = -speedY; //kecepatan ditukar ke arah berlawanan (dikurangi)
			CoorY = ballMaxY; //update posisi bola di koordinat y
		}
	}
	
	public void ballCollide(Ball bola)
	{
		//apabila bola yang ditunjuk sama -> skip
		if(this == bola)
		{
			return;
		}
		//jarak 2 titik
		double distanceB2 = Math.sqrt(Math.pow(CoorX-bola.CoorX, 2) + Math.pow(CoorY-bola.CoorY, 2));
		//angle kecepatan bola
		double angle = Math.atan((CoorY-bola.CoorY)/(CoorX-bola.CoorX));
		
		if(CoorX==bola.CoorX)
		{
			if(CoorY > bola.CoorY )
			{
				angle = Math.toRadians(90);
			}
			else
			{
				angle = Math.toRadians(-90);
			}
		}
		
		if(distanceB2+5 < 2*radius)
		{
			//membalikan frame sebelum tabrakan
			CoorX -= speedX;
			CoorY -= speedY;
			bola.CoorX -= bola.speedX;
			bola.CoorY -= bola.speedY;
			
			if (speedX<0) {
				speedX = (float)(20*Math.cos(angle));
				speedY = (float)(20*Math.sin(angle));
				
				bola.speedX = (float)(-20*Math.cos(angle));
				bola.speedY = (float)(-20*Math.sin(angle));
			}
			
			else {
				speedX = (float)(-20*Math.cos(angle));
				speedY = (float)(-20*Math.sin(angle));
				bola.speedX = (float)(20*Math.cos(angle));
				bola.speedY = (float)(20*Math.sin(angle));
			}
			
//			//membalikan speed dari bola (lawanan arah)
//			speedX = -speedX;
//			speedY = -speedY;
//			bola.speedX = -bola.speedX;
//			bola.speedY = -bola.speedY;
		}
	}
	
}

