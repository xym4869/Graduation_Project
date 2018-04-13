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
        String line = value.toString();
        log.info(line);

        Text MacKey = new Text();
        line = Detect.Detect(line);
        String mac = Detect.getMac();

        if(Detect.detectStatus == 1){
            MacKey.set(mac);
            try {
                context.write(MacKey, new Text(line));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
