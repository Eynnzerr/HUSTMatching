package com.example.hustmatching.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hustmatching.R;
import com.example.hustmatching.bean.NetPost;
import com.example.hustmatching.databinding.MyPostItemBinding;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private List<NetPost> posts;

    public PostAdapter(List<NetPost> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyPostItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.my_post_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(binding);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 将当前选中的netpost详情传给下一碎片
                Navigation.findNavController(v).navigate(R.id.action_myReleaseFragment_to_detailFragment);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NetPost netPost = posts.get(position);
        holder.binding.setMyPost(netPost);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        MyPostItemBinding binding;

        public ViewHolder(@NonNull MyPostItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
