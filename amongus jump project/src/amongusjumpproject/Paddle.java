package amongusjumpproject;
import java.awt.*;

public class Paddle extends GameObject {
	final static int HEIGHT = 15;
	static int WIDTH = 75;

	public Paddle(int courtwidth, int courtheight) {
		super((courtwidth - WIDTH) / 2, courtheight - HEIGHT - 20, 0, 0, WIDTH, HEIGHT);
		
	}
	//เปลี่ยนทิศทางหากเป็นไม้พายที่กำลังเคลื่อนที่
        @Override
	public void accelerate() {
		if(x<0){
			velocityX=2;
		}
		if(x> rightBound){
			velocityX=-2;
		}
		
	}

        @Override
	public void draw(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	
	public void setWidth(int x){
		this.WIDTH=x;
	}
	
        @Override
	public void move(){
		super.move();	
	}	


    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

