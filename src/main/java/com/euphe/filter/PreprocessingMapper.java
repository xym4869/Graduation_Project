package com.euphe.filter;

import com.euphe.filter.SecondarySort.MACTimePair;
import com.euphe.util.standardPre.Detect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PreprocessingMapper extends Mapper<LongWritable, Text, MACTimePair, Text> {
	public static Log log = LogFactory.getLog(PreprocessingMapper.class);

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// 得到输入的每一行数据
		String line = value.toString();
		log.info(line);

		line = Detect.Detect(line);
		String mac = Detect.getMAC();
		String time = Detect.getTime();
		
		if (Detect.detectStatus == 1) {
			MACTimePair mtp = new MACTimePair();
			mtp.set(mac, time);
			context.write(mtp, new Text(line));
		}
	}
}
