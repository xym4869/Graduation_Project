package com.euphe.util.standardPre;

import com.euphe.util.standardUtil.Location;

import java.util.List;

/*
 * DetectTimeError类中的Detect方法实现了对日志文件第一列的错误检测并修正
 */
public class D1DetectTimeError {
	// **--待优化:有警告，不安全
	public static int countLine = 0;
	public static int countError = 0;

	public static List<String> Detect(List<String> list) {
		try {
			switch (Detect.detectStatus) {
			// status用来标记是否需要继续进行预处理，1表示需要继续，0表示不需要继续进行
			case 1:
				// 取出List中的第一个元素。示例：2015-11-04 00:02:23
				String tmpString = list.remove(Location.DATE_TIME);
				// 将该元素进一步分割为2015-11-04和00:02:23
				String[] tmpStrings = tmpString.split(" ");
				// 将分割出的元素重新保存到List中
				// **--待优化：如果确定了一行的日期和时间，一定只是“日期
				// 时间”这种格式的话tmpStrings.length可换为2--**
				for (int i = 0; i < 2; i++) {
					list.add(i, tmpStrings[i]);
				}

				// query a right date
				D1FindTime ft = new D1FindTime();
				String date = ft.getTime();
				// Detect & delete
				countLine++;
				if (!list.get(Location.DATE).equals(date)) {
					countError++;
					System.out.println("Line:" + countLine + " ErrorNum:" + countError + " " + list.get(0));

					// 删除该行
					// 将result置为null之后需要修改FileToRecord,否则null对象无法iterator()
					/*
					 * Iterator<String> it = result.iterator(); itList.add(it);
					 */
					Detect.detectStatus = 0;
					list = null;
				}
				Detect.time = list.get(Location.TIME);
				//以MAC地址+时间作为Mapper的key
				Detect.mac = list.get(Location.MAC);
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
