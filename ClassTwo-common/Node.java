package ClassTwo-common;

public class Node {
	
	int realBlock = 0;//页号
	int block = -1;//块号
	int status = -1;//状态位
	
	public Node() {
	}
	
	public Node(int realBlock){
		this.realBlock = realBlock;
	}
		
	public Node(int block, int status){
		this.block = block;
		this.status = status;
	}
	
	public Node(int realBlock, int block, int status){
		this.realBlock = realBlock;
		this.block = block;
		this.status = status;
	}
	
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getRealBlock() {
		return realBlock;
	}
	public void setRealBlock(int realBlock) {
		this.realBlock = realBlock;
	}
	@Override
	public String toString() {
		return realBlock + "\t" + block + "\t" + status;
	}
}
