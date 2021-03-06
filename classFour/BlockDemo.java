package classFour;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class BlockDemo extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x = 0;  
	int y = 0;  
	int count = 10;
	List<Pcb> list;
	
	public BlockDemo(List<Pcb> list) {
		this.list = list;
	}
	 
	@Override
	public void paint(Graphics g) {
		super.paint(g);
        for(int i = 0; i<list.size(); i++){
        	g.setColor(Color.red);//设置画笔颜色
        	g.fillRect(x + i*30, y, 30, 40);
        	g.setColor(Color.black);//设置画笔颜色
        	g.drawString(list.get(i).getName(), x + i*30 + 10, y+25);
        	g.drawRect(x + i*30, y, 30, 40);
      }
	}
	
	@Override
	public void run() {
		while(true){
			repaint();
		}
	}
	
}
