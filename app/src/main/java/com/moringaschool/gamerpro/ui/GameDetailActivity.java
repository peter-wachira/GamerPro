package com.moringaschool.gamerpro.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.gamerpro.R;
import com.moringaschool.gamerpro.adapters.GamePagerAdapter;
import com.moringaschool.gamerpro.models.GameModel;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moringaschool.gamerpro.R.layout.activity_game_detail;

public class GameDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private GamePagerAdapter adapterViewPager;
    ArrayList<GameModel> mGames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_game_detail);
        ButterKnife.bind(this);
        mGames = Parcels.unwrap(getIntent().getParcelableExtra("games"));

        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new GamePagerAdapter(getSupportFragmentManager(), mGames);

        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
