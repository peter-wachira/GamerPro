package com.moringaschool.gamerpro.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.gamerpro.R;
import org.parceler.Parcels;

import butterknife.BindView;

public class GameDetailActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
    }
}
