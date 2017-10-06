package classTwo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class Pages extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x = 0;  
	int y = 0;  
	List<Node> list;
	int count = 0;
	int blocksize;
	String name = "";
	int[] mark;
	
	public Pages() {
		list = new LinkedList<>();
	}
	
	public void init(){
		list = new LinkedList<>();
		mark = new int[count];
		for(int i = 0 ; i<count; i++){
			mark[i] = 0;
		}
	}
	
	@Override
	public void run() {
		while(true){
			repaint();
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("华文行楷", Font.PLAIN, 20));
		if(name.equals("pages")){
			g.setColor(new Color(29, 131, 8));
			g.drawString("块号", 40, 20);
			g.drawString("状态位", 95, 20);
			g.setColor(Color.black);
			for(int i = 1; i<=count; i++){
				g.drawString(i-1+"", x+7, i*30+20);
				g.drawRect(x+20, i*30, 80, 30);
				g.drawRect(80+20, i*30, 50, 30);
			}
			for(int i = 0; i<list.size(); i++){
				g.drawString(list.get(i).getCorr()+"", x+20+30, list.get(i).getSize()*30+20);
				g.drawString(list.get(i).getMark()+"", 80+20+30, list.get(i).getSize()*30+20);
			}
		}else{
			g.setColor(new Color(29, 131, 8));
			g.drawString("块号", 40, 20);
			g.setColor(Color.black);
			for(int i = 1; i<=count; i++){
				g.drawString(i-1+"", x+7, i*30+20);
				g.drawRect(x+20, i*30, 80, 30);
			}
			for(int i = 0; i<list.size(); i++){
//				g.drawString(list.get(i).getCorr()+"", x+20+30, (i+1)*30+20);//显示块号还是页号？
				g.drawString(list.get(i).getSize()-1+"", x+20+30, (i+1)*30+20);
			}
		}
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	public void add(Node node){
		list.add(node);
	}
	
}
