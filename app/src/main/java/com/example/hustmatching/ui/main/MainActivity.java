package com.example.hustmatching.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hustmatching.R;
import com.example.hustmatching.adapter.EditAdapter;
import com.example.hustmatching.databinding.ActivityMainBinding;
import com.example.hustmatching.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements EditAdapter.SaveEditListener {

    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //设置底部导航
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(binding.bottomNavigation.getMenu()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
    }


    @Override
    public void SaveEdit(Integer position, String string) {
        try {
            Log.d("SaveEdit", "SaveEdit: " + string + " at pos " + position);
            viewModel.addTag(position, string);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}