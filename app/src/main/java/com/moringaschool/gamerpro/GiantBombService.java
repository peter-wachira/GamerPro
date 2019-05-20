package com.moringaschool.gamerpro;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GiantBombService {

    public static void findPlatforms(String aliases,String filter, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.   GIANTBOMB_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.GIANTBOMB_PLATFORMS__QUERY_PARAMETER, aliases);
        urlBuilder.addQueryParameter(Constants.GIANTBOMB_SORT_QUERY_PARAMETER,filter);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.GIANTBOMB_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);



    }









}
