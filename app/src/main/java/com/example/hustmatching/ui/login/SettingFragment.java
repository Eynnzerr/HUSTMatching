package com.example.hustmatching.ui.login;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragmentSettingBinding;
import com.example.hustmatching.viewmodel.RegisterFragViewModel;
import com.example.hustmatching.viewmodel.SettingFragViewModel;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;
    private View view;
    private SettingFragViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(SettingFragViewModel.class);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.register(getStudentID(),viewModel.getUsername().getValue(),viewModel.getPassword().getValue());
            }
        });

        viewModel.getRegistered().observe(getViewLifecycleOwner(), registered -> {
            viewModel.getRegistered().postValue(false);
            // TODO: 注册后跳转
        });

        return view;
    }

    private String getStudentID() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            String studentID = bundle.getString("studentID");
            return studentID;
        }
        return "";
    }
}