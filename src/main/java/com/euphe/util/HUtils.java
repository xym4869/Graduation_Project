package com.euphe.util;

import com.euphe.model.CurrentJobInfo;
import com.euphe.model.LogData;
import com.euphe.util.standardUtil.StringListTools;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobStatus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Hadoop 工具类
 *
 */
public class HUtils {

    // 最原始文件在HDFS上的存储路径
    //public static final String SOURCEFILE = "/minitest/testData.log";
    public static final String DOWNLOAD_EXTENSION = ".log";// 下载文件的后缀名
    public static final int FILTER_FILES = 3;//由数据库到hdfs产生的文件数
    private static final String FILTER_FILES_PATH = "/miniFinalRes/finalFile";
    ////UserAgent文件的路径和分隔符
    //public static final String UAPATH = "WEB-INF/res/UserAgent.csv";
    //public static final String UASEPARATOR = ",";
    ////DomainKeywords文件的路径和分隔符
    //public static final String DOMAINKEYWORDSPATH = "WEB-INF/res/DomainKeywordsList.csv";
    //public static final String DOMAINKEYWORDSSEPARATOR = ",";

    private static Configuration conf = null;
    private static FileSystem fs = null;

    public static boolean flag = true; // get configuration from db or file
                                       // ,true : db,false:file

    public static int JOBNUM = 1; // 一组job的个数
    private static long jobStartTime = 0L;// 使用 System.currentTimeMillis() 获得
    private static JobClient jobClient = null;

    public static long getJobStartTime() {
        return jobStartTime;
    }

    public static void setJobStartTime(long jobStartTime) {
        HUtils.jobStartTime = jobStartTime;
    }

