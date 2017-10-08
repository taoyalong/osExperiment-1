package ClassTwo-common;

import java.util.Random;

public class Bitmap{

	int[][] arrays;//位示图
	int count = 0; //用来表示剩余的块数
	
	
	public Bitmap() {
		Random random = new Random();
		arrays = new int[8][8];
		for(int i = 0; i<arrays.length; i++){
			for(int j = 0; j<arrays[i].length; j++){
				arrays[i][j] = random.nextInt(2);
			}
		}
	}
	
	public int getNumber(){//获取第一个0
		if(count > 0){
			for(int i = 0; i<arrays.length; i++){
				for(int j = 0; j<arrays[i].length; j++){
					if(arrays[i][j] == 0){
						arrays[i][j] = 1;
						count--;
						return i*8 + j + 1;
					}
				}
			}
		}
		return -1;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void show(){//显示位示图
		for(int i = 0; i<arrays.length; i++){
			for(int j = 0; j<arrays[i].length; j++){
				System.out.print(arrays[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	
}
