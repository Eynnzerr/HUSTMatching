package com.example.hustmatching.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.example.hustmatching.R;
import com.example.hustmatching.ui.login.LoginActivity;

import java.lang.ref.WeakReference;

public class WelcomeActivity extends AppCompatActivity {

    MyHandler handler = new MyHandler(Looper.myLooper(),this);//获取Looper并传递

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        handler.sendEmptyMessageDelayed(0,3000);
    }

    //为了用Intent，不给用static class???
    class MyHandler extends Handler {
        WeakReference<WelcomeActivity> mactivity;

        public MyHandler(@NonNull Looper looper, WelcomeActivity activity){
            super(looper);//调用父类的显式指明的构造函数
            mactivity = new WeakReference<WelcomeActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            WelcomeActivity nactivity = mactivity.get();
            if(nactivity == null)
                return ;

            switch (msg.what) {
                case 0:
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    }
}