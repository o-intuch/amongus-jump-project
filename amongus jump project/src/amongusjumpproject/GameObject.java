
package amongusjumpproject;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameObject {
	int x; // พิกัด x และ y ด้านซ้ายบน
	int y;

	int width;
	int height;

	int velocityX; //พิกเซลที่จะย้ายแต่ละครั้งที่มีการเรียก move ()
	double velocityY;

	int rightBound; //ค่า x, y สูงสุดที่อนุญาต
	int bottomBound;

	

	public GameObject(int x, int y, int velocityX, int velocityY, int width,
			int height) {
		this.x = x;
		this.y = y;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.width = width;
		this.height = height;
	}

	public void setBounds(int width, int height) {
		rightBound = width - this.width;
		bottomBound = height - this.height;
	}

	public void setVelocity(int velocityX, double velocityY) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}

	// เคลื่อนย้ายวัตถุด้วยความเร็วที่กำหนด
	public void move() {
		x += velocityX;
		y += velocityY;

		accelerate(); // ทำหน้าที่เป็นเหมือนคันเร่งและเบรกของรถ 
		clip();
		

			
	}

	// ให้วัตถุอยู่ในขอบเขต
	public void clip() {
		if (x < 0)
			x = 0;
		else if (x > rightBound)
			x = rightBound;
		//else if (y > bottomBound)
			//y = bottomBound;
	}
	public Intersection intersects(GameObject other) {
		if (other.x > x + width|| other.y > y + height|| other.x + other.width  < x || other.y + other.height < y)
                    return Intersection.NONE;

		// เซ็ทค่าให้มันอยู่ตรงกลาง
		double dx = other.x + other.width /2 - (x + width /2);
		double dy = other.y + other.height/2 - (y + height/2);
                //ไว้ใช้คำนวนว่ามันintersectกันมั้ย
		double theta = Math.atan2(dy, dx);
		double diagTheta = Math.atan2(height, width); //ใช้หาค่ามุมพิกัดโพลาร์ ของพิกัด x และ y

		if ( -diagTheta <= theta && theta <= diagTheta )
			return Intersection.RIGHT;
		if ( Math.PI - diagTheta <= theta || theta <= diagTheta - Math.PI )
			return Intersection.LEFT;
		if ( diagTheta <= theta && theta <= Math.PI - diagTheta )
			return Intersection.DOWN;
		if ( diagTheta - Math.PI <= theta && theta <= diagTheta && Doodle.postMove>Doodle.holder)
			return Intersection.UP;
		
		return Intersection.NONE;
	}

	public abstract void accelerate();

	public abstract void draw(Graphics g);
        public abstract Rectangle getBounds();
}

