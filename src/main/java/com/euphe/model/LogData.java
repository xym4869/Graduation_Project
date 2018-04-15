package com.euphe.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name="logdata")
/**
 * 日志数据表
 */
public class LogData implements Serializable, ObjectInterface{
    private static final long serialVersionUID = 1L;
    private Integer Id;
    private Date Time;
    private String MAC;
    private String VPNIP;
    private String UserIP;
    private String WebsiteIP;

    private String DomainKey1;
    private String DomainKey2;
    private String DomainKey3;

    private String OsFamily;//操作系统家族
    private String OsName;//操作系统详细名称
    private String UaFamily;//浏览器名称
    private String BrowserVersionInfo;//浏览器版本
    private String DeviceType;//设备类型
    private String Type;//类型

    private String Protocol;//协议
    private String Host;//主机名
    //private String Path;//路径
    //private String Query;//请求信息

    public LogData() {
    }

    @Override
    public Object setObjectByMap(Map<String, Object> map) {
        LogData ld = new LogData();
        ld.setId((Integer)map.get("Id"));
        //ld.setDate((Date)map.get("Date"));
        ld.setTime((Date)map.get("Time"));
        ld.setMAC((String)map.get("MAC"));
        ld.setVPNIP((String)map.get("VPNIP"));
        ld.setUserIP((String)map.get("UserIP"));
        ld.setWebsiteIP((String)map.get("WebsiteIP"));

        ld.setDomainKey1((String)map.get("DomainKey1"));
        ld.setDomainKey2((String)map.get("DomainKey2"));
        ld.setDomainKey3((String)map.get("DomainKey3"));

        ld.setOsFamily((String)map.get("OsFamily"));
        ld.setOsName((String)map.get("OsName"));
        ld.setUaFamily((String)map.get("UaFamily"));
        ld.setBrowserVersionInfo((String)map.get("BrowserVersionInfo"));
        ld.setDeviceType((String)map.get("DeviceType"));
        ld.setType((String)map.get("Type"));

        ld.setProtocol((String)map.get("Protocol"));
        ld.setHost((String)map.get("Host"));
        //ld.setPath((String)map.get("Path"));
        //ld.setQuery((String)map.get("Query"));
        return ld;
    }
    public LogData(String[] s) throws ParseException {
        //this.Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s[1]);
        this.Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s[1]);
        this.MAC = s[2];
        this.VPNIP = s[3];
        this.UserIP = s[4];
        this.WebsiteIP = s[5];

        this.DomainKey1 = s[6];
        this.DomainKey2 = s[7];
        this.DomainKey3 = s[8];

        this.OsFamily = s[9];
        this.OsName = s[10];
        this.UaFamily = s[11];
        this.BrowserVersionInfo = s[12];
        this.DeviceType = s[13];
        this.Type = s[14];

        this.Protocol = s[15];
        this.Host = s[16];
        //this.Path = s[18];
        //this.Query = s[19];
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    //public java.util.Date getDate() {
    //    return Date;
    //}
    //
    //public void setDate(java.util.Date date) {
    //    Date = date;
    //}

    public java.util.Date getTime() {
        return Time;
    }

    public void setTime(java.util.Date time) {
        Time = time;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public String getVPNIP() {
        return VPNIP;
    }

    public void setVPNIP(String VPNIP) {
        this.VPNIP = VPNIP;
    }

    public String getUserIP() {
        return UserIP;
    }

    public void setUserIP(String userIP) {
        UserIP = userIP;
    }

    public String getWebsiteIP() {
        return WebsiteIP;
    }

    public void setWebsiteIP(String websiteIP) {
        WebsiteIP = websiteIP;
    }

    public String getDomainKey1() {
        return DomainKey1;
    }

    public void setDomainKey1(String domainKey1) {
        DomainKey1 = domainKey1;
    }

    public String getDomainKey2() {
        return DomainKey2;
    }

    public void setDomainKey2(String domainKey2) {
        DomainKey2 = domainKey2;
    }

    public String getDomainKey3() {
        return DomainKey3;
    }

    public void setDomainKey3(String domainKey3) {
        DomainKey3 = domainKey3;
    }

    public String getOsFamily() {
        return OsFamily;
    }

    public void setOsFamily(String osFamily) {
        OsFamily = osFamily;
    }

    public String getOsName() {
        return OsName;
    }

    public void setOsName(String osName) {
        OsName = osName;
    }

    public String getUaFamily() {
        return UaFamily;
    }

    public void setUaFamily(String uaFamily) {
        UaFamily = uaFamily;
    }

    public String getBrowserVersionInfo() {
        return BrowserVersionInfo;
    }

    public void setBrowserVersionInfo(String browserVersionInfo) {
        BrowserVersionInfo = browserVersionInfo;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getProtocol() {
        return Protocol;
    }

    public void setProtocol(String protocol) {
        Protocol = protocol;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    //@Column(length=1000)
    //public String getPath() {
    //    return Path;
    //}
    //
    //public void setPath(String path) {
    //    Path = path;
    //}
    //
    //@Column(length=1000)
    //public String getQuery() {
    //    return Query;
    //}
    //
    //public void setQuery(String query) {
    //    Query = query;
    //}
}
