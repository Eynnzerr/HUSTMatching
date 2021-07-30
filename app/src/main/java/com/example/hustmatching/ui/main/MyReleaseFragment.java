package com.example.hustmatching.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hustmatching.R;
import com.example.hustmatching.adapter.PostAdapter;
import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.viewmodel.MyReleaseFragViewModel;
import com.example.hustmatching.viewmodel.TestViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyReleaseFragment extends Fragment {

    private static final String TAG = "MyReleaseFragment";

    private View view;
    private TestViewModel viewModel;
    private PostAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(TestViewModel.class);
        view = inflater.inflate(R.layout.fragment_my_release, container, false);

        ImageView back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        //TODO: 先从本地数据库尝试获取发布，若数据为空，再发送网络请求，获取当前用户的所有发布。
        RecyclerView recyclerView = view.findViewById(R.id.my_release_rv);
        adapter = new PostAdapter(new ArrayList<>());
        viewModel.getNetPostsLive().observe(getViewLifecycleOwner(), new Observer<List<NetPost>>() {
            @Override
            public void onChanged(List<NetPost> netPosts) {
                adapter.setPosts(netPosts);
                adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}