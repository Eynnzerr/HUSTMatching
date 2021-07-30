package com.example.hustmatching.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
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
import com.example.hustmatching.viewmodel.BrowseFragViewModel;

import java.util.List;

public class BrowseFragment extends Fragment {

    private FragmentBrowseBinding binding;
    private View view;
    private BrowseFragViewModel viewModel;
    private MatchedPostsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BrowseFragViewModel.class);

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

        //对recyclerview进行测试
        List<NetPost[]> netPosts = viewModel.getMatchedPosts();
        adapter = new MatchedPostsAdapter(netPosts);
        binding.postRv.setAdapter(adapter);
        binding.postRv.setLayoutManager(new LinearLayoutManager(getContext()));

        //TODO 进入页面即请求接口，针对当前用户对其所有提交逐个进行匹配，将结果显示在recyclerview上

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