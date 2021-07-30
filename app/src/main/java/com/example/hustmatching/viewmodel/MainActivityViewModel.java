package com.example.hustmatching.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private String[] titles;
    private String[] keys;
    private String classification;
    private List<String> tags = new ArrayList<>();

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
        tags.add(pos, tag);
    }

    public void resetTags() {
        tags.clear();
    }

    public List<String> getTags() {
        return tags;
    }
}
