package com.collect.user_luo.mycollect;

/**
 * 作者：罗发新
 * 时间：2018/12/5 0005    16:05
 * 邮件：424533553@qq.com
 * 说明：
 */
public class HardwareInfo {
    private int steelyardtype = 0;//0:商通称,1：香山7.0,2:香山15.6-1:其他型号秤待定
    private Userinfo userinfo = new Userinfo();
    private Errorinfo errorinfo = new Errorinfo();
    private Deviceinfo deviceinfo = new Deviceinfo();

    public int getSteelyardtype() {
        return steelyardtype;
    }

    public void setSteelyardtype(int steelyardtype) {
        this.steelyardtype = steelyardtype;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public Errorinfo getErrorinfo() {
        return errorinfo;
    }

    public void setErrorinfo(Errorinfo errorinfo) {
        this.errorinfo = errorinfo;
    }

    public Deviceinfo getDeviceinfo() {
        return deviceinfo;
    }

    public void setDeviceinfo(Deviceinfo deviceinfo) {
        this.deviceinfo = deviceinfo;
    }


    private static class Userinfo {

        private int marketid = 11; //int  市场编号
        private String marketname = "黄田市场";
        private String companyno = "B070";
        private int tid = 101;  //秤号
        private String seller = "郭金龙";
        private int sellerid = 127;

        public int getMarketid() {
            return marketid;
        }

        public void setMarketid(int marketid) {
            this.marketid = marketid;
        }

        public String getMarketname() {
            return marketname;
        }

        public void setMarketname(String marketname) {
            this.marketname = marketname;
        }

        public String getCompanyno() {
            return companyno;
        }

        public void setCompanyno(String companyno) {
            this.companyno = companyno;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getSeller() {
            return seller;
        }

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }
    }

    private static class Errorinfo {

        String classpath = "com.axecom.smartweight.my.entity.DataDemoActivity";
        String errormessage = "空指针异常";
        String errortime = " 2018-01-01";

        public String getClasspath() {
            return classpath;
        }

        public void setClasspath(String classpath) {
            this.classpath = classpath;
        }

        public String getErrormessage() {
            return errormessage;
        }

        public void setErrormessage(String errormessage) {
            this.errormessage = errormessage;
        }

        public String getErrortime() {
            return errortime;
        }

        public void setErrortime(String errortime) {
            this.errortime = errortime;
        }
    }

    private static class Deviceinfo {
        String release = "4.1.2";  //系统版本	RELEASE	获取系统版本字符串。如4.1.2 或2.2 或2.3等	4.4.4
        String sdk = "19";   //系统版本值 SDK 系统的API级别 一般使用下面大的SDK_INT 来查看 19
        String brand = "华为";   //品牌 BRAND 获取设备品牌 Huawei
        String model = "荣耀9";  // 型号 MODEL

        String networkoperatorname = "中国联通";   // 网络类型名 getNetworkOperatorName 返回移动网络运营商的名字(SPN)中国联通
        String networktype = "3";   //网络类型 getNetworkType 3
        String phonetype = "1";    //手机类型 getPhoneType 手机类型 1
        String mac = "53:ff:a4:53:65:te";        //mac地址 getMacAddress

        public String getRelease() {
            return release;
        }

        public void setRelease(String release) {
            this.release = release;
        }

        public String getSdk() {
            return sdk;
        }

        public void setSdk(String sdk) {
            this.sdk = sdk;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getNetworkoperatorname() {
            return networkoperatorname;
        }

        public void setNetworkoperatorname(String networkoperatorname) {
            this.networkoperatorname = networkoperatorname;
        }

        public String getNetworktype() {
            return networktype;
        }

        public void setNetworktype(String networktype) {
            this.networktype = networktype;
        }

        public String getPhonetype() {
            return phonetype;
        }

        public void setPhonetype(String phonetype) {
            this.phonetype = phonetype;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }
    }


    /*data: {
steelyardtype:0  int
        userinfo：{
             marketid:11  int  市场编号
              marketname:"黄田市场"  String
              companyno:"B070"  String
           tid:101   int
           seller:"郭金龙"  String
           sellerid:127  int
        }

        errorinfo：{
            classpath:"com.axecom.smartweight.my.entity.DataDemoActivity"
            errormessage:" fasf "  String
            errortime:" 2018-01-01"  String
        }

        deviceinfo:{
            release:""  String  //系统版本	RELEASE	获取系统版本字符串。如4.1.2 或2.2 或2.3等	4.4.4
            sdk：""  String 系统版本值 SDK 系统的API级别 一般使用下面大的SDK_INT 来查看 19
            brand:""  String 品牌 BRAND 获取设备品牌 Huawei
            model:""  String 型号 MODEL

            networkoperatorname:"" String   网络类型名 getNetworkOperatorName 返回移动网络运营商的名字(SPN)中国联通
            networktype："" String  网络类型 getNetworkType 3
            phonetype："" 手机类型 getPhoneType 手机类型 1
            mac：""mac地址 getMacAddress

        }
}*/


}
