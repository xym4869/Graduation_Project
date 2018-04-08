package com.euphe.filter;

import com.euphe.filter.SecondarySort.MACTimePair;
import com.euphe.util.standardPre.Analyze;
import com.euphe.util.standardUtil.MAC;
import com.euphe.util.standardUtil.StringListTools;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreprocessingReducer extends Reducer<MACTimePair, Text, NullWritable, Text> {
	public static Log log = LogFactory.getLog(PreprocessingReducer.class);
	private MultipleOutputs<NullWritable, Text> multipleOutputs;

	@Override
	protected void setup(Context context){
		multipleOutputs = new MultipleOutputs<NullWritable, Text>(context);
	}
	
	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException{
		multipleOutputs.close();
	}
	
	@Override
	protected void reduce(MACTimePair key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		try {
			MAC mac = new MAC(key.getMAC());
			String line = "";
			String analyze = "";
			String result = "";
			List<String> resultList = new ArrayList<String>();
			//values需要按照时间排序
			for (Text value : values) {
				line = value.toString();
				analyze = Analyze.Analyze(line);
				
				if (analyze != null) {
					resultList.add(analyze);
					mac.AddRecord(analyze);
				}
			}
			//在所有记录都添加完成后再计算该MAC地址在每个类上花费的时间，而不是添加一条算一次
			mac.CalDuration();
			result = StringListTools.ListToString(resultList, "\n");
			multipleOutputs.write("Records",NullWritable.get(), new Text(result), "Records/part");
			multipleOutputs.write("Summary",NullWritable.get(), new Text(mac.toString()), "Mac/part");
		} catch (Exception e) {
		}
	}

}
