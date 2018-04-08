package com.euphe.util.standardPre;

import com.euphe.util.standardUtil.ListElements;
import com.euphe.util.standardUtil.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class A3UserAgentAnalyze {
	public static List<String> Analyze(List<String> list) throws IOException {
		try{
			//List中第10列为用户的UserAgent
			List<String> UserAgentInfoList = new ArrayList<String>();
			String userAgent = list.get(9);
			UserAgentInfoList = A3UserAgentUtil.Analize(userAgent);
			//先移除原始的UserAgent，再添加解析后的结果
			ListElements.replaceElement(list, Location.A_UA, UserAgentInfoList);
			return list;
		}
		catch(Exception e){
			return null;
		}
	}
}
