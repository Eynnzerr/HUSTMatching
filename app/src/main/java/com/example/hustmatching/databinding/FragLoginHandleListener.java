package com.example.hustmatching.databinding;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.hustmatching.R;
import com.example.hustmatching.ui.main.MainActivity;
import com.example.hustmatching.utils.AlertDialogUtil;
import com.example.hustmatching.viewmodel.LoginFragViewModel;
import com.example.hustmatching.viewmodel.SettingFragViewModel;

public class FragLoginHandleListener {

    private static final String TAG = "FragLoginHandleListener";
    
    private Activity activity;
    private FragmentLoginBinding binding;
    private LoginFragViewModel viewModel;

    public FragLoginHandleListener(Activity activity, FragmentLoginBinding binding,LoginFragViewModel viewModel) {
        this.activity = activity;
        this.binding = binding;
        this.viewModel= viewModel;
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
                    viewModel.login(viewModel.getStudentID().getValue(),viewModel.getPassword().getValue());
                }
                break;
            case R.id.to_register:
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
                break;
            case R.id.to_authen_login:
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_loginByEmailFragment);
            default:
        }
    }
}
