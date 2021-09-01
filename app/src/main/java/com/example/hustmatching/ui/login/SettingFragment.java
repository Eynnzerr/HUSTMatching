package com.example.hustmatching.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragmentSettingBinding;
import com.example.hustmatching.ui.main.MainActivity;
import com.example.hustmatching.viewmodel.SettingFragViewModel;

public class SettingFragment extends Fragment {

    private static final String TAG = "SettingFragment";

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
        viewModel = new ViewModelProvider(this).get(SettingFragViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        binding.setViewModel(viewModel);
        view = binding.getRoot();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //进行注册
                viewModel.register(getStudentID(),viewModel.getUsername().getValue(),viewModel.getPassword().getValue());
                /*User user = new User(viewModel.getUsername().getValue(),viewModel.getPassword().getValue(),getStudentID());
                String json = GsonInstance.getInstance().getGson().toJson(user);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
                RetrofitUtil.getUserService().register(requestBody).enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        Log.d(TAG, "onResponse: " + response.body());
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.d(TAG, "onFailure: 请求失败: " + t.getMessage());
                    }
                });*/

                SharedPreferences.Editor editor = getActivity().getSharedPreferences("ui.main.MainActivity", Context.MODE_PRIVATE).edit();
                editor.putString("name",viewModel.getUsername().getValue());
                editor.apply();
            }
        });

        viewModel.getRegistered().observe(getViewLifecycleOwner(), registered -> {
            if (registered){
                viewModel.getRegistered().postValue(false);
                viewModel.login(getStudentID(),viewModel.getPassword().getValue());
            }
        });

        viewModel.getLogined().observe(getViewLifecycleOwner(), logined-> {
            if (logined){
                viewModel.getLogined().postValue(false);
                Intent intent = new Intent(getActivity(), MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
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