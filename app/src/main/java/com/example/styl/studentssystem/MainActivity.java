package com.example.styl.studentssystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.styl.studentssystem.net.netsubscribe.MainSubscribe;
import com.example.styl.studentssystem.net.netutils.OnSuccessAndFaultListener;
import com.example.styl.studentssystem.net.netutils.OnSuccessAndFaultSub;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "测试";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStuInfo();
    }

    private void getStuInfo() {
        MainSubscribe.getStuInfo(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result);
            }

            @Override
            public void onFault(String msg) {

            }
        }));
    }
}
