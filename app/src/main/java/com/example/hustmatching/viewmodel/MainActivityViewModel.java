package com.example.hustmatching.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private String[] titles;
    private String[] keys;
    private String classification;
    private List<String> tags;

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void addTag(int pos, String tag) {
        tags.set(pos, tag);
    }

    public void resetTags() {
        tags.clear();
    }

    public List<String> getTags() {
        return tags;
    }

    public MainActivityViewModel() {
        tags = new ArrayList<>();
        tags.add("1");
        tags.add("2");
        tags.add("3");
    }
}
