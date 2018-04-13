package com.euphe.util.standardUtil;

import java.util.ArrayList;
import java.util.List;

public class A1FindDomain {
    public static String[] keyWord = { "51y5", "freewifi", "lianwifi", "msfwifi", "qqwifi", "surfo", "wifiapi", "wifikey",
            "guoluke", "360freewifi", "faxianwifi", "yumiyidong", "miwifi", "wififreekey", "510wifi", "hongbaowifi",
            "wifienjoy", "wifimaster", "pengwifi", "lianwangshenqi", "as568", "yun", "pan.baidu", "xcloud",
            "upaiyun", "pluloud", "cloudplayer", "hiphotos", "cloud", "storage", "scloud", "yuntv", "pan.iqiyi",
            "avlyun", "sinastorage", "micloud", "qiniucdn", "yunpan", "AVOS Cloud", "sandi", "coolyun",
            "voicecloud", "xunlei", "aliyun", "ksyun", "kuaipan", "hicloud", "bmob", "yunduan", "homecloud",
            "dbank", "apicloud", "115", "lvmama", "hwclouds", "leancloud", "myqcloud", "jcloud", "lecloud",
            "qcloud", "weiyun", "wapmap", "maps", "ditu", "map", "ipadnav", "daohang", "mapbar", "imap", "amap",
            "tracking", "ctrip", "tuniu", "web2mi", "qunar", "tieyou", "elong", "sunflower", "zhuna", "plateno",
            "keyunzhan", "huoche", "kuxun", "mapabc", "go2map", "40017", "tigerknows", "xiaozhu", "market",
            "taobao", "gouwu", "shop", "360buying", "360buy", "mengo360", "mogujie", "starschina", "suning",
            "chaoshi", "mizhe", "miya", "omigo", "alipaipai", "home.mi", "juhuasuan", "anjuke", "jd", "tmall",
            "meilishuo", "chuchujie", "xbiao", "jumei", "hmeili", "marykay", "vipshop", "yiche", "dameiren", "58",
            "ymatou", "enjoyz", "fang", "bantang", "che168", "hichao", "zhe800", "taofen8", "yhd", "koudai",
            "dangdang", "leju", "rakuten", "ichuanyi", "liwushuo", "megou", "cuxiao", "amazon", "lechange",
            "qiangqiang5", "lefei", "patek", "ppdai", "duitang", "appmedia", "youxuan", "qiao.baidu", "adview",
            "youmi", "qumi", "miidi", "microad", "kaitianad", "duomeng", "adsmogo", "inmobi", "jwmobi", "o2omobi",
            "admob", "appcpa", "wapx", "cocounion", "qtmojo", "optaim", "thinkdifferent", "mediav", "adpro",
            "punchbox", "xxxmeng", "appleweb", "cbs.baidu", "Mobile Safari", "MQQBrowser", "browser", "ucweb",
            "qqweb", "mse.sogou", "ie.sogou", "startpage", "miuibrowser", "hao.xiaomi", "hao.360", "chrome",
            "ksmobile", "firefox", "AppleWebKit", "hao.uc", "uoboo", "haosou", "easou", "juwan", "pc120",
            "zhongsou", "hao123", "umeweb", "265", "dolphin-browser", "so", "360", "sina", "baidu", "google",
            "sogou", "duba", "sohu", "yahoo", "ifeng", "bing", "tclclouds", "726", "50bang", "3g", "liebao", "qqwx",
            "vqq", "ipadqq", "weixin", "qq", "wecall.qq", "mcapp", "qzone", "weibo", "tieba", "yuyin", "wangxin",
            "wangwang", "cootekservice", "haoma", "blog", "woocall", "chat.xiaomi", "momo", "immomo", "call.360",
            "adwo", "moxz", "bbs", "changyan", "appleiphonecell", "baihe", "95195", "520guimi", "livetalk",
            "lianluo", "idol001", "yixin", "youyuan", "cootekservice", "teddymobile", "imlianai", "renren",
            "easemob", "mail", "qqmail", "jingwei", "wechat", "oneniceapp", "17app", "tianqi", "goski", "kmail",
            "highing", "163", "dianxin", "playbobo", "inbilin", "qxiu", "9xiu", "uxin", "dianhua", "dongting",
            "ttpod", "189", "ufoto", "talker", "wealoha", "lofter", "uu08", "laiwang", "myhug", "gotye", "zhihu",
            "ditiezu", "xcar", "email", "hd.sohu", "tv.sohu", "sohuvideohd", "film.sohu", "video", "qqlive",
            "sohutv", "qqvideo", "vrs.sohu", "baofeng", "TudouIPad", "qiyiipadvideo", "Imgo", "dianying", "qiyi",
            "iqiyi", "letv", "youku", "youkuhd", "smartvideo", "kan.sogou", "videos", "mangotv", "moviebox",
            "qiyividio", "kuaibo", "AppleCoreMedia", "arcsoft", "player", "hunantv", "baomihua", "moretv", "cntv",
            "brightcove", "xiaomv", "xiguaplayer", "tudou", "yunfan", "funshion", "56", "acfun.tv", "tv.ifeng",
            "pptv", "kukuplay", "pps", "bilibili", "m1905", "soku", "pplive", "zplay", "huya", "wandafilm", "kwai",
            "uitv", "plu", "zhanqi", "mtime", "douyutv", "tvesou", "cctv", "zhangyu", "cutv", "molitv", "flvcd",
            "wasu", "yixia", "317abc", "winksi", "sqimg", "photo", "qqimg", "3gimg", "qpic", "gtimg", "mmbiz",
            "bookimg", "photocdn", "baidu-img", "imgsrc", "timg.baidu", "xiangce", "iphotos", "qlogo", "imgstore",
            "logo", "singerimg", "sinaimg", "bdimg", "png", "meitu", "wdjimg", "android", "linux", "IOS", "miui",
            "windows", "pnewsapp", "infoapp", "kuaibao", "inews", "new", "new-event", "ipadnavnews", "cnews",
            "qqnews", "sohunews", "ipadnews", "news.baidu", "toutiao", "zzd.sm", "news.sogou", "newdiscoveryapi",
            "kandian", "bgl.shenchuang", "zhuyoukong", "miercn", "nytimes", "jiemian", "xinmin", "mingxing",
            "happyjuzi", "thepaper", "inveno", "huanqiu", "soso", "dongting", "gfan", "chouti", "people", "ithome",
            "xinhuanet", "yidianzixun", "gmw", "chinanews", "yinyueshiting", "musicdata", "voice", "music",
            "qqmusic", "qzone-music", "qingting", "ting.baidu", "baiduyinyue", "kugou", "musics", "song", "5sing",
            "iting360", "music360", "juangua", "duomi", "kuwo", "fanxing", "kktv", "changba", "10155", "yinyuetai",
            "audiocn", "shoujiduoduo", "12530", "sports", "nbachina", "finance", "show", "mytown", "appfeed",
            "qqshow", "didi", "maweb", "auto.sohu", "chihe.sohu", "himg.baidu", "pos.baidu", "zhidao", "xiangji",
            "baidupcs", "mpush", "tuisong", "kankan", "mo.baidu", "ufosdk.baidu", "zuoye", "suggestion", "wapiknow",
            "weather", "baichuan", "gushitong", "share.baidu", "shoujiweishi", "wenku", "zhidahao", "wapwenku",
            "carwash", "callcar", "gupiao", "baike", "fanyi", "oil.baidu", "jiankang", "mama.baidu", "baobao.baidu",
            "jingyan", "waimai", "weidaijia", "qingpai", "jiaoyu", "lvyou", "shitu", "ikan", "muzhi", "xueshu",
            "google-analytics", "translate", "tools", "suggest", "huodong", "dianxinos", "mycheer", "ksmobile",
            "hiido", "gifshow", "meizi", "miaozhen", "weatherapi", "zhannei", "shouji", "bizhi", "zhushou",
            "pinyin", "yy", "fuwu", "wenwen", "12306.ie", "piao", "xibaibai", "photosync", "lyrics", "userinfo",
            "5singlive", "tianqitong", "huangye", "lives.sina", "paikeimg", "house", "fashion", "mihome",
            "shenghuo", "zhuti", "umeng", "iknow", "wallpaper", "360safe", "kuaidi", "shake", "jiagu", "camera360",
            "360launcher", "rong360", "kuaikan", "igexin", "happyelements", "screenimgs", "hiapk", "BMW",
            "shenchuang", "crntt", "adsame", "autohome", "haitou", "dianping", "mucang", "kakamobi", "itunes",
            "iiapple", "miaopai", "tencentmind", "touchcdn", "mikecrm", "nuomi", "eastmoney", "kankanmi", "xiumi",
            "midou8", "laobingmi", "comicool", "hunliji", "doumi", "mediatek", "cnzz", "dianru", "189store",
            "idol001", "wochacha", "51wnl", "mmarket", "mfniu", "meituan", "hotel", "appcss", "hitop", "moji",
            "moxiu", "99", "linghit", "7daysinn", "coohua", "etouch", "zuanke8", "qiqu", "zuche", "pingan",
            "mytouch", "gucheng", "dxsvr", "mob", "ximalaya", "domob", "magiqmobile", "souyue", "baidao", "haodf",
            "hupu", "maka", "wanlitong", "iciba", "domobile", "10jqka", "emoney", "ganji", "cdeledu", "ikamobile",
            "xiuxiu", "yunos", "duapp", "airtake", "dajie", "dandanz", "renrentou", "xiaoying", "youdao", "jyeoo",
            "dfcfw", "ishuaji", "winbaoxian", "queyueapp", "caijing", "cnfol", "yyfensi", "peiyu", "eqxiu",
            "189store", "limei", "gzyouai", "wps", "tongbanjie", "10010", "speiyou", "qiushibaike", "crasheye",
            "tvos", "baifendian", "xueyazhushou", "at321", "pc120", "scw98", "budejie", "huisuoping", "xiaokaxiu",
            "wo116114", "sqreader", "feeyun", "ycp", "tuan800", "hexun", "jxedt", "welove520", "51yund", "raiyi",
            "xiaoenai", "xuechebu", "ishansong", "51job", "10101111", "qbb6", "rootjl", "holaworld", "hzins",
            "edaijia", "zhhwnl", "xiachufang", "didapinche", "tusdk", "365rili", "gewara", "ledongli", "jiedaibao",
            "manhuaren", "daoyoudao", "fanli", "douban", "tingtingfm", "51aiya", "fotoable", "shuzilm", "ttxuanpai",
            "ixiaochuan", "233", "jesgoo", "kuaikanmanhua", "lovebizhi", "utouu", "kaoyan", "costoon", "bangcle",
            "feidee", "youban", "shurufa", "geetest", "kaolafm", "mgyun", "shuame", "admaster", "cloudfront",
            "xingcloud", "iFLYCloud", "gw", "ip138", "ludashi", "wodeweilai", "zdworks", "ihg", "kuaiyong", "10086",
            "wawachina", "babytree", "shiguangxiaowu", "lfkjgs", "zol", "1923", "mbrev", "dianwoda", "baixing",
            "52dangong", "primex", "10010", "baicizhan", "x-bull", "weizhang", "xywy", "7wenta", "9610",
            "webterren", "niuguwang", "zhcw", "jpush", "easyread", "study", "alicdn", "lenovomm", "store",
            "appstore", "apistore", "mall", "wandoujia", "25pp", "ijinshan", "hispaceclt", "appchina", "myapp",
            "mycheering", "91", "mgyapp", "goapk", "anzhi", "coolmart", "coco", "baoruan", "iyouxi", "tvgame",
            "poker", "game", "qqgame", "wegame", "huanle", "minigame", "daoju", "feiji", "lol", "gamehall", "iwan",
            "youxi", "qform", "buluo", "xinyue", "zhen.qq", "wan.sohu", "gamecenter", "baiduwebgame", "baidu-mgame",
            "duokoo", "wan.sogou", "group.wandoujia", "mario", "gamebox", "wan.360", "lianmeng", "360game",
            "zygame", "anzogame", "zhangyoubao", "popcap", "wali", "cmgame", "doyo", "microfun", "hanjiangsanguo",
            "jj", "easymobi", "eamobile", "le4", "sdo", "lianlianbox", "rekoo", "173app", "tianmashikong", "mu77",
            "910app", "61", "9377", "zplay", "shuowan", "tuyoo", "leyogame", "zhuoyigame", "kkk5", "xy", "4399api",
            "skymoons", "ktplay", "ijunhai", "tuboshu", "6513", "kunlun", "17188", "rovio", "sanguosha",
            "snailfighter", "365you", "yunva", "pizi.cc", "61ns", "dodoeasy", "book", "allreader", "iyuedu", "read",
            "reader", "wfreader", "weread", "bookshelf", "coolread", "wfqqreader", "ipadnovel", "baiyue",
            "xiaoshuo", "dushu", "shucheng", "ikanshu", "yuedu", "easou", "qidian", "mitang", "mianhuatang", "tadu",
            "zhangyue", "ireader", "duokan", "zhuishushenqi", "txtbook", "shuqi", "dushubus", "bingodu", "xianguo",
            "cmread", "docin", "iqiyoo", "99csw", "i7wu", "1kanshu", "zongheng", "wanmeiyuedu", "alipay", "tenpay",
            "pay.baidu", "pay", "card", "unipay", "qqwallet", "bkpay", "zhifu", "innerpay", "upay360", "mibi",
            "bestpay", "icbc" };


    public static List<String> Analyze(String domainInfo) {
        String[] names = domainInfo.split("\\.");
        List<String> recordDomainKeyWords = new ArrayList<String>();

        for (int i = 0; i < names.length; i++) {
            if (!(names[i].equals("com")) && !(names[i].equals("cn")) && !(names[i].equals("c"))
                    && !(names[i].equals("com:80") && !(names[i].equals("net")) && !(names[i].equals("com:8080")))) {
                for (int j = 0; j < keyWord.length; j++) {
                    if (names[i].equals(keyWord[j])) {
                        recordDomainKeyWords.add(keyWord[j]);
                        break;
                    }
                }
            }
        }

        // 输出的Domain信息必须为3列，解析不出来的用null填充
        while (recordDomainKeyWords.size() < 3) {
            recordDomainKeyWords.add(null);
        }
        return recordDomainKeyWords;
    }
}
