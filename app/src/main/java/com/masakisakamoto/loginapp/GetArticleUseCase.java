package com.masakisakamoto.loginapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masakisakamoto.loginapp.domain.Article;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by masakisakamoto on 7/28/17.
 */

public class GetArticleUseCase {

    private LoginAppClient client;
    private TokenRepository tokenRepository;
    private OnLoadingListener mListener;

    public GetArticleUseCase(Context context) {
        client = new LoginAppClient();
        tokenRepository = new TokenRepository(context);
    }


    public void get(String groupId, OnLoadingListener listener) {
        this.mListener = listener;
        client.articles(tokenRepository.getToken(), groupId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mListener.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = GsonFactory.getInstance().get();
                    String json = response.body().string();
                    Type listType = new TypeToken<Collection<Article>>(){}.getType();
                    List<Article> res = gson.fromJson(json, listType);
                    if (mListener != null) {
                        mListener.onSuccess(res);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onError("エラー");
                    }
                }
            }
        });
    }

    public void setListener(OnLoadingListener mListener) {
        this.mListener = mListener;
    }

    public interface OnLoadingListener {
        void onSuccess(List<Article> articles);
        void onError(String content);
    }
}
