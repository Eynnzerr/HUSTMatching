package com.example.hustmatching.ui.main;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragSearchItemListener;
import com.example.hustmatching.databinding.FragmentSearchPeopleBinding;
import com.example.hustmatching.viewmodel.MainActivityViewModel;

public class SearchPeopleFragment extends Fragment {

    FragmentSearchPeopleBinding binding;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search_people, container, false);
        view = binding.getRoot();

        MainActivityViewModel activityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        binding.setOptionsListener(new FragSearchItemListener(activityViewModel));
        return view;
    }
}