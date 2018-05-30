package com.euphe.filter.mr;

import com.euphe.util.standardUtil.Detect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PreprocessingMapper extends Mapper<LongWritable, Text, Text, Text> {
    public static Log log = LogFactory.getLog(PreprocessingMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws  InterruptedException {
        String line = value.toString();//获取文件中一行内容
        log.info(line);

        Text MacKey = new Text();
        line = Detect.Detect(line);
        String mac = Detect.getMac();//将MAC值作为key

        if(Detect.detectStatus == 1){//判断是否有继续处理价值
            MacKey.set(mac);
            try {
                context.write(MacKey, new Text(line));//MAC值作为key，获取行作为value
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
