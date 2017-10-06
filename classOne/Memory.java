package classOne;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class Memory extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x = 0;  
	int y = 0;  
	int size = 400;
	List<Node> list;
	
	
	
	public Memory(List<Node> list) {
		this.list = list;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.orange);//设置画笔颜色
		g.fillRect(0, 0, 91, 30);//画长方形
		g.setColor(Color.black);//设置画笔颜色
		g.drawString("OS", 35, 15);//写字
		g.setColor(Color.black);//设置画笔颜色
		g.drawRect(0, 0, 91, 431);
        for(int i = 0; i<list.size(); i++){
        	g.setColor(Color.yellow);//设置画笔颜色
        	g.fillRect(x, list.get(i).getStart(), 91, list.get(i).getSize());
        	g.setColor(Color.black);//设置画笔颜色
        	g.drawRect(x, list.get(i).getStart(), 91, list.get(i).getSize());
        	g.drawString(list.get(i).getName() + " " + list.get(i).size, x + 20, list.get(i).getStart()+ list.get(i).getSize());
      }
	}
	
	@Override
	public void run() {
		while(true){
			repaint();
		}
	}
	
	public void add(Node node){
		list.add(node);
	}
	
}
