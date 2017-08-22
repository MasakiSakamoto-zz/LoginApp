package com.masakisakamoto.loginapp;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by masakisakamoto on 7/28/17.
 */

public class LoginAppClient {

    private static final String TAG = "LoginAppClient";
    private static final String URL = "https://mysterious-retreat-42661.herokuapp.com/";
    private static final String LOGIN = "api/login.php?user_id=%s&password=%s";
    private static final String ARTICLES = "api/articles.php?auth_token=%s&group_id=%s";

    private OkHttpClient client;

    public LoginAppClient() {
        client = new OkHttpClient().newBuilder()
                .readTimeout(15 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .build();
    }

    public void login(String id, String pass, Callback callback) {
        Request request = new Request.Builder()
                .url(URL + String.format(LOGIN, id, pass))
                .get()
                .build();

        client.newCall(request).enqueue(callback);
    }

    public void articles(String token, String groupId, Callback callback) {
        Request request = new Request.Builder()
                .url(URL + String.format(ARTICLES, token, groupId))
                .get().build();
        client.newCall(request).enqueue(callback);
    }
}
