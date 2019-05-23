package com.moringaschool.gamerpro.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.moringaschool.gamerpro.R;
import com.moringaschool.gamerpro.models.GameModel;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GameDetailFragment extends Fragment {

    @BindView(R.id.gameImageView)
    ImageView mGameImageView;
//    @BindView(R.id.gameName)
//    TextView mGameNameTextView;
    @BindView(R.id.platformName)
    TextView mPlatformNameTextView;
    @BindView(R.id.gameDeck)
    TextView mGameDeckTextView;
    @BindView(R.id.original_release)
    TextView mOriginalReleaseTextView;
    @BindView(R.id.aliasesName)
    TextView maliasesNameTextView;
    @BindView(R.id.saveGameButton)
    TextView mSaveGameButton;
    private GameModel game;

    public static GameDetailFragment newInstance(GameModel game) {

        GameDetailFragment gameDetailFragment = new GameDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("game", Parcels.wrap(game));
        gameDetailFragment.setArguments(args);
        return gameDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = Parcels.unwrap(getArguments().getParcelable("game"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);
        ButterKnife.bind(this, view);


//        mPlatformNameTextView.setText(game.getmName());
        mPlatformNameTextView.setText(game.getPlatforms().get(0));
        maliasesNameTextView.setText(game.getmAliases());
        mOriginalReleaseTextView.setText(game.getmDateadded());
        mGameDeckTextView.setText(game.getmDeck());
        Picasso.get().load(game.getmImages()).into(mGameImageView);

        return view;
    }

}

