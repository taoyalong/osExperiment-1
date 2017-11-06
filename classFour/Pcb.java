package classFour;

import java.util.Random;

import com.google.common.base.MoreObjects;

public class Pcb implements Comparable<Pcb>{
	
	private String name;
	private int arriveTime; //到达时间
	private int runnedTime; //运行时间
	private int startTime = -1;  //开始时间
	private int finishedTime;//结束时间
	private int revolveTime; //周转时间
	private double turrnTime; //带权周转时间
	private boolean hasFinised = false; //是否已经结束
	private int weight = 0; //权值
	private int hasRun = 0;
	int mark = 0;
	int flag = 0;
	
	public static int averagerevolveTime = 0; //平均周转时间
	public static double averageTurrnTime = 0;  //平均带权周转时间
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("name", name).add("weight", weight)
				.add("arriveTime", arriveTime).add("runnedTime", runnedTime)
				.add("startTime", startTime).add("finishedTime", finishedTime)
				.add("revolveTime", revolveTime).add("turrnTime", turrnTime)
				.toString();
	}
	
	@Override
	public int compareTo(Pcb o) {
		if(this.arriveTime < o.arriveTime){
			return -1;
		}else if(this.arriveTime == o.arriveTime){
			if(this.runnedTime < o.runnedTime){
				return -1;
			}else if(this.runnedTime == o.runnedTime){
				return 0;
			}else{
				return 1;
			}
		}else{
			return 1;
		}
	}
	
	public Pcb() {}
	
	public Pcb(String name, int arruveTime, int runTime) {
		this.name = name;
		this.arriveTime = arruveTime;
		this.runnedTime = runTime;
		this.weight = new Random().nextInt(5);
	}
	
	public Pcb(String name, int arruveTime, int runTime, int weight) {
		this.name = name;
		this.arriveTime = arruveTime;
		this.runnedTime = runTime;
		this.weight = weight;
	}
	
	public Pcb(Pcb pcb){
		this.name = pcb.name;
		this.arriveTime = pcb.arriveTime;
		this.runnedTime = pcb.runnedTime;
	}
	
	
	public int getHasRun() {
		return hasRun;
	}

	public void setHasRun(int hasRun) {
		this.hasRun = hasRun;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(int arriveTime) {
		this.arriveTime = arriveTime;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getFinishedTime() {
		return finishedTime;
	}
	public void setFinishedTime(int finishedTime) {
		this.finishedTime = finishedTime;
	}
	public int getRunnedTime() {
		return runnedTime;
	}
	public void setRunnedTime(int runnedTime) {
		this.runnedTime = runnedTime;
	}
	public boolean isHasFinised() {
		return hasFinised;
	}
	public void setHasFinised(boolean hasFinised) {
		this.hasFinised = hasFinised;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getRevolveTime() {
		return revolveTime;
	}
	public void setRevolveTime(int revolveTime) {
		this.revolveTime = revolveTime;
	}
	public double getTurrnTime() {
		return turrnTime;
	}
	public void setTurrnTime(double turrnTime) {
		this.turrnTime = turrnTime;
	}
}
