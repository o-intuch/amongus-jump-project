package amongusjumpproject;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Game implements Runnable {
	
public static JLabel scores;
public static JLabel times;
//private final ImageIcon pause = new ImageIcon(this.getClass().getResource("puse.png"));
//private final ImageIcon resum = new ImageIcon(this.getClass().getResource("resum.png"));
//private final ImageIcon back = new ImageIcon(this.getClass().getResource("back.png"));
//homeplay hg = new homeplay();
//ImageIcon exitover = new ImageIcon(this.getClass().getResource("exit.jpg"));
//ImageIcon restart = new ImageIcon(this.getClass().getResource("start.jpg"));
//JButton BStartover = new JButton(restart);
//JButton BExitover  = new JButton(exitover);

@Override
   public void run() {
      // Top-level frame
      
      final JFrame frame = new JFrame();
      frame.setTitle("AMONG US Jump!!");
      frame.setLocation(300,50);
      frame.setVisible(true);
      frame.setBackground(Color.WHITE);

      // Main playing area
      final PongCourt court = new PongCourt();
      frame.add(court, BorderLayout.CENTER);
      
	  //resume game button
      final JButton resume= new JButton("Resume game");
      //final ImageIcon resume = new ImageIcon(this.getClass().getResource("Start.png"));
     //final JButton resume = new JButton("Resume game");
      //resume.setIcon(new ImageIcon("Start.png"));
      resume.addActionListener(new ActionListener(){
              @Override
    	  public void actionPerformed(ActionEvent e){
     		  //w1.setVisible(false);
     		  try {
				Thread.sleep(1000);
 				court.restartTimer();
 				court.requestFocus();
				resume.setVisible(false);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}


    	  }
      });
      
      //Pause button
      final JPanel panel = new JPanel();
      frame.add(panel, BorderLayout.PAGE_END);
      final JButton Pause= new JButton("Pause");
      Pause.addActionListener(new ActionListener(){
              @Override
    	  public void actionPerformed(ActionEvent e){
    		  court.stopTimer();
     		  resume.setVisible(true);
    	  }
      });
      
      
      // Reset button
      final JButton reset = new JButton("New Game");
      reset.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	court.startTimer();
            court.reset();
         }
      });
      panel.add(resume);
      resume.setVisible(false);
      panel.add(Pause);
      panel.add(reset);
     
      scores= new JLabel();
      times = new JLabel();
      
      scores.setText("Score " + court.score);
      panel.add(scores);
      times.setText("  Time ");
      panel.add(times);
      
      // Put the frame on the screen
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setResizable(false);

      // Start the game running
      court.reset();
      
      
      
      }
   
   /*
    * Get the game started!
    */
   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Game());
       
   }

}
