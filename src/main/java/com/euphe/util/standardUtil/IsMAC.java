package com.euphe.util.standardUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsMAC {
    public static boolean IsMatch(String str) {
        String regEX = "(\\w{2}:){5}\\w{2}";

        Pattern pattern = Pattern.compile(regEX);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        return matcher.matches();
    }
}
