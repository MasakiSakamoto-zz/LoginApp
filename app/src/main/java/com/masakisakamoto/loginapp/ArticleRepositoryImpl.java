package com.masakisakamoto.loginapp;

import com.masakisakamoto.loginapp.domain.Article;

import java.util.List;

import io.realm.Realm;

/**
 * Created by masakisakamoto on 7/28/17.
 */

public class ArticleRepositoryImpl implements ArticleRepository {

    private Realm realm;

    public ArticleRepositoryImpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void createAll(final List<Article> articles) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(articles);
            }
        });
    }

    @Override
    public Article read(int id) {
        Article a;
        realm.beginTransaction();
        a = realm.where(Article.class).equalTo("id", id).findFirst();
        realm.cancelTransaction();
        return a;
    }

    @Override
    public List<Article> findAll() {
        List<Article> l;
        realm.beginTransaction();
        l = realm.where(Article.class).findAll();
        realm.cancelTransaction();
        return l;
    }
}
