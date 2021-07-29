package com.example.hustmatching.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.hustmatching.R;
import com.example.hustmatching.adapter.EditAdapter;
import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.databinding.FragmentItemEdit1Binding;
import com.example.hustmatching.utils.AlertDialogUtil;
import com.example.hustmatching.viewmodel.ItemEditFrag1ViewModel;
import com.example.hustmatching.viewmodel.MainActivityViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ItemEditFragment1 extends Fragment {

    private FragmentItemEdit1Binding binding;
    private View view;
    private ItemEditFrag1ViewModel viewModel;

    private EditAdapter keyAdapter;
    private EditAdapter infoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(ItemEditFrag1ViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_edit1, container, false);
        view = binding.getRoot();

        initKeyRecyclerView(binding.keyRv);
        initInfoRecyclerView(binding.infoRv);

        //由于涉及操作控件实例，不再适合在工具类中调用
        binding.infoDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                View alertView = getActivity().getLayoutInflater().inflate(R.layout.dialog_datepicker, null, false);
                DatePicker datePicker = alertView.findViewById(R.id.date_picker);
                datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        viewModel.setDate(year + "-" + monthOfYear + "-" + dayOfMonth);
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(alertView);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        binding.infoDate.setText(viewModel.getDate());
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        binding.btnRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 打包当前页面数据并发起网络请求，提交给后端服务器。发布成功跳转至“我的发布页面”
                NetPost netPost = new NetPost();
                netPost.setTitle(binding.postTitle.getText().toString());//从ui获取
                MainActivityViewModel activityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
                netPost.setClassification(activityViewModel.getClassification());//从viewmodel获取
                netPost.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));//获取当天时间
                netPost.setTags(activityViewModel.getTags());//从viewModel获取
                netPost.setDetail(binding.detail.getText().toString());//从ui获取
                netPost.setQq(binding.contactQq.getText().toString());//从ui获取
                netPost.setPhone(binding.contactPhone.getText().toString());//从ui获取
                netPost.setLocation(binding.infoLocation.getText().toString());//从ui获取
                netPost.setTime(viewModel.getDate() + " " + binding.timeSpinner.getSelectedItem().toString());//从viewmodel和ui组合获取

                activityViewModel.resetTags();//发起一次请求后要清空tags
                Navigation.findNavController(v).navigate(R.id.action_itemEditFragment1_to_myReleaseFragment);
            }
        });

        return view;
    }

    private void initKeyRecyclerView(RecyclerView recyclerView) {
        MainActivityViewModel activityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        String[] titles = activityViewModel.getTitles();
        for(String title : titles)
            viewModel.addKeyWord(title);
        keyAdapter = new EditAdapter(viewModel.getKeywords(), this.getActivity());
        recyclerView.setAdapter(keyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.addKeyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyAdapter != null) {
                    viewModel.addKeyWord("新增");
                    keyAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initInfoRecyclerView(RecyclerView recyclerView) {
        infoAdapter = new EditAdapter(viewModel.getInfo(), this.getActivity());
        recyclerView.setAdapter(infoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.addInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoAdapter != null) {
                    viewModel.addInfo("新增");
                    infoAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}