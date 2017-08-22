package com.masakisakamoto.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    private EditText id;
    private EditText pass;
    private ProgressBar progress;

    private LoginUseCase loginUseCase;
    private TokenRepository tokenRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {

        loginUseCase = new LoginUseCase();
        tokenRepository = new TokenRepository(this);

        id = (EditText) findViewById(R.id.edit_id);
        pass = (EditText) findViewById(R.id.edit_pass);
        progress = (ProgressBar) findViewById(R.id.progressbar);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isOnLine(v.getContext())) {
                    showToast("入力してください");
                    return;
                }

                if (progress.getVisibility() == View.GONE) {

                    String i = id.getText().toString();
                    String p = pass.getText().toString();
                    if (i.isEmpty() || p.isEmpty()) {
                        showSnackbar("入力してください", (RelativeLayout) findViewById(R.id.layout_root));
                        return;
                    }

                    loginUseCase.login(i, p, new LoginUseCase.OnLoadingListener() {
                        @Override
                        public void onSuccess(String authToken) {
                            tokenRepository.saveToken(authToken);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(LoginActivity.this, ArticlesActivity.class));
                                    finish();
                                }
                            });
                        }

                        @Override
                        public void onError(final String content) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progress.setVisibility(View.GONE);
                                    showSnackbar(content, (ViewGroup) findViewById(R.id.layout_root));
                                }
                            });
                        }
                    });
                } else {
                    showSnackbar("ログイン中です", (RelativeLayout) findViewById(R.id.layout_root));
                }
            }
        });
    }

}
