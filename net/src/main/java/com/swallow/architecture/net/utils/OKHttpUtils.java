package com.swallow.architecture.net.utils;

import android.util.Log;

import com.swallow.architecture.net.BuildConfig;
import com.swallow.architecture.net.base.BaseUrl;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class OKHttpUtils {

    private static long CONNECT_TIMEOUT = 15;
    private static long READ_TIMEOUT = 15;
    private static long WRITE_TIMEOUT = 15;
    private static OKHttpUtils instance = null;
    private OkHttpClient httpClient = null;

    public static OKHttpUtils getInstance() {
        if (null == instance) {
            synchronized (OKHttpUtils.class) {
                if (null == instance) {
                    instance = new OKHttpUtils();
                }
            }
        }
        return instance;
    }

    private OKHttpUtils() {
        if (null != httpClient) return;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            //打印拦截器日志
            builder.addNetworkInterceptor(new HttpLoggingInterceptor(message ->
                    Log.d("Retrofit", message)).setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        //缓存文件地址+大小
        File cacheFile = new File(/*BaseApplication.getAppContext().getCacheDir(),*/ "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(getHeader())//添加头部信息
        //.addInterceptor(new BaseInterceptor())//响应头过滤
        /*.cache(cache)*/;//缓存

        httpClient = builder.build();
    }

    /**
     * 头部信息
     *
     * @return
     */
    private Interceptor getHeader() {
        return chain -> {
            Request build = chain.request().newBuilder()
                    .addHeader("Accept", "application/json, text/plain, */*")
                    .addHeader("Authorization", BaseUrl.AUTHORIZATION)
                    .build();
            return chain.proceed(build);
        };
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }
}
