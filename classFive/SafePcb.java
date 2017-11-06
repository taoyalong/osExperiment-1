package classFive;

import java.util.ArrayList;
import java.util.List;

public class SafePcb {
	private String name;
	private List<Integer> workList;
	private List<Integer> needList;
	private List<Integer> allocationList;
	private List<Integer> workAlloList;
	private int resourceCount;
	
	public SafePcb(String name, int resourceCount) {
		this.name = name;
		this.resourceCount = resourceCount;
		init();
	}
	
	public void init(){
		workList = new ArrayList<>();
		needList = new ArrayList<>();
		allocationList = new ArrayList<>();
		workAlloList = new ArrayList<>();
	}

}
