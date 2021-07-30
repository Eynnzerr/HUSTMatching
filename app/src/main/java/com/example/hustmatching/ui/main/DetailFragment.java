package com.example.hustmatching.ui.main;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hustmatching.R;
import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.databinding.FragmentDetailBinding;
import com.example.hustmatching.viewmodel.MainActivityViewModel;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        view = binding.getRoot();

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        NetPost netPost = (NetPost) getArguments().getSerializable("netPost");
        binding.setNetPost(netPost);

        return view;

    }
}