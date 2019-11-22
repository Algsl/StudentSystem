package com.example.styl.studentssystem.net.api;

import com.example.styl.studentssystem.net.bean.TestBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpApi {
    @POST("index.php/student/index/getstuinfo.html")
    Observable<ResponseBody> getStuInfo(@Body TestBean bean);
}
