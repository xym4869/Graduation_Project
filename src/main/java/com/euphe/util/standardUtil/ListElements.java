package com.euphe.util.standardUtil;

import java.util.ArrayList;
import java.util.List;
///import org.junit.Test;

public class ListElements {
	public static String getElement(List<String> strList, int index){
		//返回List中指定位置的元素。第一列index为0
		String str = new String("");
		str = strList.get(index);
		return str;
	}
	
	public static List<String> getElements(List<String> strList, int index,int length){
		//返回List中从指定位置indexS开始，之后length列元素的列表（包含index）。第一列index为0
		List<String> elementsList = new ArrayList<String>();
		for(int i=0;i<length;i++){
			String str = strList.get(index+i);
			elementsList.add(str);
		}
		return elementsList;
	}
	
	public static List<String> removeElement(List<String> strList, int index){
		strList.remove(index);
		return strList;
	}
	
	public static List<String> removeElements(List<String> strList, int index,int length){
		//移除List中从指定位置index开始，及之后的length列元素
		//返回移除元素后的List
		for(int i = 0; i < length; i++){
			strList.remove(index);
		}
		return strList;
	}
	
	public static List<String> addElement(List<String> strList, int index, String text){
		strList.add(index, text);
		return strList;
	}
	
	public static List<String> addElements(List<String> strList, int index,List<String> Elements){
		//在List的指定位置，插入列表Elements中的元素
		//返回插入元素后的List
		for(int i = 0; i < Elements.size(); i++){
			strList.add(index+i, Elements.get(i));
		}
		return strList;
	}
	
	public static List<String> replaceElement(List<String> strList, int index, String text){
		//在List的指定位置，用单个Elements来替换掉指定位置的元素
		strList.remove(index);
		strList.add(index, text);
		return strList;
	}
	
	public static List<String> replaceElement(List<String> strList, int index,List<String> Elements){
		//在List的指定位置，用List Elements来替换掉指定位置的元素
		//返回插入元素后的List
		strList.remove(index);
		for(int i = 0; i < Elements.size(); i++){
			strList.add(index+i, Elements.get(i));
		}
		return strList;
	}

}
