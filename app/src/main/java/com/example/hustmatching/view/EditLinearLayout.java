package com.example.hustmatching.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class EditLinearLayout extends LinearLayout {

    EditText editText;

    public EditLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EditText getEditText() {
        return editText;
    }
}
