package classTwo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class Bitmap extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int[][] arrays;
	int size = 0;  //用来存储总共有多少块
	int col = 0;   //用来表示多少行
	int last = 0;  //用来表示最后一行剩余多少
	int count = 0; //用来表示剩余的块数
	
	
	public Bitmap() {
		arrays = new int[0][0];
	}
	
	public void init(){
		Random random = new Random();
		this.col = size/8;
		this.last = size % 8;
		if(last != 0){
			arrays = new int[col+1][8];
			arrays[col] = new int[last];
		}else{
			arrays = new int[col][8];
		}
		
		for(int i = 0; i<arrays.length; i++){
			for(int j = 0; j<arrays[i].length; j++){
				arrays[i][j] = random.nextInt(2);
			}
		}
	}
	
	@Override
	public void run() {
		while(true){
			repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(int i = 0; i<arrays.length; i++){
			for(int j = 0; j<arrays[i].length; j++){
				if(arrays[i][j] == 1){//内存被占用
					g.setColor(new Color(254, 67, 101));
					g.fillRect(j*30, i*30, 30, 30);
					g.setColor(Color.black);
					g.drawRect(j*30, i*30, 30, 30);
				}else{//内存空闲
					g.setColor(new Color(245, 245, 220));
					g.fillRect(j*30, i*30, 30, 30);
					g.setColor(Color.black);
					g.drawRect(j*30, i*30, 30, 30);
				}
			}
		}
	}
	
	public int getNumber(){
		if(count > 0){
			for(int i = 0; i<arrays.length; i++){
				for(int j = 0; j<arrays[i].length; j++){
					if(arrays[i][j] == 0){
						arrays[i][j] = 1;
						return i*8 + j + 1;
					}
				}
			}
		}
		return -1;
	}
	
	
	
	
	
	
}
