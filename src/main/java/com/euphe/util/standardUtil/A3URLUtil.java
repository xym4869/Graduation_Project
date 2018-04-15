package com.euphe.util.standardUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class A3URLUtil {
    public static List<String> Analyze(String urlStr) throws MalformedURLException {
        List<String> URLLists = new ArrayList<String>();
            if(urlStr == null || urlStr.endsWith("*")){
                URLLists.add(null);
                URLLists.add(null);
//                URLLists.add(null);
//                URLLists.add(null);
                return URLLists;
            }
            URL url = new URL(urlStr);

            URLLists.add(url.getProtocol());//协议
            URLLists.add(url.getHost());//主机名
            
//            int pathlength = 0;
//            if(url.getPath() == null)
//            	URLLists.add(null);
//            else{
//            	pathlength = url.getPath().length();
//            	if(!url.getPath().equals("/") && pathlength < 1000)
//            		URLLists.add(null);
//            	else
//            		URLLists.add(url.getPath());//路径
//            }
//            
//            int querylength = 0;
//            if(url.getQuery() == null)
//            	URLLists.add(null);
//            else{
//            	querylength = url.getQuery().length();
//            	if(querylength < 1000)
//            		URLLists.add(url.getQuery());//请求参数
//            	else
//            		URLLists.add(null);
//            }
            return URLLists;
    }
}
