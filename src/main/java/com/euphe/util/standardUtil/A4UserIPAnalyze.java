package com.euphe.util.standardUtil;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class A4UserIPAnalyze {
    public static List<String> Analyze(List<String> tmpList) throws IOException {
        final String errorInfo = "XX";
        List<String> locList = new ArrayList<String>();

        String userIP = tmpList.get(Location.SITE);

        //创建GeoLite2数据库
        File database = new File("src/main/resources/GeoLite2-City.mmdb");
        //读取数据库内容
        DatabaseReader reader = new DatabaseReader.Builder(database).build();

        InetAddress ipAddress = InetAddress.getByName(userIP);
        //获取查询结果
        CityResponse response = null;
        try {
            response = reader.city(ipAddress);

            //国家信息
            Country tmpCountry = response.getCountry();
            String country = tmpCountry.getName();
            //省份信息
            Subdivision tmpSubdivision = response.getMostSpecificSubdivision();
            String region = tmpSubdivision.getName();
            //城市信息
            City tmpCity = response.getCity();
            String city = tmpCity.getName();

            locList.add(country);
            locList.add(region);
            locList.add(city);
        } catch (GeoIp2Exception e) {
            locList.add(null);
            locList.add(null);
            locList.add(null);
        }
        return locList;
    }
}
