package com.moringaschool.gamerpro;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GiantBombService {
    public static final String jsonformat = "json";
    private static final String TAG = "GiantBombService";

    public static void findGames(String platforms, Callback callback) {


        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GIANTBOMB_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.GIANTBOMB_GAME__QUERY_PARAMETER, platforms);
        urlBuilder.addQueryParameter("api_key", Constants.GIANTBOMB_TOKEN);

        urlBuilder.addQueryParameter(Constants.GIANTBOMB__QUERY_PARAMETER, jsonformat);

        String url = urlBuilder.build().toString();

        System.out.println(url);

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.GIANTBOMB_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }




    public static ArrayList<GameModel> processresults(Response response) {


        ArrayList<GameModel> games = new ArrayList<>();
        try {

            String jsonData = response.body().string();
            Log.v(TAG, jsonData);

            JSONObject giantbombJSON = new JSONObject(jsonData);
            JSONArray gameGBJSON = giantbombJSON.getJSONArray("results");


            if (response.isSuccessful()) {
                for (int i = 0; i < gameGBJSON.length(); i++) {

                    JSONObject gameJSON = gameGBJSON.getJSONObject(i);

                    String name = gameJSON.getString("name");

                    String aliases = gameJSON.getString("aliases");

                    String dateadded = gameJSON.getString("date_added");

                    String deck = gameJSON.getString("deck");


                    String images = gameJSON.getJSONObject("image").getString("icon_url");


                    ArrayList<String> platforms = new ArrayList<>();
                    JSONArray platformsJSON = gameJSON.getJSONArray("platforms");
                    for (int y = 0; y < platformsJSON.length(); y++) {
                        platforms.add(platformsJSON.getJSONObject(y).getString("name"));
                    }


                    String originalrelease = gameJSON.getString("original_release_date");

                    GameModel gameModel = new GameModel(deck, platforms, dateadded, originalrelease, aliases, name, images);

                    games.add(gameModel);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "processresults: done");
        return games;

    }

}
