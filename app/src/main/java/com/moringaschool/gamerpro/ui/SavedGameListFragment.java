package com.moringaschool.gamerpro.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moringaschool.gamerpro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedGameListFragment extends Fragment {


    public SavedGameListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_game_list, container, false);
    }

}
