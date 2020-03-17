package com.swallow.architecture.net.base;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class BaseUrl {

    public static int TYPE_COUNT = 5;

    //数据类型
    public static final String UTF_8 = "Content-Type: application/json;charset=UTF-8";
    public static final String URLENCODED = "Content-Type: application/x-www-form-urlencoded";

    public static String TOKEN = "";

    public static String AUTHORIZATION = "";

    public static String WEBSOCKET_HOST_AND_PORT = "";

    //图片
    public static String IconPath = "";

    //文件下载
    public static String FILEDOWN = "";

    //协商平台 正式：域名
    private static final String IP = "http://ntp.sn.sgcc.com.cn/";
    private static final String WSSOCKET = "ws://ntp.sn.sgcc.com.cn/";

    public static String getHost(int type) {
        switch (type) {
            case 0:
                return IP;
            default:
                return IP;
        }
    }

    public static void setTOKEN(String TOKEN) {
        BaseUrl.TOKEN = TOKEN;
        BaseUrl.AUTHORIZATION = "Bearer " + TOKEN;
        BaseUrl.IconPath = BaseUrl.IP + "zuul/acloud-file-center/file/oper/download?access_token=" + TOKEN
                + "&fileId=";
        BaseUrl.WEBSOCKET_HOST_AND_PORT = WSSOCKET + "websocket?access_token=" + TOKEN;
        BaseUrl.FILEDOWN = IP + "ntp-negotiation-management/cmTradenotice/download?access_token=" + TOKEN + "&client_id=8a5090156c988dd2016c995195880004&id=";

    }
}
