package com.example.githubuser.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubuser.R;
import com.example.githubuser.databinding.FragmentFollowersBinding;
import com.example.githubuser.services.ApiHandler;
import com.example.githubuser.services.adapter.ListUserAdapter;
import com.example.githubuser.utils.User;
import com.example.githubuser.utils.UserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersFragment extends Fragment {

    private FragmentFollowersBinding binding;
    private ApiHandler handler;
    public FollowersFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFollowersBinding.inflate(inflater, container, false);
        handler = new ApiHandler();
        binding.rvFollowers.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.flProgressbar.setVisibility(View.VISIBLE);
        fetchData(getActivity().getIntent().getStringExtra("username").toString());
        return binding.getRoot();
    }
    void fetchData(String name){
        Call<List<User>> userList = handler.services.getFollowers(name);
        userList.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                binding.flProgressbar.setVisibility(View.GONE);
                binding.rvFollowers.setAdapter(new ListUserAdapter(false,getContext(),response.body()));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }
}