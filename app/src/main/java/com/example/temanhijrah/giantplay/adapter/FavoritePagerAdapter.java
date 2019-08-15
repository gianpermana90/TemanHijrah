package com.example.temanhijrah.giantplay.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.temanhijrah.giantplay.ui.fragment.FragmentFavoriteBacaan;
import com.example.temanhijrah.giantplay.ui.fragment.FragmentFavoriteDoa;
import com.example.temanhijrah.giantplay.ui.fragment.FragmentFavoriteSurah;


public class FavoritePagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public FavoritePagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new FragmentFavoriteSurah();
            case 1 :
                return new FragmentFavoriteDoa();
            case 2 :
                return new FragmentFavoriteBacaan();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 : return "Surah";
            case 1 : return "Doa Harian";
            case 2 : return "Bacaan Sholat";
            default: return null;
        }
    }
}
