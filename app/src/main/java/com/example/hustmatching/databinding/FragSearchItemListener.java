package com.example.hustmatching.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.navigation.Navigation;

import com.example.hustmatching.R;
import com.example.hustmatching.utils.NetPostUtil;
import com.example.hustmatching.viewmodel.MainActivityViewModel;

public class FragSearchItemListener {

    private MainActivityViewModel activityViewModel;

    public FragSearchItemListener(MainActivityViewModel activityViewModel) {
        this.activityViewModel = activityViewModel;
    }

    //导航至同一编辑页面，只是传递的关键词不一样，以及一些信息不一样。这里传递数据由共享activity的viewmodel来实现。
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.items1:
                //电子页面
                String[] titles1 = new String[]{"物品名称","品牌","外形特点"};
                String[] keys1 = new String[]{"手机","有线耳机","蓝牙耳机","U盘"};
                activityViewModel.setTitles(titles1);
                activityViewModel.setKeys(keys1);
                activityViewModel.setClassification(NetPostUtil.SEARCH_ITEM);
                Navigation.findNavController(view).navigate(R.id.action_searchItemFragment_to_itemEditFragment1);
                break;
            case R.id.items2:
                //生活页面
                String[] titles2 = new String[]{"物品名称","特征描述","特别信息"};
                String[] keys2 = new String[]{};
                activityViewModel.setTitles(titles2);
                activityViewModel.setKeys(keys2);
                activityViewModel.setClassification(NetPostUtil.SEARCH_ITEM);
                Navigation.findNavController(view).navigate(R.id.action_searchItemFragment_to_itemEditFragment1);
                break;
            case R.id.items3:
                //学习页面
                String[] titles3 = new String[]{"物品名称","品牌","特点信息"};
                String[] keys3 = new String[]{};
                activityViewModel.setTitles(titles3);
                activityViewModel.setKeys(keys3);
                activityViewModel.setClassification(NetPostUtil.SEARCH_ITEM);
                Navigation.findNavController(view).navigate(R.id.action_searchItemFragment_to_itemEditFragment1);
                break;
            case R.id.items4:
                //其它页面：除关键词信息，其它都与电子页面一致
                String[] titles4 = new String[]{"物品名称","品牌","外形特点"};
                String[] keys4 = new String[]{};
                activityViewModel.setTitles(titles4);
                activityViewModel.setKeys(keys4);
                activityViewModel.setClassification(NetPostUtil.SEARCH_ITEM);
                Navigation.findNavController(view).navigate(R.id.action_searchItemFragment_to_itemEditFragment1);
                break;
            case R.id.people1:
                //寻失主页面
                String[] titles7 = new String[]{"物品名称","品牌","外形特点"};
                String[] keys7 = new String[]{};
                activityViewModel.setTitles(titles7);
                activityViewModel.setKeys(keys7);
                activityViewModel.setClassification(NetPostUtil.SEARCH_LOST_PERSON);
                Navigation.findNavController(view).navigate(R.id.action_searchPeopleFragment_to_itemEditFragment1);
                break;
            case R.id.people2:
                //寻友页面
                String[] titles5 = new String[]{"寻友目的","性别要求","其它要求"};
                String[] keys5 = new String[]{};
                activityViewModel.setTitles(titles5);
                activityViewModel.setKeys(keys5);
                activityViewModel.setClassification(NetPostUtil.SEARCH_OTHERS);
                Navigation.findNavController(view).navigate(R.id.action_searchPeopleFragment_to_itemEditFragment1);
                break;
            case R.id.people3:
                //其它寻人页面
                String[] titles6 = new String[]{"寻人目的","性别要求","其它要求"};
                String[] keys6 = new String[]{};
                activityViewModel.setTitles(titles6);
                activityViewModel.setKeys(keys6);
                activityViewModel.setClassification(NetPostUtil.SEARCH_OTHERS);
                Navigation.findNavController(view).navigate(R.id.action_searchPeopleFragment_to_itemEditFragment1);
                break;
        }

    }
}
