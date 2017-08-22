package com.masakisakamoto.loginapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by masakisakamoto on 7/28/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        if (!isOnLine(this)) {
            showOffLineAlert();
        }
    }

    protected void showOffLineAlert() {
        if (findViewById(R.id.layout_root) != null) {
            showSnackbar("ネットワークに接続されいません", (ViewGroup) findViewById(R.id.layout_root));
        } else {
            showToast("ネットワークに接続されていません");
        }
    }

    public static boolean isOnLine(Context context) {

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if ( info != null ){
            return info.isConnected();
        } else {
            return false;
        }
    }

    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void showSnackbar(String content, ViewGroup layout) {
        Snackbar.make(layout, content, Snackbar.LENGTH_SHORT).show();
    }
}
