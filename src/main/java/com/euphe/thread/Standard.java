package com.euphe.thread;

import com.euphe.filter.StandardJob;
import com.euphe.util.HUtils;
import org.apache.hadoop.util.ToolRunner;

import java.io.Serializable;

public class Standard implements Runnable, Serializable{
    private String input;
    private String output;

    public Standard(String input,String output){
        this.input=input;
        this.output=output;
    }
    @Override
    public void run() {
        String [] args ={
                HUtils.getHDFSPath(input),//获取输入路径
                HUtils.getHDFSPath(output)//获取输出路径
        };
        try {
            ToolRunner.run(HUtils.getConf(), new StandardJob(),args );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
