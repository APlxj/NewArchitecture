package com.swallow.architecture.net;

import com.swallow.architecture.net.api.ApiService;
import com.swallow.architecture.net.utils.RetrofitUtils;
import com.swallow.architecture.net.utils.RxUtils;

import io.reactivex.Observable;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class HttpClient {

    /**
     * 获取api的回调
     *
     * @param <T>
     */
    public interface MethodSelectC<T> {
        Observable<T> selectM(ApiService service);
    }

    public static <T> Observable<T> getHttpClientC(MethodSelectC<T> select) {
        return getHttpClientC(select, 0);
    }

    public static <T> Observable<T> getHttpClientC(MethodSelectC<T> select, int type) {
        return select.selectM(RetrofitUtils.getRetrofit(type).create(ApiService.class))
                .compose(RxUtils.rxSchedulerHelper());
    }
}
