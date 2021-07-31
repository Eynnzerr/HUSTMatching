package com.example.hustmatching.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hustmatching.R;
import com.example.hustmatching.adapter.MatchedPostsAdapter;
import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.databinding.FragmentBrowseBinding;
import com.example.hustmatching.network.Api;
import com.example.hustmatching.viewmodel.BrowseFragViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrowseFragment extends Fragment {

    private FragmentBrowseBinding binding;
    private View view;
    private BrowseFragViewModel viewModel;
    private MatchedPostsAdapter adapter;
    private List<NetPost> myPosts;//寄了

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BrowseFragViewModel.class);
        //viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(BrowseFragViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_browse, container, false);
        view = binding.getRoot();

        //设置toolbar
        setHasOptionsMenu(true);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.browseBar);
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        binding.browseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 点击时弹出隐藏的searchBar
            }
        });

        //对recyclerview进行测试
        List<NetPost[]> netPosts = new ArrayList<>();
        adapter = new MatchedPostsAdapter(netPosts);
        binding.postRv.setAdapter(adapter);
        binding.postRv.setLayoutManager(new LinearLayoutManager(getContext()));

        //TODO 进入页面即请求接口，针对当前用户对其所有提交逐个进行匹配，将结果显示在recyclerview上
        //首先从本地数据库取数据，如果不为空，则逐条根据mid发起请求得到匹配的发布，将原发布和匹配的发布整合成两个元素的数组添加至netPosts
        //最终以netPosts为数据源，显示recyclerview。

        //可能要改成livedata observe
        //myPosts = viewModel.getPosts().getValue();//取到本地的我的发布
        //if(myPosts != null) {
            //for (NetPost myPost : myPosts) {
                //viewModel.findMatch(myPost, myPost.getMid());//逐条发起匹配
            //}
        //}

        viewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<List<NetPost>>() {
            @Override
            public void onChanged(List<NetPost> netPosts) {
                myPosts = netPosts;
                if(myPosts != null) {
                    for (NetPost myPost : myPosts) {
                        viewModel.findMatch(myPost, myPost.getMid());//逐条发起匹配
                    }
                }
            }
        });

        viewModel.getMatchedPostsLive().observe(getViewLifecycleOwner(), new Observer<List<NetPost[]>>() {
            @Override
            public void onChanged(List<NetPost[]> netPosts) {
                //有新数据添加了
                adapter.setMatchedPosts(netPosts);
                adapter.notifyDataSetChanged();//添加至rv并刷新
            }
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.browse_toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                break;
        }
        return true;
    }
}