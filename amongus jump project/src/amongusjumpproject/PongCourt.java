package amongusjumpproject;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class PongCourt extends JPanel {
	private Doodle doodle;
        private Enemy enemy;
	private PowerUp pUp;
        private Jet je;
        //homeplay homegames1 = new homeplay();
        private final ImageIcon imgbg = new ImageIcon(this.getClass().getResource("ject4.png"));
        private final ImageIcon endbg = new ImageIcon(this.getClass().getResource("over.jpg"));
        private List<Jet> jetList;
	private List<Paddle> paddleList;
	private List<PowerUp> pUpList;
        private List<Enemy> enemyList;
        private List<Doodle> doodleList;
	private Random rand= new Random();
	private int interval = 20; //มิลลิวินาทีระหว่างการอัปเดต
	private Timer timer;       // ตัวจับเวลาแต่ละครั้งจะทำให้เราเคลื่อนไหวไปทีละก้าว
	final int COURTWIDTH  = 600;
	final int COURTHEIGHT = 600;
	private boolean ticker=false; //ใช้เพื่อตรวจสอบว่าลูกบอลอยู่ในโหมดเพิ่มพลังหรือไม่
        public int times;
	public int score;

	final int BALL_VEL  = 6;  // ลูกบอลเคลื่อนที่เร็วแค่ไหน


	public PongCourt() {
		setBorder(BorderFactory.createLineBorder(Color.white));
		setFocusable(true);



		timer = new Timer(interval, new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent e) {
                            times++;
                           // doodleList.add(doodle);
                            tick(); }//method เมื่อเริ่มเกมให้ตัวdoodle & paddle ขยับ
                });
		timer.start(); 
                
		addKeyListener(new KeyAdapter() {
                        @Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					doodle.setVelocity(-BALL_VEL, doodle.velocityY);
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					doodle.setVelocity(BALL_VEL, doodle.velocityY);
				else if (e.getKeyCode() == KeyEvent.VK_R)
					reset();
			}

                        @Override
			public void keyReleased(KeyEvent e) {
				doodle.setVelocity(0, doodle.velocityY);
			}
		}); 
	}

	/** ตั้งค่าสถานะของเกมเป็นค่าเริ่มต้นและเตรียมเกมสำหรับการป้อนข้อมูลด้วยแป้นพิมพ์ */
	public void reset() {
		paddleList= new CopyOnWriteArrayList<Paddle>();
		pUpList=new CopyOnWriteArrayList<PowerUp>();
                enemyList = new CopyOnWriteArrayList<Enemy>();
                jetList = new CopyOnWriteArrayList<Jet>();
		doodle = new Doodle(200, 400, 0, -5);
		int y=600;
		for(int x=0;x<10;x++){
			paddleList.add(new Paddle(COURTWIDTH+525 - rand.nextInt(1000), y));
			
			y-=90;

		}
		requestFocusInWindow();
		score = 0;
                times = 0;
		Paddle.WIDTH=80;

	}
	//วิธีการเหล่านี้ใช้เมื่อหยุดชั่วคราวสำหรับคำแนะนำ / ดำเนินการต่อ
	public void stopTimer(){
		timer.stop();
	}
	public void startTimer(){
		timer.start();
	}
	public void restartTimer(){
		timer.restart();
	}
	
	/** อัปเดตเกมหนึ่งครั้งโดยการย้ายลูกบอลและแผ่นโดด */
	void tick() {
                        
                
		//เพิ่ม powerups ทุกๆ scores หาร 8000 ลงตัว หรือเมื่อ score เท่ากับ 2000
		if((score%5000==0 && score>0) || score == 2000 ){
			pUpList.add(new PowerUp(rand.nextInt(550),0, 0, 2));
		}
		if(score%3000 == 0 && score>0){
                    enemyList.add(new Enemy(rand.nextInt(550),0,0,2));
                }
                if(score%1700 == 0 && score>0){
                    jetList.add(new Jet(rand.nextInt(550),0,0,2));
                }
		doodle.setBounds(getWidth(), getHeight());
		doodle.move();
		//เพิ่มจุดตัด / ย้ายแผ่นทั้งหมด
		for(Paddle p:paddleList){
			doodle.bounce(p.intersects(doodle));
			p.setBounds(getWidth(), getHeight());
			p.move();
		}
		repaint(); // ทาสีทางอ้อมเรียก paintComponent
		
		//ตัวแปรทิกเกอร์นี้ใช้เพื่อพิจารณาว่าลูกบอลอยู่ในโหมดเปิดเครื่องหรือไม่
		if(ticker==false)
			doodle.gravity+=0.3;	
		
		Game.scores.setText("Score " + score);
                Game.times.setText("Times " + times/60);

	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);// ทาสีพื้นหลังเส้นขอบ
                g.drawImage(imgbg.getImage(),0,0,700,700,this);
		g.drawImage(doodle.imgdd.getImage(), doodle.x, doodle.y,50,50,this);
                
                for(Jet je : jetList){
                    g.drawImage(je.imgjet.getImage(), je.x,je.y,50,50, this);
                    je.y+=4;
                    if(doodle.intersects(je)==Intersection.UP ||doodle.intersects(je)==Intersection.DOWN
					|| doodle.intersects(je)==Intersection.RIGHT || doodle.intersects(je)==Intersection.LEFT){
				ticker=true;
				doodle.gravity=6;
                                
				jetList.remove(je);
				
                    }
                    
                }
                if(doodle.y<200){
			ticker=false;;
			doodle.gravity=10;
		}
                
		//วาดตัว powerUps
              
		for(PowerUp pU:pUpList){
			g.drawImage(pU.imgrocket.getImage(), pU.x, pU.y,50,60, this);
			pU.y+=4;
			//ถ้ามัน intersects กับ ball, enter powerUp mode จะทำให้บอลเด้งขึ้นไปข้างบน.
			if(doodle.intersects(pU)==Intersection.UP ||doodle.intersects(pU)==Intersection.DOWN
					|| doodle.intersects(pU)==Intersection.RIGHT || doodle.intersects(pU)==Intersection.LEFT){
				ticker=true;
				doodle.gravity=4;
				pUpList.remove(pU);
				
			}
		}
		//ปิด powerup once the ball gets too high
		if(doodle.y<10){
			ticker=false;;
			doodle.gravity=10;
		}             
                //วาดสัตว์ประหลาด
            
                for(Enemy em : enemyList){
                    //em.draw(g);
                    g.drawImage(em.imgen.getImage(), em.x, em.y,75,75,this);
                    em.y+=4;
                    if(doodle.intersects(em) == Intersection.UP || doodle.intersects(em) == Intersection.DOWN 
                            || doodle.intersects(em) == Intersection.RIGHT || doodle.intersects(em) == Intersection.LEFT){
                        enemyList.remove(em);
                        for(Paddle p: paddleList)
				paddleList.remove(p);
                        g.drawImage(endbg.getImage(),0,0,700,700,this);
			g.setFont(new Font("Verdana", Font.PLAIN, 30));
                        g.setColor(Color.white);
                        g.drawString("GAME OVER !!", COURTWIDTH/2-130, 40);
			g.drawString("Your Final Score: " + score, COURTWIDTH/2-150, 70);
                        g.drawString("Times : " + times/60, COURTWIDTH/2-120, 100);
			timer.stop();
                    }
                }
		//จบเกมเมื่อลูกบอลตกออกนอกเฟรม
		if(doodle.y> COURTHEIGHT){
			for(Paddle p: paddleList)
				paddleList.remove(p);
                        g.drawImage(endbg.getImage(),0,0,700,700,this);
			g.setFont(new Font("Verdana", Font.PLAIN, 30));
                        g.setColor(Color.white);
                        g.drawString("GAME OVER !!", COURTWIDTH/2-130, 40);
			g.drawString("Your Final Score: " + score, COURTWIDTH/2-150, 70);
                        g.drawString("Times : " + times/60, COURTWIDTH/2-120, 100);
			timer.stop();
		}
		if(doodle.postMove<doodle.holder ){
			for(Paddle p: paddleList){
				p.y+=8;
				score+=1;//คะแนนจะเพิ่มขึ้นเมื่อลูกบอลสูงขึ้น ความไม่เท่าเทียมกันทำให้คะแนนเพิ่มขึ้นเท่านั้น
						 // เมื่อลูกบอลถึงจุดสูงสุดใหม่
				if(score>4000){
					p.setWidth(65);
				}

				if(score>6000){
					p.setWidth(55);
				}

				if(score>10000){
					p.setWidth(45);
				}
				
				if(score>12000){
					p.setWidth(37);
				}

				if(p.y>COURTHEIGHT-10){
					paddleList.remove(p);
					paddleList.add(new Paddle(rand.nextInt(1000), -10));
					if(rand.nextInt(8)==0){//ถ้าสุ่มแล้วได้8จะทำให้paddleเลื่อน
						if(rand.nextInt(2)==0)
							paddleList.get(paddleList.size()-1).setVelocity(2, 0);
						else
							paddleList.get(paddleList.size()-1).setVelocity(-2, 0);

					}
				}
			}				
		}	

		for(Paddle p: paddleList){
			p.draw(g);
		}

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURTWIDTH, COURTHEIGHT);
	}
}

