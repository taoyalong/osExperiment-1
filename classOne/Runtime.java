package classOne;

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
	Node node;
	
	public Runtime(Node node) {
		this.node = node;
	}
	
	public Runtime() {
		node = new Node();
		node.setName("无");
	}
	
	 
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
        g.setColor(Color.green);//设置画笔颜色
        g.fillRect(0, 0, 30, 40);
        g.setColor(Color.black);//设置画笔颜色
        g.drawString(node.getName(), 10, 23);
        g.drawRect(0, 0, 30, 40);
	}
	
	@Override
	public void run() {
		while(true){
			repaint();
		}
	}
	
}
