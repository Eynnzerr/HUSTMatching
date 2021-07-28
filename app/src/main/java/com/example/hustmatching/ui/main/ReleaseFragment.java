package com.example.hustmatching.ui.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hustmatching.R;
import com.example.hustmatching.databinding.FragReleaseHandleListener;
import com.example.hustmatching.databinding.FragmentReleaseBinding;

public class ReleaseFragment extends Fragment {

    private FragmentReleaseBinding binding;
    private View view;

    ActivityResultLauncher<String> requestPermission;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if(result) Toast.makeText(getContext(), "request permitted.", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(getContext(), "You denied permission request.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_release, container, false);
        view = binding.getRoot();
        binding.setReleaseFragListener(new FragReleaseHandleListener());

        //设置toolbar
        setHasOptionsMenu(true);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.releaseBar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        //申请访问相册的权限
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            requestPermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.release_toolbar, menu);
    }
}