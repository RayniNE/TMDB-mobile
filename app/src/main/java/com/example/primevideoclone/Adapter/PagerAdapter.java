package com.example.primevideoclone.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.primevideoclone.Fragments.Family;
import com.example.primevideoclone.Fragments.HomeFragment;
import com.example.primevideoclone.Fragments.Movies;
import com.example.primevideoclone.Fragments.TvShows;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0:
                return new HomeFragment();

            case 1:
                return new TvShows();

            case 2:
                return new Movies();

            case 3:
                return new Family();
        }

        return null;

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
