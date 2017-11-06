package classFive;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.common.base.MoreObjects;

public class Pcb {
	private String name;
	private List<Integer> maxList;
	private List<Integer> allocationList;
	private List<Integer> needList;
	private List<Integer> availableList;
	private int resoureCount;
	private Scanner scanner;
	boolean finshed = false;
	
	public Pcb() {
		init();
	}
	
    public Pcb(String name, int resourceCount) {
		this.name = name;
		this.resoureCount = resourceCount;
		init();
	}
	
	public void init(){
		maxList = new ArrayList<>();
		allocationList = new ArrayList<>();
		needList = new ArrayList<>();
		availableList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResoureCount() {
		return resoureCount;
	}

	public void setResoureCount(int resoureCount) {
		this.resoureCount = resoureCount;
	}
	
	public List<Integer> getAvailableList() {
		return availableList;
	}

	public void setAvailableList(List<Integer> availableList) {
		this.availableList = availableList;
	}

	public List<Integer> input(){
		scanner = new Scanner(System.in);
		for(int i = 0; i<resoureCount; i++){
			System.out.println("请输入" + name +"的第" + (i+1) + "种资源的需要最大数： ");
			maxList.add(scanner.nextInt());
			System.out.println("请输入第" + (i+1) + "种资源的已分配数： ");
			allocationList.add(scanner.nextInt());
			needList.add(maxList.get(i) - allocationList.get(i));
			availableList.set(i, availableList.get(i) - maxList.get(i));
		}
		return availableList;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("name", name).add("max", maxList)
				.add("allocation", allocationList).add("need", needList)
				.toString();
	}

}
