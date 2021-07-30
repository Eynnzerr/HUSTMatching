package com.example.hustmatching.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragEmailLoginListener;
import com.example.hustmatching.databinding.FragLoginHandleListener;
import com.example.hustmatching.databinding.FragmentLoginByEmailBinding;
import com.example.hustmatching.ui.main.MainActivity;
import com.example.hustmatching.viewmodel.EmailRegisterFragViewModel;
import com.example.hustmatching.viewmodel.LoginFragViewModel;


public class LoginByEmailFragment extends Fragment {

    private FragmentLoginByEmailBinding binding;
    private View view;
    private EmailRegisterFragViewModel viewModel;
    private CountDownTimer mCountDownTimer;

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

        binding.setLoginFragListener(new FragEmailLoginListener(getActivity(), binding,viewModel));
        binding.setViewModel(viewModel);

        mCountDownTimer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                TextView tv = binding.authensBar.getTextView();
                tv.setClickable(false);
                tv.setText(getString(R.string.wait_verify, (millisUntilFinished - 100) / 1000 + 1));
                tv.setTextColor(getContext().getColor(R.color.authen_unclickable_color));

            }


            @Override
            public void onFinish() {
                TextView tv = binding.authensBar.getTextView();
                tv.setClickable(true);
                tv.setText(getString(R.string.retry_verify));
                tv.setTextColor(getContext().getColor(R.color.authen_color));

            }

        };

        binding.authensBar.setOnTextClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                viewModel.getAuth(viewModel.getStudentID().getValue());
                mCountDownTimer.start();
            }
        });

        viewModel.getChecked().observe(getViewLifecycleOwner(), checked -> {
            if(checked) {
                viewModel.getChecked().postValue(false);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}