package com.moringaschool.gamerpro.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.gamerpro.models.GameModel;
import com.moringaschool.gamerpro.ui.GameDetailFragment;

import java.util.ArrayList;

public class GamePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<GameModel> mGames;

    public GamePagerAdapter(FragmentManager fm, ArrayList<GameModel> games) {
        super(fm);
        mGames = games;
    }
    @Override
    public Fragment getItem(int position) {
        return GameDetailFragment.newInstance(mGames.get(position));
    }
    @Override
    public int getCount() {
        return mGames.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mGames.get(position).getmName();
    }



}
