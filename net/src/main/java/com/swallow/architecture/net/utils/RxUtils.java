package com.swallow.architecture.net.utils;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
public class RxUtils {


    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable
                .subscribeOn(Schedulers.io())//指定网络请求在IO线程
                .observeOn(AndroidSchedulers.mainThread());//显示数据在主线程
    }


    //lambda写法
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper(Context mContext) {
        return observable -> observable
                .subscribeOn(Schedulers.io())                //指定网络请求在IO线程
                .doOnSubscribe(disposable -> {
                    //ProgressManage.getInstance().showHUD(mContext);                 //显示加载进度条
                }).subscribeOn(AndroidSchedulers.mainThread()) //显示进度条在主线程
                .observeOn(AndroidSchedulers.mainThread())     //显示数据在主线程
                .doFinally(() -> {
                    //ProgressManage.getInstance().dismissHUD(); //隐藏进度条
                });
    }


    /**
     * 得到 Observable
     *
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

}
