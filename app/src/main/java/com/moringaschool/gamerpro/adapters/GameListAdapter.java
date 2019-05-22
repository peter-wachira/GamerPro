package com.moringaschool.gamerpro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.gamerpro.models.GameModel;
import com.moringaschool.gamerpro.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {

   private  ArrayList<GameModel> mGames = new ArrayList<>();

   private Context mContext;

   public  GameListAdapter(Context context,ArrayList<GameModel> games){


       mContext =context;
       mGames = games;
   }


    @Override
    public GameListAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);

        GameListAdapter.GameViewHolder viewHolder = new GameViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(GameListAdapter.GameViewHolder holder, int position) {
        holder.bindGame(mGames.get(position));
    }
    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gameImageView)
        ImageView mGameImageView;
        @BindView(R.id.gameName)
        TextView  mGameNameTextView;
        @BindView(R.id.platformName)
        TextView  mPlatformNameTextView;
        @BindView(R.id.aliasesName)
        TextView  mAliasesNameTextView;
        @BindView(R.id.dateAdded)
        TextView  mDateAddedTextView;

//        @BindView(R.id.recyclerView) RecyclerView mRecyclerView;


        private Context mContext;


        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindGame(GameModel game) {
            mGameNameTextView.setText(game.getmName());
            mPlatformNameTextView.setText(game.getPlatforms().get(0));
            mAliasesNameTextView.setText(game.getmAliases());
            mDateAddedTextView.setText(game.getmDateadded());
            Picasso.get().load( game.getmImages()).into(mGameImageView);
        }
    }
}
