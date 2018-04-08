package com.euphe.util.standardPre;

import com.euphe.util.standardUtil.Location;

import java.util.List;

public class A1SiteAnalyze {
	public static List<String> Analyze(List<String> list) {
		//List中第5列为用户接入的站点
		try{
			String siteInfo = list.get(Location.SITE);
			String site = A1FindSite.AnalyzeSite(siteInfo);
			list.set(Location.SITE, site);
			return list;
		}
		catch(Exception e){
			return null;
		}
	}
}
