package com.example.hustmatching.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hustmatching.R;

public class AuthenticationEditText extends EditLinearLayout {

    public AuthenticationEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.authens_myview, this);
        editText = findViewById(R.id.authens_box);
    }

    public boolean isBoxEmpty() {
        //EditText editText = findViewById(R.id.email_box);
        return editText.getText().toString().isEmpty();
    }

    public void setOnTextClickListener(View.OnClickListener listener) {
        TextView tv = findViewById(R.id.authens_request);
        tv.setOnClickListener(listener);
    }

    public TextView getTextView() {
        return findViewById(R.id.authens_request);
    }

}
