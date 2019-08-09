package core;

import java.util.Date;

public class Timer {

	public long startTime;
	
	public void start(){
		startTime = getTimeStamp();
	}
	
	public static long getTimeStamp(){
		return new Date().getTime();
	}
	
	public boolean expired(int seconds){
		int diff = (int) (getTimeStamp() - startTime/1000);
		return diff > seconds;
	}
	
	
}
