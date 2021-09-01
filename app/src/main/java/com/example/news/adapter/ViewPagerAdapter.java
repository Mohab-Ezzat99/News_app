package com.example.news.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.news.fragments.EntertainmentFragment;
import com.example.news.fragments.HealthFragment;
import com.example.news.fragments.HomeFragment;
import com.example.news.fragments.ScienceFragment;
import com.example.news.fragments.SportsFragment;
import com.example.news.fragments.TechnologyFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new SportsFragment();

            case 2:
                return new HealthFragment();

            case 3:
                return new ScienceFragment();

            case 4:
                return new EntertainmentFragment();

            case 5:
                return new TechnologyFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }
}
