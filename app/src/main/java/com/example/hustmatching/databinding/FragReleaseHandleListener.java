package com.example.hustmatching.databinding;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.hustmatching.R;

public class FragReleaseHandleListener {

    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.search_items:
                Navigation.findNavController(view).navigate(R.id.action_releaseFragment_to_searchItemFragment);
                break;
            case R.id.search_people:
                break;
        }
    }
}
