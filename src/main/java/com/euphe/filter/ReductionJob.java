package com.euphe.filter;

import com.euphe.util.HUtils;
import com.euphe.util.Utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;

import java.io.IOException;

public class ReductionJob extends Configured implements Tool {
    public static class Map extends Mapper<Object, Text, Text, Text> {
        private static Text text = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            text = value;
            context.write(text, new Text());
        }
    }

    public static class Reduce extends Reducer<Text, Text, Text, Text> {
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            context.write(key, new Text());
        }
    }
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = HUtils.getConf();
        conf.set("mapreduce.job.jar", Utils.getRootPathBasedPath("WEB-INF/jars/redu.jar"));
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();//解析命令行参数
        if (otherArgs.length !=2) {//要求必须有输入和输出路径两个参数
            System.err.println("Usage: com.euphe.filter.ReductionJob <in> <out>");
            System.exit(2);
        }
        Job job =  Job.getInstance(conf,"Reduction input  :"+otherArgs[0]+" to "+otherArgs[1]);
        job.setJarByClass(ReductionJob.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setNumReduceTasks(1);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
        FileSystem.get(conf).delete(new Path(otherArgs[1]), true);//调用任务前先删除输出目录
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
