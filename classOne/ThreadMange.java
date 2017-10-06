package classOne;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ThreadMange extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	List<Node> empty = new LinkedList<>();
	List<Node> list = new LinkedList<>();
	List<Node> blockList = new LinkedList<>();
	List<Node> threadList = new LinkedList<>();
	
	private JTextArea input;
	int size = 0;
	boolean hasRun = false;
	private JButton block;
	private JButton wakeup;
	private JButton timeturn;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					ThreadMange frame = new ThreadMange();
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
	public ThreadMange() {
		Node emptyNode = new Node(30, 400);
		empty.add(emptyNode);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Memory memory = new Memory(list);
		memory.setSize(92, 432);
		memory.setLocation(20, 30);
		Thread thread = new Thread(memory);
		thread.start();
		contentPane.add(memory);
		
		ThreadDemo threadDemo = new ThreadDemo(threadList);
		threadDemo.setSize(601, 41);
		threadDemo.setLocation(150, 30);
		Thread thread1 = new Thread(threadDemo);
		thread1.start();
		contentPane.add(threadDemo);
		
		BlockDemo blockDemo = new BlockDemo(blockList);
		blockDemo.setSize(601, 41);
		blockDemo.setLocation(150, 420);
		Thread thread2 = new Thread(blockDemo);
		thread2.start();
		contentPane.add(blockDemo);
		
		Runtime runtime = new Runtime();
		runtime.setSize(31, 41);
		runtime.setLocation(350, 225);
		Thread thread3 = new Thread(runtime);
		thread3.start();
		contentPane.add(runtime);
		
		input = new JTextArea();
		input.setFont(new Font("华文宋体", Font.PLAIN, 16));
		
		JButton btnSubmit = new JButton("submit");
		btnSubmit.setFont(new Font("华文中宋", Font.PLAIN, 15));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String string = input.getText();
				String[] strings = string.split(" ");
				try {
					if (string.startsWith("create")) {
						Node node = new Node();
						node.name = strings[1];
						node.size = Integer.valueOf(strings[2]);
						if(node.size == 0){
							JOptionPane.showMessageDialog(null, "大小不能为0！", "出错啦！", JOptionPane.ERROR_MESSAGE);
							throw new Exception();
						}
						if(emptyNode.size == 400){
							node.start = 30;
							size += node.size;
							empty.get(0).size = empty.get(0).size - node.size;
							empty.get(0).start = empty.get(0).start + node.size;
							list.add(node);
						}else{
							if(node.size <= empty.get(0).size){
								node.start = empty.get(0).start;
								size += node.size;
								if(empty.get(0).size == node.size){
									empty.remove(0);
								}else{
									empty.get(0).size = empty.get(0).size - node.size;
									empty.get(0).start = empty.get(0).start + node.size;
								}
								list.add(node);
							}else if(node.size > empty.get(empty.size()-1).size){
								size -= Integer.valueOf(strings[2]);
								JOptionPane.showMessageDialog(null, "没有足够空间", "出错啦！", JOptionPane.ERROR_MESSAGE);
								throw new Exception();
							}else{
								int flag = 0;
								for(int i = 1 ;i<empty.size() - 1; i++){
									int size1 = empty.get(i-1).size;
									int size2 = empty.get(i+1).size;
									if(node.size > size1 && node.size < size2){
										node.start = empty.get(i).start;
										if(empty.get(i).size == node.size){
											empty.remove(i);
										}else{
											empty.get(i).size = empty.get(i).size - node.size;
											empty.get(i).start = empty.get(i).start + node.size;
										}
										flag = 1;
										list.add(node);
										break;
									}
								}
								if(flag == 0){
									node.start = empty.get(empty.size()-1).start;
									if(empty.get(empty.size()-1).size == node.size){
										empty.remove(empty.size()-1);
									}else{
										empty.get(empty.size()-1).size = empty.get(empty.size()-1).size - node.size;
										empty.get(empty.size()-1).start = empty.get(empty.size()-1).start + node.size;
									}
									list.add(node);
								}
							}
						}
						if(hasRun == false){
							runtime.setNode(node);
							hasRun = true;
						}else{
							threadList.add(node);
						}
						sort();
					}else{
						input.setText("请输入：create name size!");
					}
				} catch (Exception e2) {
					input.setText("ERROR!");
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("内存");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("华文行楷", Font.PLAIN, 15));
		
		block = new JButton("阻塞");
		block.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hasRun == true){
					Node node = runtime.getNode();
					blockList.add(node);
					if(threadList.size() != 0){
						Node temp = threadList.get(0);
						runtime.setNode(temp);
						threadList.remove(0);
					}else{
						hasRun = false;
						runtime.node = new Node();
						runtime.node.name = "无";
					}
				}
			}
		});
		block.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		wakeup = new JButton("唤醒");
		wakeup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(blockList.size() != 0){
					if(hasRun == false){
						Node node = blockList.get(0);
						runtime.setNode(node);
						blockList.remove(0);
						hasRun = true;
					}else{
						Node node = blockList.get(0);
						threadList.add(node);
						blockList.remove(0);
					}
				}
			}
		});
		wakeup.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		timeturn = new JButton("时间片轮转");
		timeturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(hasRun == true){
					if(threadList.size() != 0){
						Node node = runtime.getNode();
						threadList.add(node);
						runtime.setNode(threadList.get(0));
						threadList.remove(0);
					}
				}else{
					if(threadList.size() != 0){
						runtime.setNode(threadList.get(0));
						threadList.remove(0);
						hasRun = true;
					}
				}
			}
		});
		timeturn.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		JButton button = new JButton("结束");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Node node = runtime.getNode();
				if(hasRun == true){
					if(threadList.size() != 0){
						runtime.setNode(threadList.get(0));
						threadList.remove(0);
					}else{
						runtime.node = new Node();
						runtime.node.name = "无";
						hasRun = false;
					}
					Node temp = null;
					for(int i = 0; i<list.size(); i++){
						if(list.get(i).name.equals(node.name)){
							temp = list.remove(i);
						}
					}
					int i = 0;
					for(i = 0; i<empty.size(); i++){
						int end = empty.get(i).start + empty.get(i).size;
						int tempEnd = temp.start + temp.size;
						if(temp.start == end){
							empty.get(i).size += temp.size;
						}else if(tempEnd == empty.get(i).getStart()){
							empty.get(i).start = temp.start;
							empty.get(i).size += temp.size;
						}
					}
					if(i == empty.size()){
						empty.add(temp);
					}
					sort();
				}
			}
		});
		
		
		button.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("就绪态");
		lblNewLabel_1.setFont(new Font("华文行楷", Font.PLAIN, 16));
		
		JLabel lblNewLabel_2 = new JLabel("运行态");
		lblNewLabel_2.setFont(new Font("华文行楷", Font.PLAIN, 16));
		
		JLabel lblNewLabel_3 = new JLabel("阻塞态");
		lblNewLabel_3.setFont(new Font("华文行楷", Font.PLAIN, 16));
		
		JButton button_2 = new JButton("帮助");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "请在输入框内输入: create name size以创建一条进程\n例如：create t1 100", "帮助",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		button_2.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(input, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnSubmit))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(43)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_3)
										.addComponent(lblNewLabel_1)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(285)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED, 422, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(wakeup, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(block, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(timeturn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(755, Short.MAX_VALUE)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(755, Short.MAX_VALUE)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel)
										.addComponent(lblNewLabel_1))
									.addGap(87)
									.addComponent(block)
									.addGap(18)
									.addComponent(wakeup)
									.addGap(18)
									.addComponent(timeturn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_2))
							.addGap(18)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addComponent(lblNewLabel_3)
							.addGap(137)
							.addComponent(input, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void sort(){
		Comparator<Node> comparator = new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				if(o1.size < o2.size){
					return -1;
				}else if (o1.size == o2.size) {
					return 0;
				}else {
					return 1;
				}
			};
		};
		empty.sort(comparator);
	}
}
