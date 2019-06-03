package com.moringaschool.gamerpro.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.gamerpro.Constants.Constants;
import com.moringaschool.gamerpro.R;
import com.moringaschool.gamerpro.models.GameModel;
import com.moringaschool.gamerpro.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseGameViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {


    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    View mView;
    Context mContext;
    public ImageView mGameImageView;

    public FirebaseGameViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindGame(GameModel game) {

        mGameImageView = (ImageView) mView.findViewById(R.id.gameImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.gameName);
        TextView platformTextView = (TextView) mView.findViewById(R.id.platformName);
        TextView originalReleaseTextView = (TextView) mView.findViewById(R.id.original_release);
        TextView aliasesNameTextView = (TextView) mView.findViewById(R.id.aliasesName);
        TextView gameDeckTextView = (TextView) mView.findViewById(R.id.gameDeck);


        Picasso.get().load(game.getImages()).into(mGameImageView);
        platformTextView.setText(game.getPlatforms().get(0));
        aliasesNameTextView.setText(game.getAliases());

        nameTextView.setText(game.getName());
    }

    @Override
    public void onItemSelected() {
        Log.d("Animation", "onItemSelected");
        // we will add animations here
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        Log.d("Animation", "onItemClear");
        // we will add animations here
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }
}