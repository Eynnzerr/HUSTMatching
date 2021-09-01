package com.example.hustmatching.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hustmatching.R;

import java.util.ArrayList;
import java.util.List;

public class EditAdapter extends RecyclerView.Adapter<EditAdapter.EditViewHolder>{

    private Context context;
    private List<String> titles;
    private String[] keys;

    public interface SaveEditListener{
        void SaveEdit(Integer position, String string);
    }


    public EditAdapter(List<String> titles, Context context) {

        this.titles = titles;
        this.context = context;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    @NonNull
    @Override
    public EditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_item_layout,parent, false);
        final EditViewHolder holder = new EditViewHolder(view);
        holder.edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) holder.hints.setVisibility(View.VISIBLE);
                else holder.hints.setVisibility(View.GONE);
            }
        });
        holder.hint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击自动填充
                TextView textView = (TextView) v;
                holder.edit.setText(textView.getText());
            }
        });
        holder.hint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击自动填充
                TextView textView = (TextView) v;
                holder.edit.setText(textView.getText());
            }
        });
        holder.hint3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击自动填充
                TextView textView = (TextView) v;
                holder.edit.setText(textView.getText());
            }
        });
        holder.hint4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击自动填充
                TextView textView = (TextView) v;
                holder.edit.setText(textView.getText());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.edit.addTextChangedListener(new MyTextWatcher(holder));
        holder.edit.setTag(position);

        //按照位置进行关键词的绑定
        if(keys != null && keys.length > 3) {
            switch (position) {
                case 0:
                    holder.hint1.setText(keys[0]);
                    holder.hint2.setText(keys[1]);
                    holder.hint3.setText(keys[2]);
                    holder.hint4.setText(keys[3]);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class EditViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView title;
        EditText edit;
        LinearLayout hints;

        TextView hint1;
        TextView hint2;
        TextView hint3;
        TextView hint4;

        public EditViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.item_header);
            edit = itemView.findViewById(R.id.item_description);
            hints = itemView.findViewById(R.id.hints);
            hint1 = itemView.findViewById(R.id.hint_1);
            hint2 = itemView.findViewById(R.id.hint_2);
            hint3 = itemView.findViewById(R.id.hint_3);
            hint4 = itemView.findViewById(R.id.hint_4);
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
