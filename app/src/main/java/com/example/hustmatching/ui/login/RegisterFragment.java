package com.example.hustmatching.ui.login;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragmentRegisterBinding;
import com.example.hustmatching.viewmodel.RegisterFragViewModel;

import java.util.regex.Pattern;


public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";

    private FragmentRegisterBinding binding;
    private View view;
    private RegisterFragViewModel viewModel;

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

        binding.authensBar.setOnTextClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                //TODO 发送验证码至所填邮箱
                TextView tv = (TextView) v;
                tv.setText("验证码已发送");
                Toast.makeText(getActivity(), "验证码已发送", Toast.LENGTH_SHORT).show();
                tv.setClickable(false);
                tv.setTextColor(R.color.authen_color);
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 确认验证码正确后跳转至账户和密码设置界面
                Log.d(TAG, "onClick: ");
                Navigation.findNavController(v).navigate(R.id.action_registerFragment_to_settingFragment);
            }
        });

        //检测发现填写验证码后，需要改变注册按钮的背景和文字颜色并设置为可点击  使用双向绑定
        LiveData<String> authenLive = viewModel.getAuthen();
        authenLive.observe(getViewLifecycleOwner(), new Observer<String>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "authen onChanged: " + s);
                if(Pattern.matches("^\\d{6}$", s)) {
                    binding.btnRegister.setBackground(getResources().getDrawable(R.drawable.rec_bg_4));
                    binding.btnRegister.setTextColor(Color.WHITE);
                    binding.btnRegister.setClickable(true);
                }
                else {
                    binding.btnRegister.setBackground(getResources().getDrawable(R.drawable.rec_bg_3));
                    binding.btnRegister.setTextColor(getResources().getColor(R.color.state_color_1));
                    binding.btnRegister.setClickable(false);
                }
            }
        });

        return view;
    }
}