package com.euphe.filter;

import com.euphe.filter.mr.PreprocessingMapper;
import com.euphe.filter.mr.PreprocessingReducer;
import com.euphe.util.HUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;

public class StandardJob extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = HUtils.getConf();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();//解析命令行参数
        if (otherArgs.length !=2) {//要求必须有输入和输出路径两个参数
            System.err.println("Usage: com.euphe.filter.StandardJob <in> <out>");
            System.exit(2);
        }
        Job job =  Job.getInstance(conf,"Standard input  :"+otherArgs[0]+" to "+otherArgs[1]);
        job.setJarByClass(StandardJob.class);
        //job.setJar("/home/hadoop/pre1.jar");
        job.setMapperClass(PreprocessingMapper.class);
        job.setReducerClass(PreprocessingReducer.class);
        job.setNumReduceTasks(1);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
        FileSystem.get(conf).delete(new Path(otherArgs[1]), true);//调用任务前先删除输出目录

        return job.waitForCompletion(true) ? 0 : 1;
    }
}
