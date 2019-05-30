package com.moringaschool.gamerpro.ui;

import android.content.Context;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseGameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    View mView;
    Context mContext;

    public FirebaseGameViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindGame(GameModel game) {

        ImageView GameImageView = (ImageView) mView.findViewById(R.id.gameImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.gameName);
        TextView platformTextView = (TextView) mView.findViewById(R.id.platformName);
        TextView originalReleaseTextView = (TextView) mView.findViewById(R.id.original_release);
        TextView aliasesNameTextView = (TextView) mView.findViewById(R.id.aliasesName);
        TextView gameDeckTextView = (TextView) mView.findViewById(R.id.gameDeck);


        Picasso.get().load(game.getImages()).into(GameImageView);
        platformTextView.setText(game.getPlatforms().get(0));
        aliasesNameTextView.setText(game.getAliases());

        nameTextView.setText(game.getName());
    }
    @Override
    public void onClick (View v){

        final ArrayList<GameModel> games = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GAMES).child(uid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    games.add(snapshot.getValue(GameModel.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, GameDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("games", Parcels.wrap(games));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}