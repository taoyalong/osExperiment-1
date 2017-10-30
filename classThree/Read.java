package classThree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Read extends Thread{
	
	private String string;
	private String nowPosition;
	private int deepth;
	List<FCB> root;
	int count = 1;
	FCB nowFcb;
	int size = 0;
	int[][] arrays;
	
	public Read() {
		nowPosition = "D:\\操作系统test";
		root = new LinkedList<>();
		root.add(new FCB(".", count));
		root.add(new FCB("..", count));
		nowFcb = new FCB("now", count++);
	}
	
	
	
	@Override
	public void run() {
		while(true){
		    System.out.println(nowPosition);
		    System.out.println("$ ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
				 string = br.readLine();
				 Pattern p = Pattern.compile("\\s+");
                 Matcher m = p.matcher(string);
                 string= m.replaceAll(" ");
                 int type = type();
                 switch (type) {
				case 1:
					showTree();
					break;
				case 2:
					dir();
					break;
				case 3:
					try {
						makeDir();
					} catch (Exception e) {
						System.out.println("文件名格式错误！");
					}
					break;
				case 4:
					try {
						changDir();
					} catch (Exception e) {
						System.out.println("文件名格式错误！");
					}
					break;
				case 5:
					try {
						removeDir();
					} catch (Exception e) {
						System.out.println("文件名格式错误！");
					}
					break;
				case 6:
					try {
						makeFile();
					} catch (IOException e) {
						System.out.println("文件创建失败3！");
						e.printStackTrace();
					}catch (Exception e) {
						System.out.println("内存空间不足！");
					}
					break;
					
				default:
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private int type() {
		if(string.equals("tree")){
			return 1;
		}else if (string.equals("dir")) {
			return 2;
		}else if (string.startsWith("md")) {
			return 3;
		}else if (string.startsWith("cd")) {
			return 4;
		}else if(string.startsWith("rd")){
			return 5;
		}else if (string.startsWith("mk")) {
			return 6;
		}
		return 0;
	}
	
	public void showTree(){
		File file = new File(nowPosition);
		dfs(file);
	}
	
	public void dfs(File parentDir) {
		deepth++;
		if (parentDir.isDirectory()) {
			print(parentDir);
			File[] fileArr = parentDir.listFiles();
			if (fileArr != null) {
				for (File f : fileArr) {
					dfs(f);
				}
			}
		} else {
			print(parentDir);
		}
		deepth--;
	}

	public void print(File f) {
		for (int i = 1; i < deepth; i++) {
			System.out.print("   ");
		}
		System.out.println("|--" + f.getName());
	}
	
	/**
	 * 模拟dos下的dir命令
	 */
	public void dir(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.printf("%-20s %-16s %s\n", df.format(new Date()),"<DIR>", ".");
		System.out.printf("%-20s %-16s %s\n", df.format(new Date()),"<DIR>", "..");
		
		File file = new File(nowPosition);
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(int i = 0; i<files.length; i++){
				if(files[i].isDirectory()){
					System.out.printf("%-20s %-16s %s\n", df.format(new Date(files[i].lastModified())),"<DIR>", files[i].getName());
				}else{
					System.out.printf("%-26s %-10s %s\n", df.format(new Date(files[i].lastModified())), files[i].length(), files[i].getName());
				}
			}
		}
		
	}
	
	/**
	 * 模拟dos下的md命令
	 * 创建目录
	 * @throws Exception 
	 */
	public void makeDir() throws Exception{
		Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(string);
        string= m.replaceAll(" ");
		string = string.substring(2).trim();
		if(string.contains(" ")){
			throw new Exception();
		}
		//有问题
		File file = new File(nowPosition + "\\" +string);
		boolean b = file.mkdir();
		if(b == true){
			if(nowFcb.name.equals("now")){
				FCB fcb = new FCB(string, count);
				fcb.type = 2;
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				fcb.date = df.format(new Date());
				root.add(fcb);
				fcb.list = new LinkedList<>();
				fcb.list.add(new FCB(".", count));
				fcb.list.add(new FCB("..", count));
				count++;
			}else{
				FCB fcb = new FCB(string, count);
				nowFcb.list.add(fcb);
				fcb.list = new LinkedList<>();
				fcb.list.add(new FCB(".", count));
				fcb.list.add(new FCB("..", nowFcb.id));
				count++;
			}
			System.out.println(string + "目录已成功创建!");
		}else{
			System.out.println(string + "目录创建失败!");
		}
	}
	
	/**
	 * 模拟dos下的cd命令
	 * 切换目录
	 * @throws Exception 
	 */
	public void changDir() throws Exception{
		File file = new File(nowPosition);
		Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(string);
        string= m.replaceAll(" ");
		string = string.substring(2).trim();
		if(string.contains(" ")){
			throw new Exception();
		} else {
			if(nowFcb.name.equals("now")){
                if(string.equals(".")){
					
				}else if(string.equals("..")){
					
				}else{
					int i =0;
					for(i= 0; i<root.size(); i++){
						if(root.get(i).name.equals(string)){
							nowPosition += "\\" + string;
							FCB f = nowFcb;
							nowFcb = root.get(i);
							nowFcb.father = f;
							break;
						}
					}
					if(i == root.size()){
						System.out.println("无效路径！ ");
					}
				}
			}else{
				if(string.equals(".")){
					
				}else if(string.equals("..")){
					int index = nowPosition.lastIndexOf("\\");
					nowPosition = nowPosition.substring(0, index);
					nowFcb = nowFcb.father;
				}else{
					int i =0;
					for(i= 0; i<nowFcb.list.size(); i++){
						if(nowFcb.list.get(i).name.equals(string)){
							nowPosition += "\\" + string;
							FCB f = nowFcb;
							nowFcb = nowFcb.list.get(i);
							nowFcb.father = f;
							break;
						}
					}
					if(i == root.size()){
						System.out.println("无效路径！ ");
					}
				}
			}
		}
	}
	
	/**
	 * 模仿DOS下的rd命令
	 * 删除指定目录
	 * @throws Exception 
	 */
	public void removeDir() throws Exception{
		Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(string);
        string= m.replaceAll(" ");
		string = string.substring(2).trim();
		if(string.contains(" ")){
			throw new Exception();
		}
		//有问题
		File file = new File(nowPosition + "\\" +string);
		boolean b = file.delete();
		if(b == true){
			if(nowFcb.name.equals("now")){
                int i =0;
                for(i= 0; i<root.size(); i++){
                	if(root.get(i).name.equals(string)){
                		root.remove(i);
                		break;
                	}
                }
			}else{
				int i =0;
				for(i= 0; i<nowFcb.list.size(); i++){
					if(nowFcb.list.get(i).name.equals(string)){
						nowFcb.list.remove(i);
						break;
					}
				}
				if(i == root.size()){
					System.out.println("无效路径！ ");
				}
			}
			System.out.println(string + "目录已成功删除!");
		}else{
			System.out.println(string + "目录删除失败 !");
		}
	}
	
	/**
	 * 模拟DOS下的mk命令
	 * 创建文件
	 * @throws IOException, Exception 
	 */
	public void makeFile() throws IOException, Exception{
		Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(string);
        string= m.replaceAll(" ");
		string = string.substring(2).trim();
		if(string.contains(" ")){
			String[] strings = string.split(" ");
			int s = 0;
//			s = Integer.valueOf(strings[1]);
			s = Integer.parseInt(strings[1]);
			size = s;
			File file = new File(nowPosition + "\\" +strings[0] + ".txt");
			boolean b = file.createNewFile();
			if(b == true){
				FileOutputStream fos = new FileOutputStream(file,true);
				OutputStreamWriter writer = new OutputStreamWriter(fos);
				for(int i = 0; i<s; i++){
					writer.write("1");
				}
				writer.close();
				fos.close();
				if(nowFcb.name.equals("now")){
					FCB fcb = new FCB(string, count);
					fcb.type = 1;
					fcb.size = size;
					int first = getNumber();
					if(first != -1){
						fcb.firstBlock = first;
					}else{
						throw new Exception();
					}
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					fcb.date = df.format(new Date());
					root.add(fcb);
					count++;
				}else{
					FCB fcb = new FCB(string, count);
					nowFcb.list.add(fcb);
					count++;
				}
				System.out.println(string + "文件已成功创建!");
			}else{
				System.out.println(string + "文件创建失败1！");
			}
		}else{
			System.out.println("文件创建失败2！");
		}
	}
	
	public int getSize(){
		return size;
	}
	
	public int getNumber(){
		if(count > 0){
			for(int i = 0; i<arrays.length; i++){
				for(int j = 0; j<arrays[i].length; j++){
					if(arrays[i][j] == 0){
						return i*8 + j + 1;
					}
				}
			}
		}
		return -1;
	}
	
	public void setArrays(int[][] arrays){
		this.arrays = arrays;
	}
	
	
	
}
