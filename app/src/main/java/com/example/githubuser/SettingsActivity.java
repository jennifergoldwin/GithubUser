package com.example.githubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;

import com.example.githubuser.databinding.ActivityFavouriteBinding;
import com.example.githubuser.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private  ActivitySettingsBinding binding;
    private SharedPreferences preferences;

    void init(){
        preferences = getSharedPreferences("theme",MODE_PRIVATE);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        init();
        setContentView(binding.getRoot());

        String theme = preferences.getString("theme","LIGHT");
        if (theme.equals("LIGHT")){
            binding.switchTheme.setChecked(false);
        }else{
            binding.switchTheme.setChecked(true);
        }
        binding.switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    preferences.edit().putString("theme","DARK").apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    preferences.edit().putString("theme","LIGHT").apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }
}