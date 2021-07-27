package com.example.hustmatching.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hustmatching.R;

public class PasswordEditText extends EditLinearLayout {

    private static final String TAG = "PasswordEditText";

    public PasswordEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.password_myview, this);
        editText = findViewById(R.id.password_box);
    }

    public boolean isBoxEmpty() {
        //EditText editText = findViewById(R.id.password_box);
        Log.d(TAG, "isBoxEmpty: " + editText.getText());
        return editText.getText().toString().isEmpty();
    }

}
