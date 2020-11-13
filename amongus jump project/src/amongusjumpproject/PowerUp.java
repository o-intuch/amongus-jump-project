
package amongusjumpproject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class PowerUp extends GameObject {
	final ImageIcon imgrocket = new ImageIcon(this.getClass().getResource("rocket.png"));
	final static int DIAMETER = 16;

	//An object to represent a powerUp
	public PowerUp(int x, int y, int velocityX, int velocityY) {
		super(x, y, 0, velocityY, DIAMETER, DIAMETER);
	}

        @Override
	public void accelerate() {

	}

        @Override
	public void draw(Graphics g) {
		
	}
	
        @Override
	public void move(){
		super.move();
	}

    @Override
    public Rectangle getBounds() {
       return new Rectangle(x,y,100,1000);
    }
}
