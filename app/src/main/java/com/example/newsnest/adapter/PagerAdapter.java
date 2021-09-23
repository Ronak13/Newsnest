package com.example.newsnest.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsnest.EntertainmentFragment;
import com.example.newsnest.HealthFragment;
import com.example.newsnest.HomeFragment;
import com.example.newsnest.ScienceFragment;
import com.example.newsnest.SportsFragment;
import com.example.newsnest.TechFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private int tabCount;

    public PagerAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new HealthFragment();
            case 2:
                return new ScienceFragment();
            case 3:
                return new SportsFragment();
            case 4:
                return new TechFragment();
            case 5:
                return new EntertainmentFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
