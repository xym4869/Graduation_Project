package com.euphe.util.standardUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class A1FindDomain {
    public final static String DOMAIN_FILE = "src/main/resources/domainKey.csv";
    public final static String DOMAIN_SEPARATOR = ",";
    public static HashMap<String, String> category = new HashMap<String, String>();

    public static void init(String path, String separator) {
        // 从path中读取域名-类型映射文件
        String tmpStr = "";
        String[] map;
        File file = new File(path);
        BufferedReader reader = null;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            reader = new BufferedReader(isr);
            while ((tmpStr = reader.readLine()) != null) {
                map = tmpStr.split(separator);
                // 将读取到的一条域名-类型记录，添加到category这个HashMap中map[0]是域名，map[1]是类型
                category.put(map[0], map[1]);
            }
        } catch (Exception e) {
            // Init Error
        }
    }

    public static List<String> Analyze(String domainInfo) {
        final int domainSize = 6;
        String[] names = domainInfo.split("\\.");
        init(DOMAIN_FILE, DOMAIN_SEPARATOR);
        List<String> recordDomainKeyWords = new ArrayList<String>();

        for (int i = 0; i < names.length; i++) {
            if (!("com:80".equals(names[i]) && !("net".equals(names[i])) && !("com:8080".equals(names[i])))) {
                if (!("com".equals(names[i])) && !("cn".equals(names[i])) && !("c".equals(names[i]))) {
                    for(String key : category.keySet()){
                        if(names[i].equals(key)){
                            recordDomainKeyWords.add(key);
                            recordDomainKeyWords.add(category.get(key));
                            break;
                        }
                    }
                }
            }
        }

        // 输出的Domain信息必须为3列，解析不出来的用null填充
        while (recordDomainKeyWords.size() < domainSize) {
            recordDomainKeyWords.add(null);
        }
        return recordDomainKeyWords;
    }
}
