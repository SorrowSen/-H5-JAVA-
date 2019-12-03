package cn.tedu.fly;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ground {
	int x,y;
	int width,height;
	BufferedImage image;
	public Ground() throws IOException{
		image=ImageIO.read(
				getClass().getResource("ground.png"));
		width=image.getWidth();
		height=image.getHeight();
		x=0;
		y=500;
	}
	public void step(){
		x--;
		if(x==-109){
			x=0;
		}
	}
}
