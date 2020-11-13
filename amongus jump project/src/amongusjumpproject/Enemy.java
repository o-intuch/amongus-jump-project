package amongusjumpproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy extends GameObject{
    final ImageIcon imgen = new ImageIcon(this.getClass().getResource("corona.png"));
    final static int DIAMETER = 21;
    public double gravity =0.8;
    public static double holder;//score before
    public static double postMove=0;//score after
    public Enemy(int x, int y, int velocityX, int velocityY) {
        super(x, y, 0, velocityY, DIAMETER, DIAMETER);
    }
    @Override
    public void move(){
	super.move();	
	
    }
    @Override
    public void accelerate() {
        
    }

    @Override
    public void draw(Graphics g){
	
    }
    @Override
    public Rectangle getBounds(){
        return new Rectangle(x,y,32,32);
    }
    
    
}