    public static JobClient getJobClient() {
        if (jobClient == null) {
            try {
                jobClient = new JobClient(getConf());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jobClient;
    }

    public static void setJobClient(JobClient jobClient) {
        HUtils.jobClient = jobClient;
    }

    public static Configuration getConf() {

        if (conf == null) {
            conf = new Configuration();
            // get configuration from db or file
            conf.setBoolean("mapreduce.app-submission.cross-platform", "true"
                    .equals(Utils.getKey(
                            "mapreduce.app-submission.cross-platform", flag)));// 配置使用跨平台提交任务
            conf.set("fs.defaultFS", Utils.getKey("fs.defaultFS", flag));// 指定namenode
            conf.set("mapreduce.framework.name",
                    Utils.getKey("mapreduce.framework.name", flag)); // 指定使用yarn框架
            conf.set("yarn.resourcemanager.address",
                    Utils.getKey("yarn.resourcemanager.address", flag)); // 指定resourcemanager
            conf.set("yarn.resourcemanager.scheduler.address", Utils.getKey(
                    "yarn.resourcemanager.scheduler.address", flag));// 指定资源分配器
            conf.set("mapreduce.jobhistory.address",
                    Utils.getKey("mapreduce.jobhistory.address", flag));
        }

        return conf;
    }

    public static FileSystem getFs() {
        if (fs == null) {
            try {
                fs = FileSystem.get(getConf());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fs;
    }

    /**
     * 返回HDFS路径
     *
     * @param url
     * @return fs.defaultFs+url
     */
    public static String getHDFSPath(String url) {

        return Utils.getKey("fs.defaultFS", flag) + url;
    }

    /**
     * 上传本地文件到HFDS
     *
     * @param localPath
     * @param hdfsPath
     * @return
     */
    public static Map<String, Object> upload(String localPath, String hdfsPath) {
        Map<String, Object> ret = new HashMap<String, Object>();
        FileSystem fs = getFs();
        Path src = new Path(localPath);
        Path dst = new Path(hdfsPath);
        try {
            fs.copyFromLocalFile(src, dst);//上传到HDFS
            Utils.simpleLog(localPath+"上传至"+hdfsPath+"成功");
        } catch (Exception e) {
            ret.put("flag", "false");
            ret.put("msg", e.getMessage());
            e.printStackTrace();
            return ret;
        }
        ret.put("flag", "true");
        return ret;
    }

    /**
     * 下载文件
     * @param hdfsPath
     * @param localPath
     *            ,本地文件夹
     * @return
     */
    public static Map<String, Object> downLoad(String hdfsPath, String localPath) {
        Map<String, Object> ret = new HashMap<String, Object>();
        FileSystem fs = getFs();
        Path src = new Path(hdfsPath);
        Path dst = new Path(localPath);
        try {
            RemoteIterator<LocatedFileStatus> fss = fs.listFiles(src, true);
            int i = 0;
            while (fss.hasNext()) {
                LocatedFileStatus file = fss.next();
                if (file.isFile() && file.toString().contains("part")) {//只下载文件名包含“part”的文件，也就是结果文件
                    // 使用这个才能下载成功
                    fs.copyToLocalFile(false, file.getPath(), new Path(dst,
                            "hdfs_" + (i++) + HUtils.DOWNLOAD_EXTENSION), true);
                }
            }
        } catch (Exception e) {
            ret.put("flag", "false");
            ret.put("msg", e.getMessage());
            e.printStackTrace();
            return ret;
        }
        ret.put("flag", "true");
        return ret;
    }

    /**
     * 根据时间来判断，然后获得Job的状态，以此来进行监控 Job的启动时间和使用system.currentTimeMillis获得的时间是一致的，
     *
     *
     * @return
     * @throws IOException
     */
    public static List<CurrentJobInfo> getJobs() throws IOException {
        JobStatus[] jss = getJobClient().getAllJobs();//返回所有的Job，不管是失败还是成功的
        List<CurrentJobInfo> jsList = new ArrayList<CurrentJobInfo>();
        jsList.clear();
        for (JobStatus js : jss) {
            if (js.getStartTime() > jobStartTime) {//只查找任务启动时间在jobStartTime之后的任务
                jsList.add(new CurrentJobInfo(getJobClient().getJob(
                        js.getJobID()), js.getStartTime(), js.getRunState()));
            }
        }
        Collections.sort(jsList);
        return jsList;
    }

    /**
     * 判断一组MR任务是否完成
     *
     * @param currentJobInfo
     * @return
     */
    public static String hasFinished(CurrentJobInfo currentJobInfo) {

        if (currentJobInfo != null) {
            if ("SUCCEEDED".equals(currentJobInfo.getRunState())) {
                return "success";
            }
            if ("FAILED".equals(currentJobInfo.getRunState())) {
                return "fail";
            }
            if ("KILLED".equals(currentJobInfo.getRunState())) {
                return "kill";
            }
        }

        return "running";
    }

    /**
     * List  解析入HDFS
     *
     * @param list
     * @param url
     * @param fileNums
     * @throws IOException
     */
    public static void db2hdfs(List<Object> list, String url, int fileNums) {
        if(fileNums <= 0 || fileNums > 9)
            fileNums = HUtils.FILTER_FILES;
        int everyFileNum = (int)Math.ceil((double)list.size() / fileNums);//根据输入记录数和输出文件数计算每个文件存储的记录数
        Path path = null;
        int start = 0;
        int end = start + everyFileNum;
        for(int i=0; i<fileNums; i++){
            //如果url为空，那么使用默认的即可，否则使用提供的路径
            path = new Path(url == null ? HUtils.FILTER_FILES_PATH : url + "/part-r-0000" + i + ".log");//指定文件输出目录和文件名
            if(end > list.size())
                end = list.size();
            try{
                db2hdfs(list.subList(start, end), path);
                start = end;
                end += everyFileNum;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Utils.simpleLog("db2hdfs 全部解析上传完成！");
    }

    private static boolean db2hdfs(List<Object> list, Path path) throws IOException {
        boolean flag = false;
        int recordNum=0;
        SequenceFile.Writer writer = null;
        Configuration conf = getConf();
        try {
            SequenceFile.Writer.Option optPath = SequenceFile.Writer.file(path);
            SequenceFile.Writer.Option optKey = SequenceFile.Writer.keyClass(IntWritable.class);
            SequenceFile.Writer.Option optVal = SequenceFile.Writer.valueClass(Text.class);
            writer = SequenceFile.createWriter(conf, optPath, optKey, optVal);
            for (Object wlog : list) {
                if(!checkLogData(wlog)){
                    continue; // 不符合规则
                }
                Text dval = new Text(getArr(wlog).getBytes("UTF-8"));
                dval = transformTextToUTF8(dval,"GBK");
                IntWritable dkey = new IntWritable(getIntVal((wlog)));
                writer.append(dkey, dval);
                recordNum++;
            }
        } catch (IOException e) {
            Utils.simpleLog("db2HDFS失败,+hdfs file:"+path.toString());
            e.printStackTrace();
            flag =false;
        } finally {
            IOUtils.closeStream(writer);
        }
        flag=true;
        Utils.simpleLog("db2HDFS 完成,hdfs file:"+path.toString()+",records:"+recordNum);
        return flag;
    }

    private static Text transformTextToUTF8(Text text, String encoding) {
        String value = null;
        try{
            value = new String(text.getBytes(), 0 ,text.getLength(), encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Text(value);
    }

    private static int getIntVal(Object wlog) {
        LogData ld = (LogData)wlog;
        return ld.getId();
    }

    private static String getArr(Object wlog) {
        List<String> arr = new ArrayList<String>();
        LogData ld = (LogData)wlog;
        arr.add(ld.getTime().toString());
        arr.add(ld.getMAC());
        arr.add(ld.getVPNIP());
        arr.add(ld.getUserIP());
        arr.add(ld.getWebsiteIP());
        arr.add(ld.getDomainKey1());
        arr.add(ld.getDomainKey2());
        arr.add(ld.getDomainKey3());
        arr.add(ld.getOsFamily());
        arr.add(ld.getOsName());
        arr.add(ld.getUaFamily());
        arr.add(ld.getBrowserVersionInfo());
        arr.add(ld.getDeviceType());
        arr.add(ld.getType());
        arr.add(ld.getProtocol());
        arr.add(ld.getHost());
        return StringListTools.ListToString(arr, "\t") + "\n";
    }

    private static boolean checkLogData(Object wlog) {
        LogData ld = (LogData)wlog;
        if(ld.getDomainKey1() == null)
            return false;
        if(ld.getOsFamily().equals("unknown") && ld.getUaFamily().equals("unknown") && ld.getType().equals("unknown") )
            return false;
        return true;
    }
}
