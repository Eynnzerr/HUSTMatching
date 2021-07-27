package com.example.hustmatching.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hustmatching.R;

public class EmailEditText extends LinearLayout {

    public EmailEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.email_myview, this);
    }

    public boolean isBoxEmpty() {
        EditText editText = findViewById(R.id.email_box);
        return editText.getText().toString().isEmpty();
    }
}
