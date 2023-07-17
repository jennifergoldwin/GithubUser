package com.example.githubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.githubuser.services.ApiHandler;
import com.example.githubuser.databinding.ActivityDetailUserBinding;
import com.example.githubuser.services.DbHandler;
import com.example.githubuser.services.UserDatabase;
import com.example.githubuser.services.adapter.ViewPagerAdapter;
import com.example.githubuser.utils.User;
import com.example.githubuser.utils.UserDAO;
import com.example.githubuser.utils.UserDetail;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserActivity extends AppCompatActivity {

    private  ActivityDetailUserBinding binding;
    private ArrayList<String> tabsText = new ArrayList<>();
    private ApiHandler handler;

    void init(){
        binding = ActivityDetailUserBinding.inflate(getLayoutInflater());
        handler = new ApiHandler();
        tabsText.add("Following");
        tabsText.add("Followers");
        binding.vpListFollow.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle()));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tabs,binding.vpListFollow,(tab, position) ->
                tab.setText(tabsText.get(position))
        );
        tabLayoutMediator.attach();
        binding.fab.setVisibility(View.INVISIBLE);
        binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(binding.getRoot());

        fetchData(getIntent().getStringExtra("username"));

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

        binding.fab.setOnClickListener(view -> {
            Intent intent = getIntent();
            UserDAO userDAO = DbHandler.getInstance(this).getUserDb().userDao();
            User user = new User(intent.getStringExtra("id"),intent.getStringExtra("username"),intent.getStringExtra("url"));
            if (userDAO.ifUserExists(intent.getStringExtra("id")).isEmpty()){
                userDAO.insertUser(user);
                Toast.makeText(this,"Added to favorite!",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"User already exists!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    void fetchData(String name){
        Call<UserDetail> user = handler.services.detailUser(name);
        user.enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {

                UserDetail temp = response.body();
                binding.tvName.setText(temp.getName());
                binding.tvUsername.setText(temp.getLogin());
                binding.tvJlhFollowers.setText(temp.getFollowers()==null?"0":temp.getFollowers());
                binding.tvJlhFollowing.setText(temp.getFollowing()==null?"0":temp.getFollowing());
                Picasso.get().load(temp.getAvatar_url()).into(binding.ivDetailUser);
                binding.fab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
            }
        });
    }
}