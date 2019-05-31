package com.moringaschool.gamerpro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.moringaschool.gamerpro.models.GameModel;
import com.moringaschool.gamerpro.ui.FirebaseGameViewHolder;
import com.moringaschool.gamerpro.util.ItemTouchHelperAdapter;
import com.moringaschool.gamerpro.util.OnStartDragListener;
import com.moringaschool.gamerpro.R;

public class FirebaseGameListAdapter extends FirebaseRecyclerAdapter<GameModel, FirebaseGameViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseGameListAdapter(FirebaseRecyclerOptions<GameModel> options,
                                         DatabaseReference ref,
                                         OnStartDragListener onStartDragListener,
                                         Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }
    @Override
    protected void onBindViewHolder(@NonNull FirebaseGameViewHolder firebaseGameViewHolder, int position, @NonNull GameModel gameModel) {
        firebaseGameViewHolder.bindGame(gameModel);
    }
    @NonNull
    @Override
    public FirebaseGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item_drag, parent, false);
        return new FirebaseGameViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        return false;
    }

    @Override
    public void onItemDismiss(int position){

    }

}