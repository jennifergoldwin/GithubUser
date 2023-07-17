package com.example.githubuser.services.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.githubuser.fragments.FollowersFragment;
import com.example.githubuser.fragments.FollowingFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FollowingFragment();
            case 1:
                return new FollowersFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
