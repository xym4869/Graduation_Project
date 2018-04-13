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
            List<String> UAList = A2UserAgentAnalyze.Analyze(tmpList);
            List<String> URLList = A3URLAnalyze.Analyze(tmpList);
            tmpList.remove(Location.URL);
            tmpList.remove(Location.UA);
            tmpList.remove(Location.DOMAIN);
            tmpList.addAll(dominList);
            tmpList.addAll(UAList);
            tmpList.addAll(URLList);

            tmpStr = StringListTools.ListToString(tmpList,"\t");
        }catch (Exception e){
            return null;
        }
        return tmpStr;
    }
}
