package com.euphe.util.standardUtil;

public class TimeTool {

	/*
	 * 本工具只适用于HH:mm:ss这种格式的时间,例如00:12:53
	 * 计算单位为秒
	 */
	
	public static int STimeToInt(String time){
		int seconds = 0;
		try{
			String[] t = time.split(":");
			int Hours = Integer.parseInt(t[0]);
			int Minutes = Integer.parseInt(t[1]);
			int Seconds = Integer.parseInt(t[2]);
			seconds = Hours*3600+Minutes*60+Seconds;
		}
		catch(Exception e){
			//时间格式错误，无法识别则返回-1
			return -1;
		}
		return seconds;
	}
	
	public static int CalDuration(String sTime, String eTime){
		int stime = STimeToInt(sTime);
		int etime = STimeToInt(eTime);
		int duration = etime - stime;
		if(duration >= 0){
			return duration;
		}
		//输入时间错误，sTime > eTime则返回-1
		else return -1;
	}

}
