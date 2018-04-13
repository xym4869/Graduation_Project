package com.euphe.util.standardUtil;

import java.util.ArrayList;
import java.util.List;

public class Detect {
    /*detectStatus用于标记格式有严重错误的数据
	*若值为-1,则表示接下来的预处理无需继续，丢弃该行数据
	*若值大于等于0,则继续其他步骤的预处理
	*/
    public static int detectStatus = 0;
    public static String mac = "";

    public static String getMac() {
        return mac;
    }

    public static String Detect(String str) {
        String temp = new String("");
        List<String> tempList = new ArrayList<String>();
        tempList = StringListTools.StringToList(str,"\t");

        //若发现数据有错误，则tempList会被置为null
        //还需要完善，比如加一个状态，让下面的步骤不用继续做
        try {
            detectStatus = 1;
            tempList = D1DetectTimeError.Detect(tempList);
            tempList = D2DetectFormatError.Detect(tempList);
            tempList = D3DeleteMoreRow.Delete(tempList);
            temp = StringListTools.ListToString(tempList,"\t");
        }catch (Exception e){
            return null;
        }
        return temp;
    }
}
