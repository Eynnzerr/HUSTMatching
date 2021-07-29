package com.example.hustmatching.ui.main;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hustmatching.R;
import com.example.hustmatching.adapter.EditAdapter;
import com.example.hustmatching.databinding.FragmentItemEdit1Binding;
import com.example.hustmatching.viewmodel.ItemEditFrag1ViewModel;

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

        return view;
    }

    private void initKeyRecyclerView(RecyclerView recyclerView) {
        keyAdapter = new EditAdapter(viewModel.getKeywords());
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
        infoAdapter = new EditAdapter(viewModel.getInfo());
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