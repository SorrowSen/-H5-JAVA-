package cn.tedu.fly;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bird {
	int x,y;
	int width,height;
	int size;
	BufferedImage image;
	
	double g;//�������ٶ�
	double t;//ʱ����
	double speed;//���׵�˲���ٶ�
	double v0;//���ٶ�
	double s;//����ʱ��t�Ժ��λ��
	
	BufferedImage[] images;//ͼƬ���飬���ڴ洢ͼƬ
	int index;//ͼƬ����
	
	double alpha;//��ǣ��ƶ�ʱͷ��������ˮƽ�ļнǣ�
	
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
		
		images = new BufferedImage[8];//��ʼ��ͼƬ����
		for(int i=0;i<images.length;i++){
		   images[i] = ImageIO.read(getClass().getResource(i+".png"));//ͼƬ�ŵ�����
		}
	}
	
	public void step(){
		s = speed*t+g*t*t/2;
		speed = speed-g*t;
		y = y-(int)s;
		alpha = Math.atan(s/8);//�����к���
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
			y = ground.y-size/2;//����С��y����Ϊ�ٽ�㣬����С���������
			alpha = -3.1415926/2;
			return true;
			
		}
		return false;
	}
	
}

