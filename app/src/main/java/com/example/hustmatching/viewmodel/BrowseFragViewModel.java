package com.example.hustmatching.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.utils.NetPostUtil;

import java.util.ArrayList;
import java.util.List;

public class BrowseFragViewModel extends ViewModel {

    private List<NetPost[]> matchedPosts;

    public List<NetPost[]> getMatchedPosts() {
        //模拟假数据用于测试，后期可以删除
        matchedPosts = new ArrayList<>();
        for(int i = 0; i < 10; i ++) {
            NetPost[] netPosts = new NetPost[2];
            NetPost myPost = new NetPost();
            myPost.setClassification(NetPostUtil.SEARCH_ITEM);
            myPost.setTitle("我的发布" + i);
            NetPost yourPost = new NetPost();
            yourPost.setClassification(NetPostUtil.SEARCH_ITEM);
            yourPost.setTitle("匹配的发布" + i);
            List<String> tags = new ArrayList<>();
            for(int j = 0; j < 3; j ++) {
                tags.add("关键词" + j);
            }
            yourPost.setTags(tags);
            netPosts[0] = myPost;
            netPosts[1] = yourPost;
            yourPost.setTime("2021-7-30 上午");
            yourPost.setLocation("韵酒");
            yourPost.setDate("2021-7-31");
            yourPost.setQq("123456789");
            yourPost.setPhone("18055557780");
            matchedPosts.add(netPosts);
        }
        return matchedPosts;
    }
}
