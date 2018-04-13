package com.euphe.util.standardUtil;

import java.util.List;

/*
 * DetectTimeError类中的Detect方法实现了对日志文件第一列的错误检测并修正
 */
public class D1DetectTimeError {
    public static int countLine = 0;
    public static int countError = 0;

    public static List<String> Detect(List<String> tempList) {
        try{
            switch (Detect.detectStatus){
                // status用来标记是否需要继续进行预处理，1表示需要继续，0表示不需要继续进行
                case 1:
                    // 取出List中的第一个元素。示例：2015-11-04 00:02:23
                    String tempString = tempList.remove(Location.DATE_TIME);

                    //正则表达式判断时间格式，不符合
                    if(!IsTime.IsMatch(tempString)){
                        countError++;
                        System.out.println("Line:" + countLine + " ErrorNum:" + countError + " " + tempList.get(0));
                        // 删除该行
                        Detect.detectStatus = 0;
                        return null;
                    }

                    String[] tempStrings = tempString.split(" ");

                    // 将分割出的元素重新保存到List中
                    for(int i = 0; i < 2; i++)
                        tempList.add(i, tempStrings[i]);

                    D1FindTime ft = new D1FindTime();
                    String date = ft.getTime();
                    countLine++;
                    if(!tempList.get(Location.DATE).equals(date)){
                        countError++;
                        System.out.println("Line:" + countLine + " ErrorNum:" + countError + " " + tempList.get(0));
                        // 删除该行
                        Detect.detectStatus = 0;
                        return null;
                    }
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
