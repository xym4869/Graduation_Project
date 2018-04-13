package com.euphe.util.standardUtil;

import java.util.ArrayList;
import java.util.List;

public class A1DomainAnalyze {
    //static {
    //    DomainHashMap.init(Config.DOMAINKEYWORDSPATH, Config.DOMAINKEYWORDSSEPARATOR);
    //}
    public static List<String> Analyze(List<String> tmpList) throws Exception{
        //List中第7列为用户访问的站点的域名
            List<String> domainList = new ArrayList<String>();
            String domainInfo = tmpList.get(Location.DOMAIN);
            domainList = A1FindDomain.Analyze(domainInfo);
            return domainList;
    }
}
