package com.example.hustmatching.databinding;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.hustmatching.R;
import com.example.hustmatching.ui.main.MainActivity;
import com.example.hustmatching.utils.AlertDialogUtil;
import com.example.hustmatching.viewmodel.EmailRegisterFragViewModel;
import com.example.hustmatching.viewmodel.LoginFragViewModel;

public class FragEmailLoginListener {

    private static final String TAG = "FragEmailLoginListener";

    private Activity activity;
    private FragmentLoginByEmailBinding binding;
    private EmailRegisterFragViewModel viewModel;


    public FragEmailLoginListener(Activity activity, FragmentLoginByEmailBinding binding,EmailRegisterFragViewModel viewModel) {
        this.activity = activity;
        this.binding = binding;
        this.viewModel =viewModel;
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
                    viewModel.login(viewModel.getStudentID().getValue(),viewModel.getAuth().getValue());
                }
                break;
            case R.id.to_register:
                Navigation.findNavController(view).navigate(R.id.action_loginByEmailFragment_to_registerFragment);
                break;
            case R.id.to_password_login:
                Navigation.findNavController(view).navigate(R.id.action_loginByEmailFragment_to_loginFragment);
            default:
        }
    }
}
