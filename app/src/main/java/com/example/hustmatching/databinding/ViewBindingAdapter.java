package com.example.hustmatching.databinding;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.example.hustmatching.view.EditLinearLayout;

public class ViewBindingAdapter {

    private static final String TAG = "ViewBindingAdapter";

    //为EditLinearLayout的barText属性提供从model到view的绑定
    @BindingAdapter("barText")
    public static void setBarText(EditLinearLayout view, String text) {
        if(view == null) return;
        if(view.getEditText() == null) return;

        if(view.getEditText().getText().toString().trim() != text) {
            view.getEditText().setText(text);
        }
    }

    //为EditLinearLayout的barText属性提供从view到model的绑定
    @InverseBindingAdapter(attribute = "barText", event = "textAttrChanged")
    public static String getBarText(EditLinearLayout view) {
        Log.d(TAG, "getBarText: ");
        return view.getEditText().getText().toString();
    }

    //使改变生效
    @BindingAdapter("textAttrChanged")
    public static void setTextChangeListener(EditLinearLayout view, InverseBindingListener listener) {
        if(view == null) return;
        if(view.getEditText() == null) return;

        view.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listener.onChange();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
