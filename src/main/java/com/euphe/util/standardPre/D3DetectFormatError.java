package com.euphe.util.standardPre;

import com.euphe.util.standardUtil.IsMAC;
import com.euphe.util.standardUtil.IsSocket;
import com.euphe.util.standardUtil.Location;

import java.util.List;

public class D3DetectFormatError {
	public static List<String> Detect(List<String> list) {
		try {
			switch (Detect.detectStatus) {
			// status用来标记是否需要继续进行预处理，1表示需要继续，0表示不需要继续进行
			case 1:
				// 检测一行数据中是否有MAC地址错误和域名格式错误
				if (!IsMAC.IsMatch(list.get(Location.MAC))) {
					Detect.detectStatus = 0;
					return null;
				}
				if (IsSocket.IsMatch(list.get(Location.DOMAIN))) {
					Detect.detectStatus = 0;
					return null;
				}
				if(list.get(Location.UA).length()==0){
					//如果UserAgent一列为空，则填充为null
					list.set(Location.UA, "null");
				}
				return list;
			default:
				Detect.detectStatus = 0;
				return null;
			}
		} catch (Exception e) {
			Detect.detectStatus = 0;
			return null;
		}
	}

}
