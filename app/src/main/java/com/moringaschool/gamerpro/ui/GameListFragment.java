package com.moringaschool.gamerpro.ui;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.moringaschool.gamerpro.Constants.Constants;
import com.moringaschool.gamerpro.R;
import com.moringaschool.gamerpro.adapters.GameListAdapter;
import com.moringaschool.gamerpro.models.GameModel;
import com.moringaschool.gamerpro.services.GiantBombService;

import java.io.IOException;
import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GameListFragment extends Fragment  {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    ;

    private GameListAdapter mAdapter;
    public ArrayList<GameModel> mGames =new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private String mRecentPlatform;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        // Instructs fragment to include menu options:
        setHasOptionsMenu(true);
    }



    private void getGames(String platforms) {

        final GiantBombService giantBombService = new GiantBombService();

        giantBombService.findGames( platforms, new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    mGames = GiantBombService.processresults(response);
               getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            mAdapter = new GameListAdapter(getActivity(), mGames);



                            mRecyclerView.setAdapter(mAdapter);


                            RecyclerView.LayoutManager layoutManager =
                                    new LinearLayoutManager(getActivity());
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        }

                    });
                }
            }

        });


    }





    public GameListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);
        ButterKnife.bind(this, view);


        mRecentPlatform = mSharedPreferences.getString(Constants.PREFERENCES_PLATFORM_KEY, null);
        if (mRecentPlatform != null) {
            getGames(mRecentPlatform);
        }

        return view;



    }

}
