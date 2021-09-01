package com.example.hustmatching.adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hustmatching.R;
import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.databinding.NetpostItemBinding;

import java.util.List;

/**
 * 浏览页面的recyclerView的适配器
 */
public class MatchedPostsAdapter extends RecyclerView.Adapter<MatchedPostsAdapter.ViewHolder>{

    //数据源，每一项元素都是一个长度为2的数组，[0]对应用户发布，[1]对应匹配发布
    private List<NetPost[]> matchedPosts;

    public MatchedPostsAdapter(List<NetPost[]> matchedPosts) {
        this.matchedPosts = matchedPosts;
    }

    public void setMatchedPosts(List<NetPost[]> matchedPosts) {
        this.matchedPosts = matchedPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NetpostItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.netpost_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(binding);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将当前选中的netpost详情传给下一碎片
                NetPost[] netPosts = matchedPosts.get(viewHolder.getAdapterPosition());
                Bundle bundle = new Bundle();
                bundle.putSerializable("netPost", netPosts[1]);
                //Log.d("debug", "onClick() netPosts[1]" + netPosts[1].toString());
                Navigation.findNavController(v).navigate(R.id.action_browseFragment_to_detailFragment, bundle);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NetPost[] netPosts = matchedPosts.get(position);
        holder.binding.setMyPost(netPosts[0]);
        holder.binding.setMatchedPost(netPosts[1]);
    }

    @Override
    public int getItemCount() {
        if( matchedPosts == null ) return 0;
        return matchedPosts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        NetpostItemBinding binding;

        public ViewHolder(@NonNull NetpostItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
