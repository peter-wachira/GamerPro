package com.moringaschool.gamerpro.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.gamerpro.Constants.Constants;
import com.moringaschool.gamerpro.R;
import com.moringaschool.gamerpro.models.GameModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedGameListActivity extends AppCompatActivity {

    private DatabaseReference mGameReference;
    private FirebaseRecyclerAdapter<GameModel, FirebaseGameViewHolder> mFirebaseAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_display);
        ButterKnife.bind(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        mGameReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GAMES).child(uid);
        setUpFirebaseAdapter();
    }


    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<GameModel> options =
                new FirebaseRecyclerOptions.Builder<GameModel>()
                        .setQuery(mGameReference, GameModel.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<GameModel, FirebaseGameViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseGameViewHolder firebaseRestaurantViewHolder, int position, @NonNull GameModel game) {
                firebaseRestaurantViewHolder.bindGame(game);
            }

            @NonNull
            @Override
            public FirebaseGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
                return new FirebaseGameViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
}

