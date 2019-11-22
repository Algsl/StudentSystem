package com.example.styl.studentssystem.net.netutils;

import android.util.Log;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class OnSuccessAndFaultSub extends DisposableObserver<ResponseBody> {

    private OnSuccessAndFaultListener listener;

    public OnSuccessAndFaultSub(OnSuccessAndFaultListener listener){
        this.listener=listener;
    }


    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            final String result = responseBody.string();
            Log.e("body", result);
            JSONObject jsonObject = new JSONObject(result);
            int resultCode = jsonObject.getInt("code");
            if (resultCode == 1) {
                listener.onSuccess(result);
            } else {
                String errorMsg = jsonObject.getString("msg");
                listener.onFault(errorMsg);
                Log.e("OnSuccessAndFaultSub", "errorMsg: " + errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        try {

            if (e instanceof SocketTimeoutException) {//请求超时
            } else if (e instanceof ConnectException) {//网络连接超时
                //                ToastManager.showShortToast("网络连接超时");
                listener.onFault("网络连接超时");
            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                //                ToastManager.showShortToast("安全证书异常");
                listener.onFault("安全证书异常");
            } else if (e instanceof HttpException) {//请求的地址不存在
                int code = ((HttpException) e).code();
                if (code == 504) {
                    //                    ToastManager.showShortToast("网络异常，请检查您的网络状态");
                    listener.onFault("网络异常，请检查您的网络状态");
                } else if (code == 404) {
                    //                    ToastManager.showShortToast("请求的地址不存在");
                    listener.onFault("请求的地址不存在");
                } else {
                    //                    ToastManager.showShortToast("请求失败");
                    listener.onFault("请求失败");
                }
            } else if (e instanceof UnknownHostException) {//域名解析失败
                //                ToastManager.showShortToast("域名解析失败");
                listener.onFault("域名解析失败");
            } else {
                //                ToastManager.showShortToast("error:" + e.getMessage());
                listener.onFault("error:" + e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            Log.e("OnSuccessAndFaultSub", "error:" + e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }
}
