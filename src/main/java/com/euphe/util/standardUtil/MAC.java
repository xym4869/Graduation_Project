package com.euphe.util.standardUtil;

import java.util.ArrayList;
import java.util.List;

/*
 * MAC对象包含，MAC地址，该MAC地址的所有访问记录，该MAC地址在各类应用中的使用时间
 * 使用方法：
 * 1.new MAC(mac)
 * 2.将该MAC地址的所有记录 AddRecord(record)
 * 3.计算该MAC地址在每一类中的使用时间 CalDuration()
 * 4.输出该MAC地址在每个类的使用时间toString()。示例输出：f4:29:81:a2:3b:43	0	298	0	0	0	0	56	0	143
 */
public class MAC {
	public final int TIMELOCATION = 1;//一条记录中，第2列为时间
	public final int MACLOCATION = 2;//一条记录中，第3列为MAC地址
	public final int TYPELOCATION = 16;//第17列是这条记录属于的类型
	private String MAC;
	private List<String> recordList = new ArrayList<String>();
	private Domain category = new Domain();
	
	public MAC(String mac){
		this.MAC = mac;
	}
	
	public boolean AddRecord(String record){
		//添加的记录MAC地址和这个对象的MAC地址一致，则添加该记录到这个对象中，返回true,否则返回false
		String[] recordParts = record.split("\t");
		if(recordParts[MACLOCATION].equals(this.MAC)){		
			this.recordList.add(record);			
			return true;
		}
		else return false;
	}
	
	public boolean CalDuration(){
		String lastRecord = "";
		String[] lastRecordArray;
		String[] presentRecordArray;
		
		try{
			for(String presentRecord: this.recordList){
				if(lastRecord.length()!= 0){
					lastRecordArray = lastRecord.split("\t");
					presentRecordArray = presentRecord.split("\t");
					
					String typeLR = lastRecordArray[TYPELOCATION];//上一条记录的类型
					String typePre = presentRecordArray[TYPELOCATION];//当前记录的类型
					String timeLR = lastRecordArray[TIMELOCATION];//上一条记录的时间
					String timePre = presentRecordArray[TIMELOCATION];//当前记录的时间
					
					int duration = TimeTool.CalDuration(timeLR, timePre);
									
					//如果两条相邻记录中间间隔7200秒（2小时)，则认为该用户离线时间过长，不放入计算
					//间隔小于7200秒，计算
					if(duration <= 7200){
						//如果上一条记录的类型和当前记录的类型相同,则这段持续时间内归类为上一条记录的持续时间
						if(typeLR.equals(typePre)){
							category.put(typeLR, duration);
						}
						//否则，也归类到上一条记录
						else category.put(typeLR, duration);
					}
				}
				lastRecord = new String(presentRecord);
			}
			//计算当前MAC地址的持续时间成功
			return true;
		}
		catch(Exception e){
			//计算当前MAC地址的持续时间失败
			return false;
		}
	}
	
	public String toString(){
		return this.MAC + "\t" + category.toString();
	}

}