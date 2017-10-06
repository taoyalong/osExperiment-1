package classTwo;

public class Node{
	private int size = 0;//标记第几个
	private int corr;  //真正的块号
	private int mark;  //是否被在内存当中
	
	public Node(int corr, int mark, int size) {
		this.corr = corr;
		this.mark = mark;
		this.size = size;
	}
	
	public Node(int corr, int mark) {
		this.corr = corr;
		this.mark = mark;
	}
	
	public Node(){
		
	}
	
	public int getCorr() {
		return corr;
	}

	public void setCorr(int corr) {
		this.corr = corr;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}
