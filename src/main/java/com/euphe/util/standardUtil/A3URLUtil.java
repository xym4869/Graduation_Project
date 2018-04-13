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
                URLLists.add(null);
                URLLists.add(null);
                URLLists.add(null);
                return URLLists;
            }
            URL url = new URL(urlStr);

            URLLists.add(url.getProtocol());//协议
            URLLists.add(url.getAuthority());//验证信息
            URLLists.add(url.getHost());//主机名
            //URLLists.add(url.getPath());//路径
            if(url.getPath() == "/")
            	URLLists.add(null);
            else 
            	URLLists.add(url.getPath());
            URLLists.add(Integer.toString(url.getPort()));//端口
            return URLLists;
    }
}
