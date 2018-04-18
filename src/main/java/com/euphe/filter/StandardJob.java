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
        conf.set("mapreduce.job.jar", "C:\\Users\\xym48\\workplace\\preprocess\\src\\main\\resources\\mr.jar");
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();//解析命令行参数
        if (otherArgs.length !=2) {//要求必须有输入和输出路径两个参数
            System.err.println("Usage: com.euphe.filter.StandardJob <in> <out>");
            System.exit(2);
        }
        System.out.println("正在运行");
        Job job =  Job.getInstance(conf,"Standard input  :"+otherArgs[0]+" to "+otherArgs[1]);
        job.setJarByClass(StandardJob.class);
        job.setMapperClass(PreprocessingMapper.class);
        job.setReducerClass(PreprocessingReducer.class);
        job.setNumReduceTasks(1);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        System.out.println("添加输入输出");
        FileInputFormat.setInputPaths(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
        FileSystem.get(conf).delete(new Path(otherArgs[1]), true);//调用任务前先删除输出目录
        boolean b = job.waitForCompletion(true);
        if(!b) {
            System.err.println("This task has failed!!!");
        }
        System.out.println("运行结束");
        return b ? 0 : 1;
    }
}
