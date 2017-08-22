package com.masakisakamoto.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

/**
 * Created by masakisakamoto on 7/26/17.
 */

public class LauncherActivity extends BaseActivity {

    private TextView textVersionName;
    private TokenRepository tokenRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        init();
        showVersionName();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (tokenRepository.getToken().isEmpty()) {
                    startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(LauncherActivity.this, ArticlesActivity.class));
                    finish();
                }
            }
        }, 3000);
    }

    private void init() {
        textVersionName = (TextView) findViewById(R.id.text_version_name);
        tokenRepository = new TokenRepository(this);

    }

    private void showVersionName() {
        textVersionName.setText(MyApp.getVersionName(this));

    }

}
