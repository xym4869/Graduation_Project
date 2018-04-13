package com.euphe.filter.mr;

import com.euphe.util.standardUtil.Analyze;
import com.euphe.util.standardUtil.StringListTools;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreprocessingReducer extends Reducer<Text, Text, NullWritable, Text> {
    public static Log log = LogFactory.getLog(PreprocessingReducer.class);

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        try {
            String line = "";
            String analyze = "";
            String result = "";
            List<String> resultList = new ArrayList<String>();
            for (Text value : values) {
                line = value.toString();
                analyze = Analyze.Analyze(line);

                if (analyze != null)
                    resultList.add(analyze);
            }
            result = StringListTools.ListToString(resultList, "\n");
            context.write(NullWritable.get(), new Text(result));
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
