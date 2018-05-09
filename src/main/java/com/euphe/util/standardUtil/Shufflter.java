package com.euphe.util.standardUtil;

import java.util.ArrayList;
import java.util.List;

public class Shufflter {
    public static boolean shufflter(String line){
        boolean fflg = true;
        List<String> tmpList = new ArrayList<String>();
        tmpList = StringListTools.StringToList(line, "\t");

        try{
            String dk1 = tmpList.get(Location.DK1);
            String of = tmpList.get(Location.osFamily);
            String uf = tmpList.get(Location.uaFamily);
            String ty = tmpList.get(Location.type);
            if(dk1.equals("null")
                    && of.equals("unknown")
                    && uf.equals("unknown")
                    && ty.equals("unknown")) {
                fflg = false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return fflg;
    }
}
