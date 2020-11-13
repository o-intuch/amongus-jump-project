package amongusjumpproject;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Jet extends GameObject{
    final ImageIcon imgjet = new ImageIcon(this.getClass().getResource("jetspack.png"));
    final static int DIAMETER = 16;
    public Jet(int x, int y, int velocityX, int velocityY) {
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
         return new Rectangle(x,y,32,32);
    }
    
}
