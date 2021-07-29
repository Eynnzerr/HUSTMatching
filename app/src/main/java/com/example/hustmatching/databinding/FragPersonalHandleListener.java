package com.example.hustmatching.databinding;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.hustmatching.R;

public class FragPersonalHandleListener {
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.info_btn:
                Navigation.findNavController(view).navigate(R.id.action_personalFragment_to_myInfoFragment);
                break;
            case R.id.release_btn:
                Navigation.findNavController(view).navigate(R.id.action_personalFragment_to_myMatchFragment);
                break;
            case R.id.setting_btn:
                Navigation.findNavController(view).navigate(R.id.action_personalFragment_to_myContactFragment);
                break;
        }
    }
}
