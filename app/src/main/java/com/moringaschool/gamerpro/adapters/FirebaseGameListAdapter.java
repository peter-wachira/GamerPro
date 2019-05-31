package com.moringaschool.gamerpro.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.moringaschool.gamerpro.models.GameModel;
import com.moringaschool.gamerpro.ui.FirebaseGameViewHolder;
import com.moringaschool.gamerpro.ui.GameDetailActivity;
import com.moringaschool.gamerpro.util.ItemTouchHelperAdapter;
import com.moringaschool.gamerpro.util.OnStartDragListener;
import com.moringaschool.gamerpro.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseGameListAdapter extends FirebaseRecyclerAdapter<GameModel, FirebaseGameViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<GameModel> mGames = new ArrayList<>();

    public FirebaseGameListAdapter(FirebaseRecyclerOptions<GameModel> options,
                                   Query ref,
                                   OnStartDragListener onStartDragListener,
                                   Context context) {
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;


        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {




            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mGames.add(dataSnapshot.getValue(GameModel.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @NonNull
    @Override
    public FirebaseGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item_drag, parent, false);
        return new FirebaseGameViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){

        Collections.swap(mGames, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position){
        mGames.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase(){
        for(GameModel gameModel: mGames){
            int index =mGames.indexOf(gameModel);
            DatabaseReference ref = getRef(index);
            gameModel.setIndex(Integer.toString(index));
            ref.setValue(gameModel);
        }
    }
    @Override
    public void stopListening() { super.stopListening(); mRef.removeEventListener(mChildEventListener); }

    @Override
    protected void onBindViewHolder(@NonNull final FirebaseGameViewHolder viewHolder, int position, @NonNull GameModel gameModel) {
        viewHolder.bindGame(gameModel);
        viewHolder.mGameImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }

        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GameDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("games", Parcels.wrap(mGames));
                mContext.startActivity(intent);
            }
        });

    }

}