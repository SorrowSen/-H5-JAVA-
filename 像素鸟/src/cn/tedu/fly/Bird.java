package cn.tedu.fly;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bird {
	int x,y;
	int width,height;
	int size;
	BufferedImage image;
	
	double g;//重力加速度
	double t;//时间间隔
	double speed;//上抛的瞬间速度
	double v0;//初速度
	double s;//经过时间t以后的位移
	
	BufferedImage[] images;//图片数组，用于存储图片
	int index;//图片索引
	
	double alpha;//倾角（移动时头部方向与水平的夹角）
	
	public Bird() throws Exception{
		image = ImageIO.read(getClass().getResource("0.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 132;
		y = 280;
		size = 40;
		
		g = 4;
		t = 0.25;
		v0 = 20;
		speed = v0;
		alpha = 0;
		
		images = new BufferedImage[8];//初始化图片数组
		for(int i=0;i<images.length;i++){
		   images[i] = ImageIO.read(getClass().getResource(i+".png"));//图片放到数组
		}
	}
	
	public void step(){
		s = speed*t+g*t*t/2;
		speed = speed-g*t;
		y = y-(int)s;
		alpha = Math.atan(s/8);//反正切函数
	}
	public void flay(){
		speed = v0;
	}
	public void fly(){
		index++;
		image = images[(index/12)%8];
	}
	
	public boolean hit(Column column){
		if((x>column.x-column.width/2-size/2 && x<column.x+column.width/2+size/2) && (y<column.y-column.gap/2+size/2 || y>column.y+column.gap/2-size/2)){
			return true;
		}
		return false;
	}
	
	public boolean hit(Ground ground){
		if(y>ground.y-size/2){
			y = ground.y-size/2;//设置小鸟y坐标为临界点，不让小鸟继续下落
			alpha = -3.1415926/2;
			return true;
			
		}
		return false;
	}
	
}

