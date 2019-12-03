package cn.tedu.fly;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class BirdGame extends JPanel{
     Bird bird;
     Ground ground;
     Column column1,column2;
     BufferedImage background;
     BufferedImage startImage;
     BufferedImage gameoverImage;
     int score;
     int status;
     public static final int START = 0;
     public static final int RUNNING = 1;
     public static final int GAMEOVER = 2;
     
     public BirdGame() throws Exception{
    	 bird = new Bird();
    	 ground = new Ground();
    	 column1 = new Column(1);
    	 column2 = new Column(2);
    	 background = ImageIO.read(getClass().getResource("bg.png"));
    	 startImage = ImageIO.read(getClass().getResource("start.png"));
    	 gameoverImage = ImageIO.read(getClass().getResource("gameover.png"));
    	 score = 0;
    	 status = START;
     }
     
     public void action() throws Exception{
    	 MouseListener ml = new MouseAdapter(){
			public void mousePressed(MouseEvent arg0){
				if(status == START){
					status = RUNNING;
				}
				if(status == RUNNING){
					bird.flay();
				}
				if(status == GAMEOVER){
					try {
						bird = new Bird();
						column1 = new Column(1);
						column2 = new Column(2);
						status = START;
						score = 0;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
    	 };
    	 
    	 this.addMouseListener(ml);
    	 
    	 while(true){
    		 if(status!= GAMEOVER){
    			  bird.fly();
    			  ground.step();
    		 }
    		 if(status == RUNNING){
    			 bird.step();
    	         column1.step();
    	         column2.step();
    		 }
    	  
    	   
    	   
    	   if(bird.hit(column1)||bird.hit(column2)||bird.hit(ground)){
    		   status = GAMEOVER;
    	   }
    	   if(bird.x == column1.x||bird.x == column2.x){
    		   score++;//ͨ�����Ӽӷ�
    	   }
    	   repaint();
    	   Thread.sleep(1000/60);
    	 }
     }
     
     public void paint(Graphics g){
    	 g.drawImage(background, 0, 0,null);
    	 g.drawImage(column1.image,column1.x-column1.width/2,column1.y-column1.height/2,null);
    	 g.drawImage(column2.image,column2.x-column2.width/2,column2.y-column2.height/2,null);
    	 g.drawImage(ground.image, ground.x, ground.y	, null);
    	 Graphics2D g2 = (Graphics2D)g;
    	 g2.rotate(-bird.alpha,bird.x,bird.y);
    	 //g2.rotate():��һ����������ת�Ƕȣ�--���Ϊ����,��ʱ����ת
    	 //              ��                  --Ŀ�����������x����
    	 //              ��                  --Ŀ�����������y����
    	 g.drawImage(bird.image,bird.x-bird.width/2,bird.y-bird.height/2,null);
    	 g2.rotate(bird.alpha,bird.x,bird.y);
    	 
    	 if(status==START){
    		 g.drawImage(startImage,0,0,null);
    	 }
    	 if(status==GAMEOVER){
    		 g.drawImage(gameoverImage,0,0,null);
    	 }
    	 Font font = new Font(Font.SANS_SERIF,Font.BOLD,40);
    	 g.setFont(font);
    	 
    	 g.setColor(Color.white);
    	 g.drawString(score+"",37,57);
     }
     
     
	public static void main(String[] args) throws Exception{
		JFrame jf = new JFrame();
		jf.setTitle("������");
		jf.setSize(440,670);
		BirdGame game = new BirdGame();
		jf.add(game);
		jf.setVisible(true);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.action();
		
	}

}
