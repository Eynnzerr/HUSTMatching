package com.example.hustmatching.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hustmatching.R;

import java.util.List;

public class EditAdapter extends RecyclerView.Adapter<EditAdapter.EditViewHolder>{
    private List<String> titles;

    public EditAdapter(List<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public EditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_item_layout,parent, false);
        final EditViewHolder holder = new EditViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class EditViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView title;

        public EditViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.item_header);
        }
    }
}
