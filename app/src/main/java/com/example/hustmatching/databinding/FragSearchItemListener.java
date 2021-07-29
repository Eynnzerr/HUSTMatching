package com.example.hustmatching.databinding;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.hustmatching.R;

public class FragSearchItemListener {

    //导航至同一编辑页面，只是传递的关键词不一样
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.items1:
                Navigation.findNavController(view).navigate(R.id.action_searchItemFragment_to_itemEditFragment1);
                break;
            case R.id.items2:
                break;
            case R.id.items3:
                break;
            case R.id.items4:
                break;
        }

    }
}
