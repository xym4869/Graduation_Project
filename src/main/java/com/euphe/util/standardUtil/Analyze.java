package com.euphe.util.standardUtil;

import java.util.ArrayList;
import java.util.List;

public class Analyze {
    public static String Analyze(String line) {
        String tmpStr = "";
        List<String> tmpList = new ArrayList<String>();
        tmpList = StringListTools.StringToList(line, "\t");

        try{
            List<String> dominList = A1DomainAnalyze.Analyze(tmpList);
            List<String> uaList = A2UserAgentAnalyze.Analyze(tmpList);
            List<String> urlList = A3URLAnalyze.Analyze(tmpList);
            List<String> locList = A4UserIPAnalyze.Analyze(tmpList);
            tmpList.remove(Location.URL);
            tmpList.remove(Location.UA);
            tmpList.remove(Location.DOMAIN);
            tmpList.addAll(locList);
            tmpList.addAll(dominList);
            tmpList.addAll(uaList);
            tmpList.addAll(urlList);

            tmpStr = StringListTools.ListToString(tmpList,"\t");
        }catch (Exception e){
            return null;
        }
        return tmpStr;
    }
}
