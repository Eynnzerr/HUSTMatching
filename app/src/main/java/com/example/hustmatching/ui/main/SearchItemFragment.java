package com.example.hustmatching.ui.main;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragSearchItemListener;
import com.example.hustmatching.databinding.FragmentSearchItemBinding;


public class SearchItemFragment extends Fragment {

    private FragmentSearchItemBinding binding;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_item, container, false);
        view = binding.getRoot();

        binding.setOptionsListener(new FragSearchItemListener());

        return view;
    }
}