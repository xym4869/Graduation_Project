package com.euphe.filter;

import com.euphe.util.HUtils;
import com.euphe.util.Utils;
import com.euphe.util.standardUtil.Location;
import com.euphe.util.standardUtil.StringListTools;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.euphe.util.standardUtil.Shufflter.shufflter;

public class FilterJob extends Configured implements Tool {
    public static class Map extends Mapper<Object, Text, Text, Text> {
        private static Text text = new Text();

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            List<String> FirstList = new ArrayList<String>();
            FirstList = StringListTools.StringToList(line,"\t");
            String time = FirstList.get(Location.DATE_TIME);
            context.write(new Text(time), new Text(line));
        }
    }

    public static class Reduce extends Reducer<Text, Text, NullWritable, Text> {
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            try{
                String line = "";
                String result = "";
                boolean fflg = false;
                List<String> resultList = new ArrayList<String>();
                for(Text value : values){
                    line = value.toString();
                    fflg = shufflter(line);

                    if(fflg){
                        resultList.add(line);
                    }
                }
                result = StringListTools.ListToString(resultList, "\n");
                context.write(NullWritable.get(), new Text(result));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = HUtils.getConf();
        conf.set("mapreduce.job.jar", Utils.getRootPathBasedPath("WEB-INF/jars/filter.jar"));
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();//解析命令行参数
        if (otherArgs.length !=2) {//要求必须有输入和输出路径两个参数
            System.err.println("Usage: com.euphe.filter.FilterJob <in> <out>");
            System.exit(2);
        }
        Job job =  Job.getInstance(conf,"Filter input  :"+otherArgs[0]+" to "+otherArgs[1]);
        job.setJarByClass(FilterJob.class);
        job.setMapperClass(FilterJob.Map.class);
        job.setReducerClass(FilterJob.Reduce.class);
        job.setNumReduceTasks(1);

        //设置map输出的key value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        //设置reducer输出的key，value类型
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
        FileSystem.get(conf).delete(new Path(otherArgs[1]), true);//调用任务前先删除输出目录
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
