package com.example.hustmatching.databinding;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.hustmatching.R;
import com.example.hustmatching.ui.main.MainActivity;
import com.example.hustmatching.utils.AlertDialogUtil;

public class FragEmailLoginListener {

    private static final String TAG = "FragEmailLoginListener";

    private Activity activity;
    private FragmentLoginByEmailBinding binding;

    public FragEmailLoginListener(Activity activity, FragmentLoginByEmailBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public void onButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                boolean isEmailEmpty = binding.emailBar.isBoxEmpty();
                boolean isAuthenEmpty = binding.authensBar.isBoxEmpty();
                Log.d(TAG, "onButtonClicked: empty1:" + isEmailEmpty + " empty2:" + isAuthenEmpty);
                if(isEmailEmpty && isAuthenEmpty) {
                    AlertDialogUtil.createErrorDialog(activity, "请输入用户信息");
                }
                else if(isEmailEmpty) {
                    AlertDialogUtil.createErrorDialog(activity,"请输入邮箱地址");
                }
                else if(isAuthenEmpty) {
                    AlertDialogUtil.createErrorDialog(activity,"请输入验证码");
                }
                else {
                    //TODO 邮箱、验证码均已输入。验证验证码是否正确，如果正确，跳转至MainActivity，否则弹出alertDialog:验证码错误
                    Intent intent = new Intent(activity, MainActivity.class);
                    Toast.makeText(activity,"登录成功",Toast.LENGTH_SHORT).show();
                    activity.startActivity(intent);
                }
                break;
            case R.id.to_register:
                Navigation.findNavController(view).navigate(R.id.action_loginByEmailFragment_to_registerFragment);
                break;
            case R.id.to_authen_login:
                Navigation.findNavController(view).navigate(R.id.action_loginByEmailFragment_to_loginFragment);
            default:
        }
    }
}
