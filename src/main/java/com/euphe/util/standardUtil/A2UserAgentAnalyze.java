package com.euphe.util.standardUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class A2UserAgentAnalyze {
    public static List<String> Analyze(List<String> tmpList) throws IOException {
            List<String> UAList = new ArrayList<String>();
            String UA = tmpList.get(Location.UA);
            UAList = A2UserAgentUtil.Analyze(UA);
            return UAList;
    }
}
