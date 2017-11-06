package classFour;

import java.util.LinkedList;
import java.util.List;

public class Dispatch {
	List<Pcb> list;
	List<Pcb> result;
	
	
	public Dispatch() {
		init();
	}
	
	public void init(){
		list = new LinkedList<>();
		result = new LinkedList<>();
	}
	
	public void setList(List<Pcb> list) {
		this.list = list;
	}
	public List<Pcb> getList() {
		return list;
	}
	
	
	/**
	 * 先来先服务调度算法
	 */
    public void FCFS(){
    	Pcb pcb = list.get(0);
    	pcb.setStartTime(pcb.getArriveTime());
    	pcb.setFinishedTime(pcb.getRunnedTime());
    	pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
    	pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
    	Pcb.averagerevolveTime += pcb.getRevolveTime();
    	Pcb.averageTurrnTime += pcb.getRunnedTime();
    	pcb.mark = 1;
    	
		for(int i = 1; i<list.size(); i++){
			pcb = list.get(i);
			if(pcb.getArriveTime() < list.get(i-1).getFinishedTime()){
				pcb.setStartTime(list.get(i-1).getFinishedTime());
			}else{
				pcb.setStartTime(pcb.getArriveTime());
			}
			pcb.setFinishedTime(pcb.getStartTime() + pcb.getRunnedTime());
			pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
	    	pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
	    	pcb.mark = 1;
	    	Pcb.averagerevolveTime += pcb.getRevolveTime();
	    	Pcb.averageTurrnTime += pcb.getTurrnTime();
		}
		result = list;
	}
    
    /**
     * 短作业优先调度算法
     */
    public void SJF(){
    	int time = 0;
    	int count = 0;
    	int flag = 0;
    	List<Pcb> temp = new LinkedList<>();
    	while(true){
    		for(int i = 0; i<list.size(); i++){
    			Pcb p = list.get(i);
    			if(p.getArriveTime() == time){
    				int j = 0;
    				for(j = 0; j<temp.size(); j++){
    					if(temp.get(j).mark == 0 && temp.get(j).getRunnedTime() > p.getRunnedTime()){
    						temp.add(j, p);
    						count++;
    						break;
    					}
    				}
    				if(j == temp.size()){
    					temp.add(p);
    					count++;
    				}
    			}
    		}
    		
			if(flag == 0 && temp.size()!=0){
				Pcb pcb = temp.get(0);
	        	pcb.setStartTime(pcb.getArriveTime());
	        	pcb.setFinishedTime(pcb.getRunnedTime());
	        	pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
	        	pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
	        	Pcb.averagerevolveTime += pcb.getRevolveTime();
	        	Pcb.averageTurrnTime += pcb.getRunnedTime();
	        	time += pcb.getRunnedTime();
	        	flag = 1;
	        	pcb.mark = 1;
			}else{
				for(int i = 1; i<temp.size(); i++){
					Pcb pcb = temp.get(i);
					if(pcb.mark == 0){
						if(pcb.getArriveTime() < temp.get(i-1).getFinishedTime()){
							pcb.setStartTime(temp.get(i-1).getFinishedTime());
						}else{
							pcb.setStartTime(pcb.getArriveTime());
						}
						pcb.setFinishedTime(pcb.getStartTime() + pcb.getRunnedTime());
						pcb.setRevolveTime(pcb.getFinishedTime() - pcb.getArriveTime());
						pcb.setTurrnTime((double)(pcb.getFinishedTime() - pcb.getArriveTime()) / pcb.getRunnedTime());
						time += pcb.getRunnedTime();
						pcb.mark = 1;
						Pcb.averagerevolveTime += pcb.getRevolveTime();
						Pcb.averageTurrnTime += pcb.getTurrnTime();
						break;
					}
				}
			}
    		if(count == list.size()){
    			break;
    		}
    	}
    	result = temp;
    }

    /**
     * 输出结果
     */
    public void showResult(){
    	for(int i = 0; i<result.size(); i++){
    		System.out.println(result.get(i));
    	}
    	System.out.println("平均周转时间： " + Pcb.averagerevolveTime);
    	System.out.println("平均带权周转时间： " + Pcb.averageTurrnTime);
    }
    
    
}
