package com.euphe.util.standardUtil;

import java.util.List;

public class D3DeleteMoreRow {
    public static List<String> Delete(List<String> tempList) {
        try {
            switch (Detect.detectStatus){
                case 1:
                    for(int i = tempList.size(); i > 9; i--)
                        tempList.remove(i-1);
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
