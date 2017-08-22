package com.masakisakamoto.loginapp;

import com.masakisakamoto.loginapp.domain.Article;

import java.util.List;

/**
 * Created by masakisakamoto on 7/28/17.
 */

public interface ArticleRepository {

    void createAll(List<Article> articles);

    Article read(int id);

    List<Article> findAll();
}
