package com.example.hustmatching.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.hustmatching.R;
import com.example.hustmatching.adapter.EditAdapter;
import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.databinding.FragmentItemEdit1Binding;
import com.example.hustmatching.network.Api;
import com.example.hustmatching.network.UserService;
import com.example.hustmatching.response.Response;
import com.example.hustmatching.room.GsonInstance;
import com.example.hustmatching.room.PostDatabase;
import com.example.hustmatching.utils.AlertDialogUtil;
import com.example.hustmatching.utils.NetPostUtil;
import com.example.hustmatching.utils.Test;
import com.example.hustmatching.viewmodel.ItemEditFrag1ViewModel;
import com.example.hustmatching.viewmodel.MainActivityViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemEditFragment1 extends Fragment {

    private static final String TAG = "ItemEditFragment1";

    private FragmentItemEdit1Binding binding;
    private View view;
    private ItemEditFrag1ViewModel viewModel;

    private EditAdapter keyAdapter;
    private EditAdapter infoAdapter;

    private int spinnerPosition;//应后端要求，将spinner值转为int

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

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        binding.timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 打包当前页面数据并发起网络请求，提交给后端服务器。发布成功跳转至“我的发布页面”
                Log.d(TAG, "RecyclerView长度: " + keyAdapter.getItemCount());
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
                netPost.setTime(viewModel.getDate() + "-" + binding.timeSpinner.getSelectedItem().toString());//从viewmodel和ui组合获取

                Log.d(TAG, "Tags: " + activityViewModel.getTags());

                //根据不同情况发送相应请求
                Map<String,String> fieldMap = getFieldMap(netPost);
                if(netPost.getClassification() == "#寻失物")
                    viewModel.sendPosts(fieldMap);//发送请求
                else
                    viewModel.sendPostsPerson(fieldMap);

                viewModel.getMid().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        if(netPost != null && integer != 0) {
                            netPost.setMid(integer);
                            viewModel.addPostToDatabase(netPost, getContext());//得到取回的mid再存入数据库
                        }
                    }
                });

                viewModel.getSended().observe(getViewLifecycleOwner(),sended -> {
                    if (sended){
                        viewModel.getSended().postValue(false);
                        activityViewModel.resetTags();//发起一次请求后要清空tags
                        getActivity().onBackPressed();
                    }
                });


            }
        });

        return view;
    }

    private void initKeyRecyclerView(RecyclerView recyclerView) {
        MainActivityViewModel activityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        String[] titles = activityViewModel.getTitles();
        if(viewModel.getKeywords().isEmpty()) {
            for(String title : titles)
                viewModel.addKeyWord(title);
        }
        keyAdapter = new EditAdapter(viewModel.getKeywords(), this.getActivity());
        keyAdapter.setKeys(activityViewModel.getKeys());
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

    //将NetPost对象实例转换成POST请求需要的fieldMap
    private Map<String, String> getFieldMap(NetPost netPost) {
        Map<String, String> map = new HashMap<>();
        //map.put("studentID", "U201917277");
        map.put("title",netPost.getTitle());
        map.put("classification", netPost.getClassification());
        //map.put("tags", GsonInstance.getInstance().getGson().toJson(netPost.getTags()));
        map.put("tag1",netPost.getTags().get(0));
        map.put("tag2",netPost.getTags().get(1));
        map.put("tag3",netPost.getTags().get(2));
        map.put("detail", netPost.getDetail());
        int index = netPost.getTime().lastIndexOf("-");
        map.put("time",formatDate(netPost.getTime().substring(0, index+1)) + spinnerPosition);
        map.put("location", netPost.getLocation());
        map.put("qq", netPost.getQq());
        map.put("phone", netPost.getPhone());
        map.put("date", netPost.getDate());
        Log.d(TAG, "getFieldMap: " + map);
        return map;
    }

    private String formatDate(String time) {
        //2020-1-13
        String[] times = time.split("-");
        int month = Integer.parseInt(times[1]);
        if(month < 10) {
            String newMonth = "0" + month;
            return times[0] + "-" + newMonth + "-" + times[2];
        }
        Log.d(TAG, "formatDate: " + time);
        return time;
    }
}