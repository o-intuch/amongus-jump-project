package amongusjumpproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Doodle extends GameObject {
    int j;
    final ImageIcon imgdd = new ImageIcon(this.getClass().getResource("among.png"));
    final static int DIAMETER = 21;
    public double gravity =0.8;
    public static double holder;//score before
    public static double postMove=0;//score after
    public Doodle(int x, int y, int velocityX, int velocityY) {
	super(x, y, 0, velocityY, DIAMETER, DIAMETER);
    }
		
    @Override
    public void accelerate() {
        if (x < 0)
            x=rightBound;
	else if (x > rightBound)
            x=0;
    }
    // Bounce the ball, if necessary
    public boolean bounce(Intersection i) {
	switch (i) {
            case NONE: break;//if i = NONE break
            case UP: velocityY = -4; gravity=0.8; return true; 
	}
	return false;
    }
	
    @Override
    public void move(){
	holder=this.y;
	super.move();	
	this.y+=this.velocityY+gravity;
	postMove=this.y;
    }

    @Override
    public void draw(Graphics g){
	//g.setColor(Color.GREEN);
	//g.fillOval(x, y, DIAMETER, DIAMETER);
	//g.setColor(Color.BLACK);
    }
    @Override
     public Rectangle getBounds(){
        return new Rectangle(x,y,32,32);
    }

}

