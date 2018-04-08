package com.euphe.util.standardPre;

import com.euphe.util.standardUtil.StringListTools;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Analyze {
	public static String Analyze(String str){
		String tmpStr = new String("");
		List<String> tmpList = new ArrayList<String>();
		tmpList = StringListTools.StringToList(str, "\t");

		try{
			tmpList = A1SiteAnalyze.Analyze(tmpList);
			tmpList = A2DomainAnalyze.Analyze(tmpList);
			tmpList = A3UserAgentAnalyze.Analyze(tmpList);
			tmpStr = StringListTools.ListToString(tmpList, "\t");
		}
		catch(Exception e){
			return null;
		}
		return tmpStr;
	}
	
	@Test
	public void AnalyzeTest(){
		String tmpStr = new String("");
		String tmpStr1 = new String("");
		String tmpStr2 = "";
		List<String> tmpList = new ArrayList<String>();
		tmpList = StringListTools.StringToList("2015-11-04	00:02:23	f6:d0:10:7c:a9:43	192.168.4.1	10.1.116.81	60.206.195.11	baidu.com	Dalvik/1.6.0 (Linux; U; Android 4.3; Coolpad 7251 Build/JLS36C)", "\t");

		try{
			tmpList = A1SiteAnalyze.Analyze(tmpList);
			tmpStr1 = StringListTools.ListToString(tmpList, "\t");
			tmpList = A2DomainAnalyze.Analyze(tmpList);
			tmpStr2 = StringListTools.ListToString(tmpList, "\t");
			tmpList = A3UserAgentAnalyze.Analyze(tmpList);
			tmpStr = StringListTools.ListToString(tmpList, "\t");
		}
		catch(Exception e){
		}
		System.out.println(tmpStr1);
		System.out.println(tmpStr2);
		System.out.println(tmpStr);
	}
}
