package com.euphe.thread;

import com.euphe.filter.FilterJob;
import com.euphe.util.HUtils;
import org.apache.hadoop.util.ToolRunner;

public class Filter2hdfs implements Runnable {
    private String input;
    private String output;

    public Filter2hdfs(String input, String output) {
        this.input = input;
        this.output = output;
    }
    @Override
    public void run() {
        String args[] = {
                HUtils.getHDFSPath(input),//获取输入
                HUtils.getHDFSPath(output)//获取输出
        };
        try {
            ToolRunner.run(HUtils.getConf(), new FilterJob(), args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
