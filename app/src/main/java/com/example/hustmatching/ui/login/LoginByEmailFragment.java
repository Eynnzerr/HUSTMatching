package com.example.hustmatching.ui.login;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragEmailLoginListener;
import com.example.hustmatching.databinding.FragLoginHandleListener;
import com.example.hustmatching.databinding.FragmentLoginByEmailBinding;
import com.example.hustmatching.viewmodel.EmailRegisterFragViewModel;
import com.example.hustmatching.viewmodel.LoginFragViewModel;


public class LoginByEmailFragment extends Fragment {

    private FragmentLoginByEmailBinding binding;
    private View view;
    private EmailRegisterFragViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(EmailRegisterFragViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_by_email, container, false);
        view = binding.getRoot();

        binding.setLoginFragListener(new FragEmailLoginListener(getActivity(), binding));
        binding.setViewModel(viewModel);

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

        return view;
    }
}