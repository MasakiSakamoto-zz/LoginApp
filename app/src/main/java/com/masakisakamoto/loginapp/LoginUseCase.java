package com.masakisakamoto.loginapp;

import java.io.IOException;

import com.masakisakamoto.loginapp.LoginResponse;
import com.masakisakamoto.loginapp.LoginAppClient;
import com.masakisakamoto.loginapp.GsonFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by masakisakamoto on 7/28/17.
 */

public class LoginUseCase {

    public void login(final String id, final String pass, final OnLoadingListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LoginAppClient client = new LoginAppClient();
                client.login(id, pass, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        LoginResponse res = GsonFactory.getInstance().get().fromJson(json, LoginResponse.class);
                        if (response.isSuccessful()) {
                            listener.onSuccess(res.authToken);
                        } else {
                            // TODO
                            listener.onError(res.errorDescription);
                        }
                    }
                });
            }
        }).start();
    }

    public interface OnLoadingListener {
        void onSuccess(String authToken);
        void onError(String content);
    }
}
