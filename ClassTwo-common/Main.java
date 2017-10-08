package ClassTwo-common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
	private static Scanner scanner;
	static Bitmap bitmap = new Bitmap();
	static List<Node> pageList = new LinkedList<>();//fifo页表
	static List<Node> pageListLRU = new LinkedList<>();//lru页表
	static List<Node> memList = new LinkedList<>();//lru内存
	static Queue<Node> memQueue = new ConcurrentLinkedQueue<>();//FIFO内存，先进先出
	static double FIFOMiss = 0;
	static double FIFOPer = 0;
	static double LRUMiss = 0;
	static double LRUPer = 0;
	
	public static void main(String[] args) {
		bitmap.show();
		scanner = new Scanner(System.in);
		System.out.println("请输入页表长度：");
		int pageLenth = scanner.nextInt();
		System.out.println("请输入块大小(kb)：");
		int blockLenth = scanner.nextInt();
		System.out.println("请输入可用内存数：");
		int memCanBeUserd = scanner.nextInt();
		bitmap.setCount(memCanBeUserd);
		for(int i = 0; i<pageLenth; i++){//初始化页表
			pageList.add(new Node(i));
			pageListLRU.add(new Node(i));
		}
		while(true){
			System.out.println("请输入逻辑地址：");
			String string = scanner.next();
			int logicAddr = Integer.valueOf(string, 16);//逻辑地址，十六->十
			int bits = 10+(int)(Math.log(blockLenth)/Math.log(2));//换底公式，几位不变
			int realBlock = (logicAddr/(int)(Math.pow(2, 10+(int)(Math.log(blockLenth)/Math.log(2)))));//页号
			String s1 = Integer.toBinaryString(logicAddr);//把逻辑地址转化为二进制
			if(s1.length() < 16){//小于16位，在前面补零
				int l = 16-s1.length();
				for(int i =0; i<l; i++){
					s1 = "0" + s1;
				}
			}
			if(realBlock>=pageLenth){//页号大于页表长度，错误
				System.err.println("逻辑地址输入有误！");
				break;
			}
			if(bitmap.getCount() > 0){//可用内存块不为空
				int temp = 0;
				if(pageList.get(realBlock).status == -1){//获取页表状态位
					int block = bitmap.getNumber();//块号
					temp = block;
					pageList.get(realBlock).setBlock(block);
					pageList.get(realBlock).setStatus(1);
					memQueue.add(pageList.get(realBlock));
					FIFOMiss++;
					FIFOPer++;
				}else {
					FIFOPer++;
				}
				if(pageListLRU.get(realBlock).status == -1){
					pageListLRU.get(realBlock).setBlock(temp);
					pageListLRU.get(realBlock).setStatus(1);
					memList.add(pageList.get(realBlock));
					LRUMiss++;
					LRUPer++;
				}else{
					LRUPer++;
					for(int i = 0; i<memList.size(); i++){
						if(memList.get(i).getRealBlock() == realBlock){
							Node node = memList.remove(i);//刚用完的放最下面
							memList.add(node);
						}
					}
				}
			}else{//没内存块，发生置换
				if(pageList.get(realBlock).status == -1){
					Node node = memQueue.poll();//队列头 出
					pageList.set(node.getRealBlock(), new Node(node.getRealBlock()));
					pageList.set(realBlock, new Node(realBlock, node.getBlock(), 1));
					memQueue.add(pageList.get(realBlock));
					FIFOMiss++;
					FIFOPer++;
				}else{
					FIFOPer++;
				}
				if(pageListLRU.get(realBlock).status == -1){
					Node node = memList.remove(0);
					pageListLRU.set(node.getRealBlock(), new Node(node.getRealBlock()));
					pageListLRU.set(realBlock, new Node(realBlock, node.getBlock(), 1));
					memList.add(pageListLRU.get(realBlock));
					LRUMiss++;
					LRUPer++;
				}else{
					LRUPer++;
					for(int i = 0; i<memList.size(); i++){
						if(memList.get(i).getRealBlock() == realBlock){
							Node node = memList.remove(i);
							memList.add(node);
						}
					}
				}
			}
			System.out.println("页号\t块号\t状态位");
			for(int i = 0; i<pageList.size(); i++){
				System.out.println(pageList.get(i));
			}
			System.out.println("页号\t块号\t状态位");
			for(int i = 0; i<pageListLRU.size(); i++){
				System.out.println(pageListLRU.get(i));
			}
			System.out.println("FIFO");
			getRealAddr(s1, bits, realBlock, pageList);
			System.out.println("缺页数: " + (int)FIFOMiss + "\t缺页率: " + (FIFOMiss/FIFOPer)*100 + "%");
			System.out.println("页号\t块号\t状态位");
			Iterator<Node> iterator = memQueue.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next());
			}
			System.out.println("LRU");
			getRealAddr(s1, bits, realBlock, pageListLRU);
			System.out.println("缺页数: " + (int)LRUMiss + "\t缺页率: " + (LRUMiss/LRUPer)*100 + "%");
			System.out.println("页号\t块号\t状态位");
			Iterator<Node> iterator1 = memList.iterator();
			while(iterator1.hasNext()){
				System.out.println(iterator1.next());
			}
		}
	}
	
	public static void getRealAddr(String s1, int bits, int realBlock, List<Node> pagelist){
		String ss = s1.substring(s1.length()-bits, s1.length());//不变位
		String s2 = Integer.toBinaryString(pagelist.get(realBlock).getBlock()) + ss;
		int re = Integer.valueOf(s2, 2);
		String result = Integer.toHexString(re);
		System.out.println("物理地址: " + result);
	}
}
