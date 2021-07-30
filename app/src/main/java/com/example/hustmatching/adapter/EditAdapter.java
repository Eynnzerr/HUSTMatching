package com.example.hustmatching.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hustmatching.R;

import java.util.ArrayList;
import java.util.List;

public class EditAdapter extends RecyclerView.Adapter<EditAdapter.EditViewHolder>{

    private Context context;
    private List<String> titles;

    public interface SaveEditListener{
        void SaveEdit(Integer position, String string);
    }


    public EditAdapter(List<String> titles, Context context) {

        this.titles = titles;
        this.context = context;
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
        holder.edit.addTextChangedListener(new MyTextWatcher(holder));
        holder.edit.setTag(position);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class EditViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView title;
        EditText edit;

        public EditViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.item_header);
            edit = itemView.findViewById(R.id.item_description);
        }
    }

    class MyTextWatcher implements TextWatcher {

        private EditViewHolder viewHolder;

        public MyTextWatcher(EditViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            SaveEditListener listener = (SaveEditListener) context;
            if(s != null) {
                listener.SaveEdit(Integer.parseInt(viewHolder.edit.getTag().toString()),s.toString());
            }
        }
    }
}
