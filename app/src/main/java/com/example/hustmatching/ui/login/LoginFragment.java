package com.example.hustmatching.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragLoginHandleListener;
import com.example.hustmatching.databinding.FragmentLoginBinding;
import com.example.hustmatching.ui.main.MainActivity;
import com.example.hustmatching.viewmodel.LoginFragViewModel;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private View view;
    private LoginFragViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(LoginFragViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        view = binding.getRoot();

        binding.setLoginFragListener(new FragLoginHandleListener(getActivity(), binding,viewModel));
        binding.setViewModel(viewModel);

        viewModel.getChecked().observe(getViewLifecycleOwner(),checked->{
            viewModel.getChecked().postValue(false);
            Intent intent = new Intent(getActivity(), MainActivity.class);
            getActivity().startActivity(intent);
        });

        return view;
    }
}