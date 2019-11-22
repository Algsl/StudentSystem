package com.example.styl.studentssystem.net.netsubscribe;

import com.example.styl.studentssystem.net.bean.TestBean;
import com.example.styl.studentssystem.net.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

public class MainSubscribe {

    /**
     * 获取学生信息
     * @param subscribe
     */
    public static void getStuInfo(DisposableObserver<ResponseBody> subscribe){
        TestBean bean=new TestBean();
        Observable<ResponseBody> observable= RetrofitFactory.getInstance().getHttpApi().getStuInfo(bean);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }
}
