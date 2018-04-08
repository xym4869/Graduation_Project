package com.euphe.util.standardPre;

public class A1FindSite {
	public static String AnalyzeSite(String str){
		String[] siteInfo = str.split("\\.");
		//通过网关区别站点,即IPv4中的第3位
		String result = "Site " + siteInfo[2];
		return result;
	}
}
