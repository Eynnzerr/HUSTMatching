package com.example.hustmatching.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ItemEditFrag1ViewModel extends ViewModel {
    private ArrayList<String> keywords;
    private ArrayList<String> info;

    public ItemEditFrag1ViewModel() {
        keywords = new ArrayList<>();
        keywords.add("品类");
        keywords.add("品牌");
        keywords.add("外形特点");

        info = new ArrayList<>();
        info.add("地点");
    }

    public void addKeyWord(String keyword) {
        keywords.add(keyword);
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void addInfo(String information) {
        info.add(information);
    }

    public ArrayList<String> getInfo() {
        return info;
    }
}
