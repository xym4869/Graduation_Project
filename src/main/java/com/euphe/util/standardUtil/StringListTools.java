package com.euphe.util.standardUtil;

import java.util.ArrayList;
import java.util.List;

public class StringListTools {
	public static List<String> StringToList(String record,String separator){
		//该函数读入日志文件的一行，根据分隔符将各个项保存到List中
		if(record == null){
			return null;
		}
		List<String> recordList = new ArrayList<String>();
		String[] recordArray = record.split(separator);
		for(String text: recordArray){
			recordList.add(text);
		}
		return recordList;
	}
	
	public static String ListToString(List<String> recordList,String separator){
		//该函数根据分隔符将List保存为String
		if(recordList == null){
			return null;
		}
		String recordString = new String("");
		//List中的元素个数为size
		//对于第0号到第size-2号元素，输出为String时，使用separator作为分隔符
		//对于第size-1号元素，只需要输出值，不需要在后面再加分隔符
		int size = recordList.size();
		for(int i = 0;i < (size-1);i++){
			recordString = recordString + recordList.get(i) + separator;
		}
		recordString = recordString + recordList.get(size-1);
		
		return recordString;
	}

}
