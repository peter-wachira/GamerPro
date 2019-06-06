package com.moringaschool.gamerpro.ui;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.util.Log;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.moringaschool.gamerpro.Constants.Constants;
        import com.moringaschool.gamerpro.models.GameModel;
        import com.moringaschool.gamerpro.services.GiantBombService;
        import com.moringaschool.gamerpro.R;
        import com.moringaschool.gamerpro.adapters.GameListAdapter;

        import java.io.IOException;
        import java.util.ArrayList;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import okhttp3.Call;
        import okhttp3.Callback;
        import okhttp3.Response;

public class GamesListActivity extends AppCompatActivity {
    public static final String TAG = GamesListActivity.class.getSimpleName();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
;

    private GameListAdapter mAdapter;
    public ArrayList<GameModel> mGames =new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private String mRecentPlatform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_display);
        ButterKnife.bind(this);
        Toast.makeText(GamesListActivity.this,"Finding Platform...",Toast.LENGTH_LONG).show();
//        Intent intent = getIntent();



//        String platforms = intent.getStringExtra("query");
//        getGames(platforms);


//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//
//        mRecentPlatform = mSharedPreferences.getString(Constants.PREFERENCES_PLATFORM_KEY, null);
//        if (mRecentPlatform != null) {
//            getGames(mRecentPlatform);
//        }
    }




//    private void getGames(String platforms) {
//
//        final GiantBombService giantBombService = new GiantBombService();
//
//        giantBombService.findGames( platforms, new Callback() {
//
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                    if (response.isSuccessful()) {
//                        mGames = GiantBombService.processresults(response);
//                        GamesListActivity.this.runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//
//                                mAdapter = new GameListAdapter(getApplicationContext(), mGames);
//
//
//
//                                mRecyclerView.setAdapter(mAdapter);
//
//
//                                RecyclerView.LayoutManager layoutManager =
//                                        new LinearLayoutManager(GamesListActivity.this);
//                                mRecyclerView.setLayoutManager(layoutManager);
//                                mRecyclerView.setHasFixedSize(true);
//                            }
//
//                        });
//                    }
//                }
//
//        });
//
//
//    }






}
