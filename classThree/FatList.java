package classThree;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class FatList extends JPanel implements Runnable{
	
	List<Integer> list = new LinkedList<>();
	int[][] arrays;
	int x,y;
	
	public void init(int[][] arrays){
		this.arrays = arrays;
		for(int i = 0; i<arrays.length; i++){
			for(int j = 0; j<arrays[i].length; j++){
				if(arrays[i][j] == 1){
					list.add(-50);
				}else{
					list.add(-2);
				}
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
		for(int i = 0; i<list.size(); i++){
			if(list.get(i) == -50){
				g.setColor(Color.black);
				g.drawRect(0, i*30, 80, 30);
				g.setColor(new Color(254, 67, 101));
				g.fillRect(0, i*30, 79, 29);
				g.setColor(new Color(29, 131, 8));
				g.drawString("占", 20, i*30+16);
			}else if(list.get(i) == -2){
				g.setColor(Color.black);
				g.drawRect(0, i*30, 80, 30);
				g.setColor(new Color(245, 245, 220));
				g.fillRect(0, i*30, 79, 29);
				g.setColor(new Color(29, 131, 8));
				g.drawString("闲", 20, i*30+16);
			}else{
				g.setColor(Color.black);
				g.drawRect(0, i*30, 80, 30);
				g.setColor(new Color(254, 67, 101));
				g.fillRect(0, i*30, 79, 29);
				g.setColor(new Color(29, 131, 8));
				g.drawString(list.get(i) + "", 20, i*30+16);
			}
		}
	}
	
	public void setArrays(int[][] arrays){
		this.arrays = arrays;
	}
}
