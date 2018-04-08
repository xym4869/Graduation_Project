package com.euphe.util.standardPre;

import com.euphe.util.HUtils;
import com.euphe.util.standardUtil.DomainHashMap;
import com.euphe.util.standardUtil.ListElements;
import com.euphe.util.standardUtil.Location;

import java.util.ArrayList;
import java.util.List;

public class A2DomainAnalyze {
	static{
		DomainHashMap.init(HUtils.DOMAINKEYWORDSPATH, HUtils.DOMAINKEYWORDSSEPARATOR);
	}
	public static List<String> Analyze(List<String> list) {
		//静态类只初始化一次，不应该放在这，还需要再修改
		try{
			//List中第7列为用户访问的站点的域名
			List<String> domainList = new ArrayList<String>();
			String domainInfo = list.get(Location.DOMAIN);
			domainList = A2FindDomain.Analyze(domainInfo);
//			ListElements.addElements(list, 6, domainList);
			ListElements.replaceElement(list, Location.DOMAIN, domainList);
			//6.0版本新添功能，判断记录时哪种类型
			String category = DomainHashMap.findCategory(domainList.get(0), domainList.get(1),domainList.get(2));
			//在最后一列添加所属类型
			list.add(category);
			//-----------
			return list;
		}
		catch(Exception e){
			return null;
		}
	}
}
