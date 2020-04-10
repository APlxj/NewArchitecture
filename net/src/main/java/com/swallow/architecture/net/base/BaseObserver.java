package com.swallow.architecture.net.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {

    private Context context;

    public BaseObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T o) {
        onSuccess(o);
    }

    @Override
    public void onError(Throwable e) {
        if (!isNetWorking(context)) {
            onError("网络不可用");
        } else if (e instanceof SocketTimeoutException) {
            onError("服务器响应超时");
        } else if (e instanceof ConnectException) {
            onError("服务器请求超时");
        } else if (e instanceof HttpException) {
            onError("服务器异常");
        } else {
            onError("未知异常：" + e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }


    public abstract void onSuccess(T data);

    public abstract void onError(String s);

    /**
     * 网络监测
     *
     * @param context
     * @return
     */
    public static boolean isNetWorking(Context context) {
        boolean flag = checkNet(context);
        if (!flag) {
            Toast.makeText(context, "当前设备网络异常，请检查后再重试！", Toast.LENGTH_SHORT).show();
        }
        return flag;
    }

    private static boolean checkNet(Context context) {

        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
