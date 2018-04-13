package com.euphe.util.standardUtil;

import java.util.ArrayList;
import java.util.List;

public class A3URLAnalyze {
    public static List<String> Analyze(List<String> tmpList) throws Exception{
        List<String> urlList = new ArrayList<String>();
        String urlInfo = tmpList.get(Location.URL);
        urlList = A3URLUtil.Analyze(urlInfo);
        return urlList;
    }
}
