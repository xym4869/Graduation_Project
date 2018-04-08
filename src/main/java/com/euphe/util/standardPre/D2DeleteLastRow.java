package com.euphe.util.standardPre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
 * DeleteLastRow类中的SplitString方法实现了对日志文件多余的最后一列的删除操作
 */
public class D2DeleteLastRow {
	// **--待优化:有警告，不安全
	public static List<String> Delete(List<String> list) {
		try {
			switch (Detect.detectStatus) {
			// status用来标记是否需要继续进行预处理，1表示需要继续，0表示不需要继续进行
			case 1:
				// 数据中第9列为URL，暂时没用。第9列之后的数据为错误数据，删除
				for (int i = list.size(); i >= 9; i--) {
					list.remove(i - 1);
				}
				return list;
			default:
				Detect.detectStatus = 0;
				return null;
			}
		} catch (Exception e) {
			Detect.detectStatus = 0;
			return null;
		}
	}
}
