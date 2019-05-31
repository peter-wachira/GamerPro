package com.moringaschool.gamerpro.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.google.firebase.database.Query;
import com.moringaschool.gamerpro.Constants.Constants;
import com.moringaschool.gamerpro.R;
import com.moringaschool.gamerpro.adapters.FirebaseGameListAdapter;
import com.moringaschool.gamerpro.models.GameModel;
import com.moringaschool.gamerpro.util.OnStartDragListener;
import com.moringaschool.gamerpro.util.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedGameListActivity extends AppCompatActivity implements OnStartDragListener {

    private DatabaseReference mGameReference;
    private ItemTouchHelper mItemTouchHelper;
    private  FirebaseGameListAdapter mFirebaseAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_display);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }


    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        Query query = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GAMES).child(uid).orderByChild(Constants.FIREBASE_QUERY_INDEX);


        FirebaseRecyclerOptions<GameModel> options =
                new FirebaseRecyclerOptions.Builder<GameModel>()
                        .setQuery(query, GameModel.class)
                        .build();


        mFirebaseAdapter = new FirebaseGameListAdapter(options, query, this, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);

        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
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
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.stopListening(); }
}



