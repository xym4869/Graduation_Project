package com.euphe.util.standardUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsURL {
    public static boolean IsMatch(String string){
        String regEX = "^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";
        Pattern pattern = Pattern.compile(regEX);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
