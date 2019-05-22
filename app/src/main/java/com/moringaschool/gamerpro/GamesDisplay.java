package com.moringaschool.gamerpro;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Adapter;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.ButtonBarLayout;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import java.io.IOException;
        import java.util.ArrayList;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import okhttp3.Call;
        import okhttp3.Callback;
        import okhttp3.Response;

public class GamesDisplay extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = GamesDisplay.class.getSimpleName();
    @BindView(R.id.platforms)
    EditText platforms;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.trigger)
    Button trigger;

    private GameListAdapter mAdapter;
    public ArrayList<GameModel> mGames =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_display);
        ButterKnife.bind(this);
        trigger.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        if( v == trigger){

            String platformz = platforms.getText().toString();

            getGames(platformz);
        }
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
