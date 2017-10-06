package classOne;

public class Node{
	static int id = 0;
	int start;
	int size;
	String name;
	
	
	
	public Node(int start, int size) {
		this.start = start;
		this.size = size;
	}
	
	public Node() {
		
	}

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		Node.id = id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
