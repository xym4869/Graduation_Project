package com.euphe.util.standardUtil;

import java.util.ArrayList;
import java.util.List;

public class StringListTools {
    public static List<String> StringToList(String str, String seperator){
        //该函数读入日志文件的一行，根据分隔符将各个项保存到List中
        if(str == null)
            return null;

        List<String> strList = new ArrayList<String>();
        String[] strArray = str.split(seperator);
        for(String text : strArray)
            strList.add(text);
        return strList;
    }

    public static String ListToString(List<String> tempList, String seperator) {
        //该函数根据分隔符将List保存为String
        if (tempList == null)
            return null;

        String temp = new String();
        for(int i = 0; i < tempList.size()-1; i++){
            temp = temp + tempList.get(i) + seperator;
        }
        temp = temp + tempList.get(tempList.size()-1);
        return temp;
    }
}
