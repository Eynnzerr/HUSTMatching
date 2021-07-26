package com.example.hustmatching.databinding;


import android.app.Activity;
import android.util.Log;
import android.view.View;

import androidx.navigation.Navigation;

import com.example.hustmatching.R;
import com.example.hustmatching.utils.AlertDialogUtil;

public class FragLoginHandleListener {

    private static final String TAG = "FragLoginHandleListener";
    
    private Activity activity;
    private FragmentLoginBinding binding;

    public FragLoginHandleListener(Activity activity, FragmentLoginBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public void onButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                boolean isAccountEmpty = binding.loginAccountBar.isBoxEmpty();
                boolean isPasswordEmpty = binding.loginPasswordBar.isBoxEmpty();
                Log.d(TAG, "onButtonClicked: empty1:" + isAccountEmpty + " empty2:" + isPasswordEmpty);
                if(isAccountEmpty && isPasswordEmpty) {
                    Log.d(TAG, "onButtonClicked: 请输入用户信息");
                    AlertDialogUtil.createErrorDialog(activity, "请输入用户信息");
                }
                else if(isAccountEmpty) {
                    Log.d(TAG, "onButtonClicked: 请输入用户名");
                    AlertDialogUtil.createErrorDialog(activity,"请输入用户名");
                }
                else if( isPasswordEmpty) {
                    Log.d(TAG, "onButtonClicked: 请输入密码");
                    AlertDialogUtil.createErrorDialog(activity,"请输入密码");
                }
                else {
                    //用户、密码均已输入。验证是否正确，如果正确，跳转至MainActivity，否则弹出alertDialog:用户名或密码错误
                }
                break;
            case R.id.btn_register:
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
                break;
            default:
        }
    }
}
