package com.example.hustmatching.databinding;

import android.app.Activity;
import android.view.View;

import com.example.hustmatching.R;

public class FragRegisterHandleListener {

    private Activity activity;
    private FragmentRegisterBinding binding;

    public FragRegisterHandleListener(Activity activity, FragmentRegisterBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                //收集ui信息并发送请求


        }
    }
}
