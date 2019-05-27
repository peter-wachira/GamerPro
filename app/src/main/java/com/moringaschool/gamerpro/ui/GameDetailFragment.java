package com.moringaschool.gamerpro.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.gamerpro.Constants.Constants;
import com.moringaschool.gamerpro.R;
import com.moringaschool.gamerpro.models.GameModel;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GameDetailFragment extends Fragment implements View.OnClickListener{

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
    private GameModel Game;

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
        Game = Parcels.unwrap(getArguments().getParcelable("game"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);
        ButterKnife.bind(this, view);


//        mPlatformNameTextView.setText(game.getmName());
        mPlatformNameTextView.setText(Game.getPlatforms().get(0));
        maliasesNameTextView.setText(Game.getAliases());
        mOriginalReleaseTextView.setText(Game.getDateAdded());
        mGameDeckTextView.setText(Game.getDeck());
        Picasso.get().load(Game.getImages()).into(mGameImageView);

        mSaveGameButton.setOnClickListener(this);

        return view;
    }
    public void onClick(View v) {

        if (v == mSaveGameButton) {
            DatabaseReference gameRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_GAMES);
            gameRef.push().setValue(Game);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

        }

    }

}