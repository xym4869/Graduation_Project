package com.euphe.util.standardUtil;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class A2UserAgentUtil {
    static UASparser uasParser = null;
    static {
        try {
            uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> Analyze(String ua) throws IOException {
        List<String> UAInfo = new ArrayList<String>();
        UserAgentInfo userAgentInfo = A2UserAgentUtil.uasParser.parse(ua);

        UAInfo.add(userAgentInfo.getOsFamily());//操作系统家族
        UAInfo.add(userAgentInfo.getOsName());//操作系统详细名称
        UAInfo.add(userAgentInfo.getUaFamily());//浏览器名称
        UAInfo.add(userAgentInfo.getBrowserVersionInfo());//浏览器版本
        UAInfo.add(userAgentInfo.getDeviceType());//设备类型
        UAInfo.add(userAgentInfo.getType());//类型

        return UAInfo;
    }
}
