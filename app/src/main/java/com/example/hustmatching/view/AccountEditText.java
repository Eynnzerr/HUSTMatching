package com.example.hustmatching.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hustmatching.R;

public class AccountEditText extends EditLinearLayout {

    //private EditText editText;

    private static final String TAG = "AccountEditText";

    public AccountEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.account_myview, this);
        editText = findViewById(R.id.account_box);
    }

    public boolean isBoxEmpty() {
        //EditText editText = findViewById(R.id.account_box);
        Log.d(TAG, "isBoxEmpty: " + editText.getText());
        return editText.getText().toString().isEmpty();
    }

}
