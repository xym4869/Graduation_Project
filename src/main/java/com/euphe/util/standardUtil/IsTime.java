package com.euphe.util.standardUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsTime {
    public static boolean IsMatch(String string){
        String regEx = "\\d{4}((\\-|\\/|.)\\d{2}){2}\\s(\\d{2}\\:){2}\\d{2}";

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }
}
