package com.example.githubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.githubuser.R;
import com.example.githubuser.databinding.ActivityFavouriteBinding;
import com.example.githubuser.databinding.ActivityMainBinding;
import com.example.githubuser.services.DbHandler;
import com.example.githubuser.services.adapter.ListUserAdapter;

public class FavouriteActivity extends AppCompatActivity {

    private ActivityFavouriteBinding binding;
    private  ListUserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavouriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        binding.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.settings2){
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            }

            else {
                return false;
            }
        });
        binding.rvFavorite.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListUserAdapter(true,this,DbHandler.getInstance(this).getUserDb().userDao().getAllUser());
        binding.rvFavorite.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new ListUserAdapter(true,this,DbHandler.getInstance(this).getUserDb().userDao().getAllUser());
        binding.rvFavorite.setAdapter(adapter);
    }
}