package com.swallow.architecture.net.utils;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类描述：云端响应头拦截器，用来配置缓存策略
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class BaseInterceptor implements Interceptor {

    //设缓存有效期为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    //max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    /*************************缓存设置*********************/
    /*1. noCache 不使用缓存，全部走网络
      2. noStore 不使用缓存，也不存储缓存
      3. onlyIfCached 只使用缓存
      4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合
      5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言
      6. minFresh 设置有效时间，依旧如上
      7. FORCE_NETWORK 只走网络
      8. FORCE_CACHE 只走缓存*/
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request();
        String cacheControl = request.cacheControl().toString();
        return chain.proceed(request);
    }

}
