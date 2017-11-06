package classFive;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	List<Pcb> list = new ArrayList<>();
	static List<Integer> valueList = new ArrayList<>();
	static List<Integer> valueAvaList = new ArrayList<>();
	private static Scanner scanner;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		System.out.println("请输入计算机内的资源个数：");
		int n = scanner.nextInt();
		for(int i = 0; i<n; i++){
			System.out.println("请输入第" + (i+1) + "种资源的最大值为： ");
			valueList.add(scanner.nextInt());
		}
		valueAvaList = valueList;
		System.out.println("请输入总共有多少进程： ");
		int m = scanner.nextInt();
		for(int i = 0; i<m; i++){
			Pcb pcb = new Pcb();
			scanner.nextLine();
			System.out.println("请输入进程名：");
			pcb.setName(scanner.nextLine());
			pcb.setResoureCount(n);
			pcb.setAvailableList(valueAvaList);
			valueAvaList = pcb.input();
		}
	}
	
	public void show(){
		for(int i = 0; i<valueAvaList.size(); i++){
			System.out.print(valueAvaList.get(i) + " ");
		}
		System.out.println();
		for(int i = 0; i<list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
}
