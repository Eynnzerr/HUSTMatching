package com.example.hustmatching.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragmentRegisterBinding;
import com.example.hustmatching.viewmodel.RegisterFragViewModel;

import org.w3c.dom.Text;

import java.util.regex.Pattern;


public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";

    private FragmentRegisterBinding binding;
    private View view;
    private RegisterFragViewModel viewModel;
    private CountDownTimer mCountDownTimer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(RegisterFragViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        binding.setViewModel(viewModel);
        view = binding.getRoot();


        mCountDownTimer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                TextView tv =binding.authensBar.getTextView();
                tv.setClickable(false);
                tv.setText(getString(R.string.wait_verify,(millisUntilFinished-100)/1000+1));
                tv.setTextColor(getContext().getColor(R.color.authen_unclickable_color));

            }


            @Override
            public void onFinish() {
                TextView tv =binding.authensBar.getTextView();
                tv.setClickable(true);
                tv.setText(getString(R.string.retry_verify));
                tv.setTextColor(getContext().getColor(R.color.authen_color));

            }

        };

        binding.authensBar.setOnTextClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                //TODO 发送验证码至所填邮箱
                mCountDownTimer.start();

            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 确认验证码正确后跳转至账户和密码设置界面
                Log.d(TAG, "onClick: ");
                Bundle bundle = new Bundle();
                bundle.putString("studentID",viewModel.getEmail().getValue());
                Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_settingFragment, bundle);
            }
        });

        //检测发现填写验证码后，需要改变注册按钮的背景和文字颜色并设置为可点击  使用双向绑定
        LiveData<String> authenLive = viewModel.getAuthen();
        authenLive.observe(getViewLifecycleOwner(), new Observer<String>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "authen onChanged: " + s);
                if (Pattern.matches("^\\d{6}$", s)) {

                    binding.btnRegister.setBackground(getResources().getDrawable(R.drawable.rec_bg_4));
                    binding.btnRegister.setTextColor(Color.WHITE);
                    binding.btnRegister.setClickable(true);
                } else {
                    binding.btnRegister.setBackground(getResources().getDrawable(R.drawable.rec_bg_3));
                    binding.btnRegister.setTextColor(getResources().getColor(R.color.state_color_1));
                    binding.btnRegister.setClickable(false);
                }
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        mCountDownTimer.cancel();
    }
}