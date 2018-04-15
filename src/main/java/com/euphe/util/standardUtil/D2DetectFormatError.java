package com.euphe.util.standardUtil;

import java.util.List;

public class D2DetectFormatError {
    public static List<String> Detect(List<String> tempList) {
        try{
            switch (Detect.detectStatus){
                // status用来标记是否需要继续进行预处理，1表示需要继续，0表示不需要继续进行
                case 1:
                    // 检测一行数据中是否有格式错误
                    if(!IsMAC.IsMatch(tempList.get(Location.MAC))){
                        Detect.detectStatus = 0;
                        return null;
                    }

                    if(!IsSocket.IsMatch(tempList.get(Location.VPN)))
                        tempList.set(Location.VPN, null);

                    if(!IsSocket.IsMatch(tempList.get(Location.SITE)))
                        tempList.set(Location.SITE, null);

                    if (!IsSocket.IsMatch(tempList.get(Location.WEBSITE)))
                        tempList.set(Location.WEBSITE, null);

                    if(IsSocket.IsMatch(tempList.get(Location.DOMAIN)))
                        tempList.set(Location.DOMAIN, null);

                    if(tempList.get(Location.UA).length() == 0)
                        //如果UserAgent一列为空，则填充为null
                        tempList.set(Location.UA, null);

                    if(!IsURL.IsMatch(tempList.get(Location.URL)))
                        tempList.set(Location.URL, null);
                    
//                    if(!IsURL.IsMatch(tempList.get(Location.URL2)))
//                        tempList.set(Location.URL2, null);
                    
                    Detect.mac = tempList.get(Location.MAC);
                    return tempList;

                default:
                    Detect.detectStatus = 0;
                    return null;
            }
        }catch (Exception e){
            Detect.detectStatus = 0;
            return null;
        }

    }
}
