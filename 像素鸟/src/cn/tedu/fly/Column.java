package cn.tedu.fly;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Column {
    int x,y;//����
    int width,height;//ͼƬ���
    int distance;//��������֮��ľ���
    int gap;//�������Ӽ�ļ�϶
    BufferedImage image;
    Random random = new Random();

     public void step(){
	   x--;
	   if(x==-width/2){
		   x = distance*2-width/2;
		   y = random.nextInt(218)+132;
	   }
  }
   public Column(int n) throws Exception{
	   image = ImageIO.read(getClass().getResource("column.png"));
	   width = image.getWidth();
	   height = image.getHeight();
	   distance = 245;
	   gap = 144;
	   x = 550+(n-1)*distance;
	   y = random.nextInt(218)+132;
   }
}
  