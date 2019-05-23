package com.moringaschool.gamerpro.ui;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

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

public class GamesDisplay extends AppCompatActivity {
    public static final String TAG = GamesDisplay.class.getSimpleName();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
;

    private GameListAdapter mAdapter;
    public ArrayList<GameModel> mGames =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_display);
        ButterKnife.bind(this);
        Toast.makeText(GamesDisplay.this,"Finding Game...",Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        String platforms = intent.getStringExtra("platforms");
        getGames(platforms);


    }




    private void getGames(String platforms) {

        final GiantBombService giantBombService = new GiantBombService();

        giantBombService.findGames( platforms, new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                    if (response.isSuccessful()) {
                        mGames = GiantBombService.processresults(response);
                        GamesDisplay.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                mAdapter = new GameListAdapter(getApplicationContext(), mGames);
                                mRecyclerView.setAdapter(mAdapter);
                                RecyclerView.LayoutManager layoutManager =
                                        new LinearLayoutManager(GamesDisplay.this);
                                mRecyclerView.setLayoutManager(layoutManager);
                                mRecyclerView.setHasFixedSize(true);
                            }

                        });
                    }
                }

        });


    }






}
