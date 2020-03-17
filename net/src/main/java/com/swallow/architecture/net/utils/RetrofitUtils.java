package com.swallow.architecture.net.utils;

import android.util.SparseArray;

import com.swallow.architecture.net.base.BaseUrl;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class RetrofitUtils {

    //sparsearray 比 hashmap 更优化内存
    private static SparseArray<Retrofit> sRetrofitManager = new SparseArray<>(BaseUrl.TYPE_COUNT);

    //获取Retrofit：根据类型
    public static Retrofit getRetrofit(int type) {
        Retrofit retrofit = sRetrofitManager.get(type);
        if (retrofit == null) {
            retrofit = creatRetrofit(type);
            sRetrofitManager.put(type, retrofit);
        }

        return retrofit;
    }

    private static Retrofit creatRetrofit(int type) {
        return new Retrofit.Builder()
                .baseUrl(BaseUrl.getHost(type))
                .client(OKHttpUtils.getInstance().getHttpClient())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
