package com.gaojunhui.day7_7tuisong;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.UmengRegistrar;
import com.umeng.message.entity.UMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushAgent.getInstance(MainActivity.this).onAppStart();
        PushAgent mPushAgent = PushAgent.getInstance(MainActivity.this);
//        mPushAgent.enable();
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler(){
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom + "dianji", Toast.LENGTH_LONG).show();
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        //开启推送并设置注册的回调处理
        mPushAgent.enable(new IUmengRegisterCallback() {
            @Override
            public void onRegistered(final String registrationId) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        //onRegistered方法的参数registrationId即是device_token
                        Log.d("device_token", "---------"+registrationId);
                    }
                });
            }
        });
        String device_token = UmengRegistrar.getRegistrationId(MainActivity.this);
        Log.d("-------", "token-------" + device_token);
    }
}
