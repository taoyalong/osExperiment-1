package classThree;

import java.util.List;

public class FCB {
	String name;
	int id;
	int size = 0;
	int firstBlock;
	int type;
	String date;
	FCB father;
	List<FCB> list = null;
	
	public FCB(){
		
	}
	
	public FCB(String name, int id){
		this.name = name;
		this.id = id;
	}
}
