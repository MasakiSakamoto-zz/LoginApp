package com.masakisakamoto.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.masakisakamoto.loginapp.domain.Article;

import java.util.List;


/**
 * Created by masakisakamoto on 7/28/17.
 */

public class ArticlesActivity extends BaseActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private TokenRepository tokenRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        init();

        toolbar.inflateMenu(R.menu.menu_articles);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_logout) {
                    tokenRepository.saveToken("");
                    startActivity(new Intent(ArticlesActivity.this, LoginActivity.class));
                    finish();
                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isOnLine(this)) {
            ArticlePagerAdapter adapter = new ArticlePagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void init() {
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tokenRepository = new TokenRepository(this);
    }
}
