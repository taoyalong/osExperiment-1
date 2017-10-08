package classTwo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea blockSize;
	private JTextArea memory;
	private JTextArea pageSize;
	private JTextArea blocksCanBeUsed;
	Bitmap bitmap = new Bitmap();
	Pages pages = new Pages();
	double miss = 0;
	double look = 0;
	private JLabel misslabel;
	private JLabel missPercent;
	private JLabel realAddr;
	List<Node> opt = new LinkedList<>();
	private JLabel optMiss;
	private JLabel optPercent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					Main frame = new Main();
					frame.setVisible(true);
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
		setTitle("操作系统实验2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 988, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(131, 175, 155));
		setContentPane(contentPane);
		
		Pages pages = new Pages();
		pages.setSize(200, 200);
		pages.setLocation(20, 110);
		pages.setBackground(new Color(131, 175, 155));
		pages.setName("pages");
		Thread thread = new Thread(pages);
		thread.start();
		contentPane.add(pages);
		
		Pages mems = new Pages();
		mems.setSize(200, 200);
		mems.setLocation(250, 110);
		mems.setBackground(new Color(131, 175, 155));
		mems.setName("memory");
		Thread memthread = new Thread(mems);
		memthread.start();
		contentPane.add(mems);
		
		Bitmap bitmap = new Bitmap();
		bitmap.setSize(300, 200);
		bitmap.setLocation(500, 140);
		bitmap.setBackground(new Color(131, 175, 155));
		Thread thread1 = new Thread(bitmap);
		thread1.start();
		contentPane.add(bitmap);
		
		JLabel label = new JLabel("块大小：");
		label.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		blockSize = new JTextArea();
		blockSize.setBackground(SystemColor.info);
		blockSize.setFont(new Font("华文行楷", Font.PLAIN, 20));
		blockSize.setText("4");
		
		JLabel lblKb = new JLabel("kb");
		lblKb.setFont(new Font("华文宋体", Font.PLAIN, 20));
		
		JLabel label_1 = new JLabel("页表大小：");
		label_1.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		pageSize = new JTextArea();
		pageSize.setBackground(SystemColor.info);
		pageSize.setFont(new Font("华文行楷", Font.PLAIN, 20));
		pageSize.setText("5");
		
		JLabel label_2 = new JLabel("可用块数：");
		label_2.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		blocksCanBeUsed = new JTextArea();
		blocksCanBeUsed.setBackground(SystemColor.info);
		blocksCanBeUsed.setFont(new Font("华文行楷", Font.PLAIN, 20));
		blocksCanBeUsed.setText("3");
		
		JButton button = new JButton("确认");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int mem = Integer.valueOf(memory.getText());
					int a  = Integer.valueOf(blockSize.getText());
					int b  = Integer.valueOf(pageSize.getText());
					int c  = Integer.valueOf(blocksCanBeUsed.getText());
					bitmap.size = mem/a;
					pages.blocksize = a;
					bitmap.count = c;
					pages.count = b;
					pages.init();
					mems.count = c;
					mems.init();
					bitmap.init();
					opt = new LinkedList<>();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "输入信息有误！", "出错啦！", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		JLabel label_3 = new JLabel("内存大小：");
		label_3.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		memory = new JTextArea();
		memory.setBackground(SystemColor.info);
		memory.setFont(new Font("华文行楷", Font.PLAIN, 20));
		memory.setText("128");
		
		JButton changeWay = new JButton("FIFO");
		changeWay.setForeground(new Color(255, 0, 0));
		changeWay.setBackground(new Color(192, 192, 192));
		changeWay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(changeWay.getText().equals("FIFO")){
					changeWay.setText("LRU");
				}else{
					changeWay.setText("FIFO");
				}
			}
		});
		changeWay.setFont(new Font("华文宋体", Font.PLAIN, 20));
		JTextArea manAddress = new JTextArea();
		manAddress.setFont(new Font("华文宋体", Font.PLAIN, 20));
		
		JButton submit = new JButton("提交");
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int logicAddr = Integer.valueOf(manAddress.getText(), 16);
					int bits = 10+(int)(Math.log(pages.blocksize)/Math.log(2));
					int realBlock = (logicAddr/(int)(Math.pow(2, 10+(int)(Math.log(pages.blocksize)/Math.log(2)))));
					Node nood = null;
					if(pages.mark[realBlock] == 0){
						miss++;
						look++;
						pages.mark[realBlock] = 1;
						if(bitmap.count > 0){
							Node node = new Node(bitmap.getNumber(), 1 ,realBlock+1);
							nood = node;
							bitmap.count--;
							pages.add(node);
							mems.add(node);
						}else{
							Node temp =pages.list.remove(0);
							pages.mark[temp.getSize()-1] = 0;
							Node node = new Node(temp.getCorr(), 1, realBlock+1);
							pages.add(node);
							nood = node;
							mems.list.remove(0);
							mems.add(node);
						}
					}else {
						look++;
						if(changeWay.getText().equals("LRU")){
							for(int i = 0; i<mems.list.size(); i++){
								if(mems.list.get(i).getSize()-1 == realBlock){
									Node node = mems.list.remove(i);
									mems.list.add(node);
									nood = node;
								}
								if(pages.list.get(i).getSize()-1 == realBlock){
									Node node2 = pages.list.remove(i);
									pages.add(node2);
								}
							}
						}else{
							for(int i = 0; i<mems.list.size(); i++){
								if(mems.list.get(i).getSize()-1 == realBlock){
									nood = mems.list.get(i);
								}
							}
						}
					}
					opt.add(nood);
					DecimalFormat df=new DecimalFormat("0.00");
					misslabel.setText((int)miss + "次");
					missPercent.setText(df.format((miss/look)*100) + "%");
					String s1 = Integer.toBinaryString(logicAddr);
					if(s1.length() < 16){
						int l = 16-s1.length();
						for(int i =0; i<l; i++){
							s1 = "0" + s1;
						}
					}
					String ss = s1.substring(s1.length()-bits, s1.length());
					String s2 = Integer.toBinaryString(nood.getCorr()) + ss;
					int re = Integer.valueOf(s2, 2);
					String result = Integer.toHexString(re);
					realAddr.setText(result);
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "请输入正确的逻辑地址！", "出错啦！", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		submit.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		JLabel lblNewLabel = new JLabel("物理地址：");
		lblNewLabel.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		realAddr = new JLabel("0");
		realAddr.setForeground(new Color(255, 0, 0));
		realAddr.setBackground(new Color(192, 192, 192));
		realAddr.setFont(new Font("华文宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("缺页数：");
		lblNewLabel_2.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		JLabel label_4 = new JLabel("缺页率：");
		label_4.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		misslabel = new JLabel("0");
		misslabel.setFont(new Font("华文宋体", Font.PLAIN, 20));
		
		missPercent = new JLabel("0");
		missPercent.setFont(new Font("华文宋体", Font.PLAIN, 20));
		
		JButton btnOpt = new JButton("OPT");
		btnOpt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				double a = 0;
				double b = 0;
				int size = Integer.valueOf(blocksCanBeUsed.getText());
				if(opt.size() != 0){
					List<Node> list = new LinkedList<>();
					for(int i = 0; i<opt.size(); i++){
						if(list.contains(opt.get(i)) == false && list.size()<size){
							a++;
							b++;
							list.add(opt.get(i));
						}else if(list.contains(opt.get(i)) == false){
							a++;
							b++;
							for(int j = i+1; j<i+size && j<opt.size(); j++){
								if(list.contains(opt.get(j))){
									for(int t = 0; t<list.size(); t++){
										if(list.get(t).getSize()-1 == opt.get(j).getSize()-1){
											Node node = list.remove(t);
											list.add(node);
										}
									}
								}
							}
							list.remove(0);
							list.add(opt.get(i));
						}else {
							b++;
						}
					}
					DecimalFormat df=new DecimalFormat("0.00");
					optMiss.setText((int)a + "");
					optPercent.setText(df.format((a/b)*100) + "%");
				}
			}
		});
		
		btnOpt.setFont(new Font("华文宋体", Font.PLAIN, 20));
		
		JLabel label_5 = new JLabel("缺页数：");
		label_5.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		JLabel label_6 = new JLabel("缺页率：");
		label_6.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		optMiss = new JLabel("0");
		optMiss.setFont(new Font("华文宋体", Font.PLAIN, 20));
		
		optPercent = new JLabel("0");
		optPercent.setFont(new Font("华文宋体", Font.PLAIN, 20));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(changeWay)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_2)
										.addGap(18)
										.addComponent(misslabel, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(label_3)
										.addGap(18)
										.addComponent(memory, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
									.addComponent(manAddress, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(missPercent, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
									.addComponent(label)
									.addGap(18)
									.addComponent(blockSize, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblKb, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addGap(43)
									.addComponent(label_1))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(submit)
									.addGap(18)
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(realAddr, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(pageSize, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
									.addGap(52)
									.addComponent(label_2))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnOpt)
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(label_6, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_5, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(blocksCanBeUsed, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(button))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(optMiss, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(optPercent, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))))
							.addGap(60))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(memory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(blockSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKb)
						.addComponent(label_1)
						.addComponent(pageSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(blocksCanBeUsed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(changeWay, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 308, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(misslabel))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
										.addComponent(missPercent, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
									.addGap(20)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
											.addComponent(realAddr))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(manAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(submit))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
										.addComponent(optMiss))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
										.addComponent(optPercent, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOpt)
							.addGap(40))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
