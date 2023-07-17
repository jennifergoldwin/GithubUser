package com.example.githubuser.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubuser.R;
import com.example.githubuser.databinding.FragmentFollowersBinding;
import com.example.githubuser.databinding.FragmentFollowingBinding;
import com.example.githubuser.services.ApiHandler;
import com.example.githubuser.services.adapter.ListUserAdapter;
import com.example.githubuser.utils.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingFragment extends Fragment {

    private FragmentFollowingBinding binding;
    private ApiHandler handler;
    public FollowingFragment() {
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
        binding = FragmentFollowingBinding.inflate(inflater, container, false);
        handler = new ApiHandler();
        binding.rvFollowers.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.flProgressbar.setVisibility(View.VISIBLE);
        fetchData(getActivity().getIntent().getStringExtra("username").toString());
        return binding.getRoot();
    }

    void fetchData(String name){
        Call<List<User>> userList = handler.services.getFollowing(name);
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