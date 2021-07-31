package com.example.hustmatching.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hustmatching.R;
import com.example.hustmatching.adapter.PostAdapter;
import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.viewmodel.MyReleaseFragViewModel;

import java.util.ArrayList;
import java.util.List;

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

        //TODO: 先从本地数据库尝试获取发布，若数据为空，再发送网络请求，获取当前用户的所有发布。
        Log.d("fragment","myrelease");
        Log.d("adapter", String.valueOf(adapter==null));

        RecyclerView recyclerView = view.findViewById(R.id.my_release_rv);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
        });


        viewModel.loadPosts();
        viewModel.getPosts().observe(getViewLifecycleOwner(), posts -> {
            if (adapter == null) {
                adapter = new PostAdapter(viewModel.getPosts().getValue());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }
}