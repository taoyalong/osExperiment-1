package classThree;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame implements Runnable{

	private JPanel contentPane;
	static Read read = new Read();
	static FatList fatList = new FatList();
	static Bitmap bitmap;
	static Main frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					frame = new Main();
					read.setArrays(bitmap.arrays);
					read.start();
					frame.setVisible(true);
					Thread thread = new Thread(frame);
					thread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 1123);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(131, 175, 155));
		setContentPane(contentPane);
		
		bitmap = new Bitmap();
		bitmap.size = 128/4;
		bitmap.count = 3;
		bitmap.init();
		bitmap.setSize(250, 160);
		bitmap.setLocation(10, 10);
		bitmap.setBackground(new Color(131, 175, 155));
		Thread thread1 = new Thread(bitmap);
		thread1.start();
		contentPane.add(bitmap);
		
		
		
		fatList = new FatList();
		fatList.init(bitmap.getArray());
		fatList.setSize(50, 1000);
		fatList.setLocation(10, 180);
		fatList.setBackground(new Color(131, 175, 155));
		Thread thread2 = new Thread(fatList);
		thread2.start();
		contentPane.add(fatList);
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 463, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 696, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void run() {
		while(true){
			if(read.size != 0){
				int i = 0;
				int i1 = bitmap.getNumber()-1;
				for(i = 0; i<read.size-1; i++){
					int i2 = bitmap.getNumber()-1;
					fatList.list.set(i1,  i2);
					i1 = i2;
				}
				fatList.list.set(i1, -1);
				fatList.setArrays(bitmap.arrays);
				read.setArrays(bitmap.arrays);
				read.size = 0;
			}
			repaint();
		}
	}
	

}
