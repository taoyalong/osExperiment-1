package classFour;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Runtime extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x = 0;  
	int y = 0;  
	int count = 10;
	Pcb node;
	
	public Runtime(Pcb node) {
		this.node = node;
	}
	
	public Runtime() {
		node = new Pcb();
		node.setName("无");
	}
	
	 
	public Pcb getNode() {
		return node;
	}

	public void setNode(Pcb node) {
		this.node = node;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
        g.setColor(new Color(131, 175, 155));//设置画笔颜色
        g.fillRect(0, 0, 80, 40);
        g.setColor(Color.black);//设置画笔颜色
        g.drawString(node.getName(), 33, 23);
        g.drawRect(0, 0, 80, 40);
	}
	
	@Override
	public void run() {
		while(true){
			repaint();
		}
	}
	
}
