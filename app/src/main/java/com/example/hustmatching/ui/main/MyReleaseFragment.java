package com.example.hustmatching.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hustmatching.R;
import com.example.hustmatching.adapter.PostAdapter;
import com.example.hustmatching.viewmodel.MyReleaseFragViewModel;

public class MyReleaseFragment extends Fragment {

    private View view;
    private MyReleaseFragViewModel viewModel;
    private PostAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MyReleaseFragViewModel.class);
        view = inflater.inflate(R.layout.fragment_my_release, container, false);

        //TODO: 发送网络请求，获取当前用户的所有发布。
        RecyclerView recyclerView = view.findViewById(R.id.my_release_rv);
        adapter = new PostAdapter(viewModel.getPosts());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}