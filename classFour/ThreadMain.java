package classFour;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ThreadMain extends JFrame {
	
	String dispatchName = "FCFS";
	List<Pcb> waitList = new ArrayList<>();
	List<Pcb> runList = new ArrayList<>();
	List<Pcb> blockList = new ArrayList<>();
	List<Pcb> result = new LinkedList<>();
	Dispatch dispatch = new Dispatch();
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem now;
	private JTextField textField;
	private JTextField progressNmae;
	private JTextField textField_2;
	private JTextField arriveTime;
	private JTextField textField_4;
	private JTextField runTime;
	private JLabel timeCounter;
	private ThreadDemo threadDemo;
	private BlockDemo blockDemo;
	private Runtime runtime_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					ThreadMain frame = new ThreadMain();
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
	public ThreadMain() {
		waitList.add(new Pcb("a", 0,2,0));
		waitList.add(new Pcb("b", 2,5,2));
		waitList.add(new Pcb("c", 2,4,0));
		waitList.add(new Pcb("d", 3,7,5));
		waitList.add(new Pcb("e", 5,2,0));
		
		setResizable(false);
		setTitle("进程调度");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 504);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("调度算法");
		mnNewMenu.setFont(new Font("华文行楷", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("FCFS");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				now.setText("FCFS");
				dispatchName = "FCFS";
			}
		});
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("先来先服务调度算法");
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_2 = new JMenu("SJF");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				now.setText("SJF");
				dispatchName = "SJF";
			}
		});
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("短作业优先调度算法");
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_3 = new JMenu("timeAround");
		mnNewMenu_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				now.setText("timeAround");
				dispatchName = "timeAround";
			}
		});
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("时间片轮转调度算法");
		mnNewMenu_3.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_4 = new JMenu("Priority(preemtive)");
		mnNewMenu_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				now.setText("Priority(preemtive)");
				dispatchName = "Priority(preemtive)";
			}
		});
		mnNewMenu.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("优先级调度算法(非抢占式)");
		mnNewMenu_4.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_5 = new JMenu("Priority");
		mnNewMenu_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				now.setText("Priority");
				dispatchName = "Priority";
			}
		});
		mnNewMenu.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("优先级调度算法(抢占式)");
		mnNewMenu_5.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_6 = new JMenu("HRN");
		mnNewMenu_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				now.setText("HRN");
				dispatchName = "HRN";
			}
		});
		mnNewMenu.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("响应比高者优先调度算法");
		mnNewMenu_6.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_7 = new JMenu("MFQ");
		mnNewMenu_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				now.setText("MFQ");
				dispatchName = "MFQ";
			}
		});
		mnNewMenu.add(mnNewMenu_7);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("多级反馈队列调度算法");
		mnNewMenu_7.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("当前选择的调度算法是: ");
		mntmNewMenuItem_7.setFont(new Font("华文行楷", Font.PLAIN, 20));
		menuBar.add(mntmNewMenuItem_7);
		
		now = new JMenuItem("FCFS");
		now.setForeground(new Color(255, 0, 0));
		now.setFont(new Font("华文楷体", Font.BOLD, 20));
		menuBar.add(now);
		
		timeCounter = new JLabel("0");
		timeCounter.setFont(new Font("华文楷体", Font.PLAIN, 20));
		menuBar.add(timeCounter);
		
		JButton btnNewButton_1 = new JButton("开始");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Thread(){
					@Override
					public void run() {
						while(true){
							int temp = Integer.valueOf(timeCounter.getText());
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							temp++;
							timeCounter.setText(temp +"");
						}
					}
				}.start();
				
				howAdd();
				
				new Thread(){
					@Override
					public void run() {
//						while(true){
//							if(runList.size() == waitList.size()){
//								runAlgo();
//								break;
//							}
//							else{
//								try {
//									Thread.sleep(5);
//								} catch (InterruptedException e) {
//									e.printStackTrace();
//								}
//							}
//						}
						runAlgo();
					};
				}.start();
			}
		});
		btnNewButton_1.setForeground(new Color(255, 69, 0));
		btnNewButton_1.setBackground(SystemColor.info);
		btnNewButton_1.setFont(new Font("华文行楷", Font.PLAIN, 20));
		menuBar.add(btnNewButton_1);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		threadDemo = new ThreadDemo(runList);
		threadDemo.setBorder(new LineBorder(new Color(0, 0, 0)));
		threadDemo.setSize(640, 41);
		threadDemo.setLocation(10, 10);
		Thread thread1 = new Thread(threadDemo);
		thread1.start();
		contentPane.add(threadDemo);
		
		blockDemo = new BlockDemo(blockList);
		blockDemo.setBorder(new LineBorder(new Color(0, 0, 0)));
		blockDemo.setSize(640, 41);
		blockDemo.setLocation(10, 200);
		Thread thread2 = new Thread(blockDemo);
		thread2.start();
		contentPane.add(blockDemo);
		
		runtime_1 = new Runtime();
		runtime_1.setSize(81, 41);
		runtime_1.setLocation(300, 100);
		Thread thread3 = new Thread(runtime_1);
		thread3.start();
		contentPane.add(runtime_1);
		
		textField = new JTextField();
		textField.setFont(new Font("华文行楷", Font.PLAIN, 20));
		textField.setEditable(false);
		textField.setText("进程名：");
		textField.setColumns(10);
		
		progressNmae = new JTextField();
		progressNmae.setFont(new Font("华文楷体", Font.PLAIN, 20));
		progressNmae.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("华文行楷", Font.PLAIN, 20));
		textField_2.setEditable(false);
		textField_2.setText("到达时间：");
		textField_2.setColumns(10);
		
		arriveTime = new JTextField();
		arriveTime.setFont(new Font("华文楷体", Font.PLAIN, 20));
		arriveTime.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("华文行楷", Font.PLAIN, 20));
		textField_4.setEditable(false);
		textField_4.setText("运行时间：");
		textField_4.setColumns(10);
		
		runTime = new JTextField();
		runTime.setFont(new Font("华文楷体", Font.PLAIN, 20));
		runTime.setColumns(10);
		
		JButton btnNewButton = new JButton("提交");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!progressNmae.getText().equals("") && !arriveTime.getText().equals("") && !runTime.getText().equals("")){
					try{
						int runtime = Integer.valueOf(runTime.getText());
						if(runtime == 0){
							JOptionPane.showMessageDialog(null, "运行时间不可以为0呦~~~", "出错啦！", JOptionPane.ERROR_MESSAGE);
							return;
						}
						Pcb pcb = new Pcb();
						pcb.setName(progressNmae.getText());
						pcb.setArriveTime(Integer.valueOf(arriveTime.getText()));
						pcb.setRunnedTime(runtime);
						pcb.setWeight(new Random().nextInt(5));
						waitList.add(pcb);
						JOptionPane.showMessageDialog(null, "成功添加进去了呦~~~", "成功啦！", JOptionPane.INFORMATION_MESSAGE);
					}catch (Exception ex){
						JOptionPane.showMessageDialog(null, "请输入正确的信息啦~~~", "出错啦！", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("华文行楷", Font.PLAIN, 20));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(progressNmae, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(arriveTime, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(runTime, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(266)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(progressNmae, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(arriveTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(runTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(92, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public int getNowType(){
		if(dispatchName.equals("FCFS")){
			return 1;
		}else if(dispatchName.equals("SJF")){
			return 2;
		}else if(dispatchName.equals("timeAround")){
			return 3;
		}else if(dispatchName.equals("Priority(preemtive)")){
			return 4;
		}else if(dispatchName.equals("Priority")){
			return 5;
		}else if(dispatchName.equals("HRN")){
			return 6;
		}else if(dispatchName.equals("MFQ")){
			return 7;
		}
		return -1;
	}
	
	public void runAlgo(){
		int type = getNowType();
		switch (type) {
		case 1:
			FCFS();
			break;
		case 2:
			SJF();
			break;
		case 3:
			timeAround();
			break;
		case 4:
			priority(false);
			break;
		case 5:
			priority(true);
			break;
		case 6:
			
			break;
		case 7:
			
			break;

		default:
			break;
		}
	}
	
	public void howAdd(){
		int type = getNowType();
		switch (type) {
		case 1:
			addRunList();
			break;
		case 2:
			addRunListShortJobFrist();
			break;
		case 3:
			addRunList();
			break;
		case 4:
			addRunListWeihtJobFrist();
			break;
		case 5:
			addRunListWeihtJobFrist();
			break;
		case 6:
			
			break;
		case 7:
			
			break;

		default:
			break;
		}
	}
	
	public void addRunList(){
		int size = waitList.size();
		for(int i = 0; i<size; i++){
			Pcb pcb = waitList.get(i);
			try {
				Thread.sleep(new Random().nextInt(5));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			new Thread(){
				@Override
				public void run() {
					try {
						Thread.sleep(pcb.getArriveTime() * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (runList) {
						runList.add(pcb);
					}
				}
			}.start();
		}
	}
	
	public void addRunListShortJobFrist(){
		int size = waitList.size();
		for(int i = 0; i<size; i++){
			Pcb pcb = waitList.get(i);
			try {
				Thread.sleep(new Random().nextInt(5));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			new Thread(){
				@Override
				public void run() {
					try {
						Thread.sleep(pcb.getArriveTime() * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (runList) {
						int i = 0;
						for(i = 0; i<runList.size(); i++){
							Pcb pcb1 = runList.get(i);
							if(pcb1.mark == 0 && pcb1.getRunnedTime() > pcb.getRunnedTime()){
								runList.add(i, pcb);
								break;
							}
						}
						if(i == runList.size()){
							runList.add(pcb);
						}
					}
				}
			}.start();
		}
	}
	
	public void addRunListWeihtJobFrist(){
		int size = waitList.size();
		for(int i = 0; i<size; i++){
			Pcb pcb = waitList.get(i);
			try {
				Thread.sleep(new Random().nextInt(5));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			new Thread(){
				@Override
				public void run() {
					try {
						Thread.sleep(pcb.getArriveTime() * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					int i = 0;
					for(i = 0; i<runList.size(); i++){
						Pcb pcb1 = runList.get(i);
						if(pcb1.mark == 0 && pcb1.getWeight() < pcb.getWeight()){
							runList.add(i-1, pcb);
							break;
						}
					}
					if(i == runList.size()){
						runList.add(pcb);
					}
//					synchronized (runList) {
//						
//					}
				}
			}.start();
		}
	}
	
	public void FCFS1(){
		Pcb pcb = waitList.get(0);
    	pcb.setStartTime(pcb.getArriveTime());
    	pcb.setFinishedTime(pcb.getRunnedTime());
    	pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
    	pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
    	Pcb.averagerevolveTime += pcb.getRevolveTime();
    	Pcb.averageTurrnTime += pcb.getRunnedTime();
    	pcb.mark = 1;
    	runtime_1.setNode(pcb);
    	try {
			Thread.sleep(pcb.getRunnedTime()*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	for(int i = 1; i<waitList.size(); i++){
			pcb = waitList.get(i);
			if(pcb.getArriveTime() < waitList.get(i-1).getFinishedTime()){
				pcb.setStartTime(waitList.get(i-1).getFinishedTime());
			}else{
				pcb.setStartTime(pcb.getArriveTime());
			}
			pcb.setFinishedTime(pcb.getStartTime() + pcb.getRunnedTime());
			pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
	    	pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
	    	pcb.mark = 1;
	    	runtime_1.setNode(pcb);
	    	try {
				Thread.sleep(pcb.getRunnedTime()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	Pcb.averagerevolveTime += pcb.getRevolveTime();
	    	Pcb.averageTurrnTime += pcb.getTurrnTime();
		}
		result = waitList;
		showResult();
	}
	
	public void FCFS(){
		int time = 0;
		int count = 0;
		while(true){
			for(int i = 0; i<runList.size(); i++){
				Pcb pcb = runList.get(i);
				if(pcb.mark == 2){
					count++;
				}
				if(pcb.mark == 0){
					pcb.setStartTime(time);
					pcb.mark = 1;
					runtime_1.setNode(pcb);
					try {
						Thread.sleep(pcb.getRunnedTime()*1000);
						time += pcb.getRunnedTime(); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pcb.mark = 2;
					pcb.setFinishedTime(time);
					pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
					pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
					Pcb.averagerevolveTime += pcb.getRevolveTime();
					Pcb.averageTurrnTime += pcb.getTurrnTime();
				}
			}
			if(count >= waitList.size()){
				break;
			}
		}
		result = runList;
		showResult();
	}
	
	/**
     * 短作业优先调度算法
     */
    public void SJF(){
    	int time = 0;
		int count = 0;
		while(true){
			for(int i = 0; i<runList.size(); i++){
				Pcb pcb = runList.get(i);
				if(pcb.mark == 2){
					count++;
				}
				if(pcb.mark == 0){
					pcb.setStartTime(time);
					pcb.mark = 1;
					runtime_1.setNode(pcb);
					try {
						Thread.sleep(pcb.getRunnedTime()*1000);
						time += pcb.getRunnedTime(); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					pcb.mark = 2;
					pcb.setFinishedTime(time);
					pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
					pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
					Pcb.averagerevolveTime += pcb.getRevolveTime();
					Pcb.averageTurrnTime += pcb.getTurrnTime();
				}
			}
			if(count >= waitList.size()){
				break;
			}
		}
		result = runList;
		showResult();
    }
    
    /**
     * 时间片轮转算法
     */
    public void timeAround(){
    	int time = 0;
    	int count = 0;
    	int aroundTime = 2;
    	for(int i = 0; ; i++){
    		if(i == runList.size()){
    			i = 0;
    		}
    		Pcb pcb = runList.get(i);
    		if(pcb.mark != 2){
    			if(pcb.getStartTime() == -1){
    				pcb.setStartTime(time);
    			}
    			pcb.mark = 1;
    			runtime_1.setNode(pcb);
    			try {
    				if(pcb.getRunnedTime() - pcb.getHasRun() > aroundTime){
    					Thread.sleep(aroundTime * 1000);
    					time += aroundTime;
    					pcb.setHasRun(pcb.getHasRun() + aroundTime);
    				}else{
    					Thread.sleep((pcb.getRunnedTime() - pcb.getHasRun()) * 1000);
    					time += pcb.getRunnedTime() - pcb.getHasRun();
    					pcb.setHasRun(pcb.getRunnedTime());
    				}
    				if(pcb.getHasRun() == pcb.getRunnedTime()){
        				pcb.mark = 2;
        				count++;
        				pcb.setHasFinised(true);
        				pcb.setFinishedTime(time);
        				pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
        				pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
        				Pcb.averagerevolveTime += pcb.getRevolveTime();
        				Pcb.averageTurrnTime += pcb.getTurrnTime();
        			}else{
        				pcb.mark = 0;
        			}
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}else{
    			if(count == waitList.size()){
    				System.out.println(count + "ssss");
    				break;
    			}
    		}
		}
		result = runList;
		showResult();
    }
    
    public void priority(boolean race){
    	if(race == true){//抢占式
    		int time = 0;
    		int count = 0;
    		while(true){
    			for(int i = 0; i<runList.size();){
    				if(runList.get(i).mark != 0){
    					i++;
    					continue;
    				}
    				Pcb pcb = runList.get(i);
    				if(pcb.mark == 2){
    					count++;
    				}
    				if(pcb.mark == 0){
    					pcb.setStartTime(time);
    					pcb.mark = 1;
    					runtime_1.setNode(pcb);
    					try {
    						Thread.sleep(1000);
    						time += 1;
    						pcb.setHasRun(pcb.getHasRun() + 1);
    					} catch (InterruptedException e) {
    						e.printStackTrace();
    					}
    					pcb.mark = 0;
    					if(pcb.getHasRun() == pcb.getRunnedTime()){
    						pcb.mark = 2;
    						pcb.setFinishedTime(time);
    						pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
    						pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
    						Pcb.averagerevolveTime += pcb.getRevolveTime();
    						Pcb.averageTurrnTime += pcb.getTurrnTime();
    					}
    				}
    				i = 0;
    			}
    			if(count >= waitList.size()){
    				break;
    			}
    		}
    		result = runList;
    		showResult();
    	}else{//非抢占式
    		int time = 0;
    		int count = 0;
    		while(true){
    			for(int i = 0; i<runList.size(); i++){
    				Pcb pcb = runList.get(i);
    				if(pcb.mark == 2){
    					count++;
    				}
    				if(pcb.mark == 0){
    					pcb.setStartTime(time);
    					pcb.mark = 1;
    					runtime_1.setNode(pcb);
    					try {
    						Thread.sleep(pcb.getRunnedTime()*1000);
    						time += pcb.getRunnedTime(); 
    					} catch (InterruptedException e) {
    						e.printStackTrace();
    					}
    					pcb.mark = 2;
    					pcb.setFinishedTime(time);
    					pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
    					pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
    					Pcb.averagerevolveTime += pcb.getRevolveTime();
    					Pcb.averageTurrnTime += pcb.getTurrnTime();
    				}
    			}
    			if(count >= waitList.size()){
    				break;
    			}
    		}
    		result = runList;
    		showResult();
    	}
    }
	
	/**
     * 输出结果
     */
    public void showResult(){
    	for(int i = 0; i<result.size(); i++){
    		System.out.println(result.get(i));
    	}
    	System.out.println("平均周转时间： " + Pcb.averagerevolveTime);
    	System.out.println("平均带权周转时间： " + Pcb.averageTurrnTime);
    }
}
