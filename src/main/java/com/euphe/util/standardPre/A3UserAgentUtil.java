package com.euphe.util.standardPre;

import com.euphe.util.HUtils;
import com.euphe.util.standardUtil.UserAgentHashMap;
import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class A3UserAgentUtil {

	static UASparser uasParser = null;

	// 初始化uasParser对象
	static {
		try {
			uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
			UserAgentHashMap.init(HUtils.UAPATH, HUtils.UASEPARATOR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> Analize(String UserAgent) throws IOException {
		List<String> uaInfo = new ArrayList<String>();
		
		UserAgentInfo userAgentInfo = A3UserAgentUtil.uasParser.parse(UserAgent);
		
		String OsFamily = userAgentInfo.getOsFamily();
		String OsName = userAgentInfo.getOsName();
		String UaFamily = userAgentInfo.getUaFamily();
		String BrowserVersionInfo = userAgentInfo.getBrowserVersionInfo();
		String DeviceType = userAgentInfo.getDeviceType();
		String Type = userAgentInfo.getType();
		//如果不能识别出来
		//判断不能识别出来的逻辑比较复杂，还可以优化BrowserVersionInfo为null或者OsFamily，OsName，UaFamily中至少一项为unknown
		//if(BrowserVersionInfo == null ||(OsFamily.equals("unknown") && OsName.equals("unknown") && UaFamily.equals("unknown"))){
		if("unknown".equals(BrowserVersionInfo)||BrowserVersionInfo==null){
			String type = UserAgentHashMap.findCategory(UserAgent);
			uaInfo.add("null");
			uaInfo.add("null");
			uaInfo.add("null");
			uaInfo.add("null");
			uaInfo.add("Phone");
			uaInfo.add("null");
			//不能识别出的UA，查询UserAgent表得到数据。
			uaInfo.add(type);
		}
		//如果能识别出来Name,Type等信息不为unknown
		else{
			uaInfo.add(OsFamily);
			uaInfo.add(OsName);
			uaInfo.add(UaFamily);
			uaInfo.add(BrowserVersionInfo);
			uaInfo.add(DeviceType);
			uaInfo.add(Type);
			//目前能识别出来的都是一些浏览器的UserAgent，客户端设置为该浏览器
			uaInfo.add(UaFamily +" "+ BrowserVersionInfo);
		}
		return uaInfo;
	}
}