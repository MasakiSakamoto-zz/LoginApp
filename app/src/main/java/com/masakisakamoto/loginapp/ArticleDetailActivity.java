package com.masakisakamoto.loginapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.masakisakamoto.loginapp.domain.Article;
import com.squareup.picasso.Picasso;

/**
 * Created by masakisakamoto on 7/28/17.
 */

public class ArticleDetailActivity extends AppCompatActivity {

    private static final String ID = "id";

    private ImageView image;
    private TextView title;
    private TextView description;

    private ArticleRepository articleRepository;
    public long url;

    public static Intent newIntent(Context context, int id) {
        Intent i = new Intent(context, ArticleDetailActivity.class);
        i.putExtra(ID, id);
        return  i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        if(url != null && !url.isEmpty()) {
        } else

        findViewById(R.id.btn_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ArticleDetailActivity.this, WebActivity.class));
            }
        });

        int id = getIntent().getIntExtra(ID, -1);
        if (id != -1) {
            init(id);
        }
    }

    private void init(int id) {

        image = (ImageView) findViewById(R.id.image_article);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);

        articleRepository = new ArticleRepositoryImpl();
        Article a = articleRepository.read(id);

        Picasso.with(this).load(a.imageUrl).into(image);
        title.setText(a.title);
        description.setText(a.description);
    }
}
