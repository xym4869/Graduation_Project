package com.euphe.util.standardPre;

import com.euphe.util.standardUtil.StringListTools;

import java.util.ArrayList;
import java.util.List;

public class Detect {
	/*detectStatus用于标记格式有严重错误的数据
	*若值为-1,则表示接下来的预处理无需继续，丢弃该行数据
	*若值大于等于0,则继续其他步骤的预处理
	*/
	public static int detectStatus = 0;
	public static String mac = "";
	public static String time = "";
	public static String Detect(String str){
		String tmpStr = new String("");
		List<String> tmpList = new ArrayList<String>();
		tmpList = StringListTools.StringToList(str, "\t");
		
		//若发现数据有错误，则tmpList会被置为null
		//还需要完善，比如加一个状态，让下面的步骤不用继续做
		try{
			detectStatus = 1;
			tmpList = D1DetectTimeError.Detect(tmpList);//对时间进行处理
			tmpList = D2DeleteLastRow.Delete(tmpList);//对url进行处理
			tmpList = D3DetectFormatError.Detect(tmpList);//对其他进行格式化处理
			tmpStr = StringListTools.ListToString(tmpList, "\t");
		}
		catch(Exception e){
			return null;
		}
		return tmpStr;
	}
	
	public static String getMAC(){
		return mac;
	}
	
	public static String getTime(){
		return time;
	}
	
}
