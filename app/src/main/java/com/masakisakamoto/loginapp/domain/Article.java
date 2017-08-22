package com.masakisakamoto.loginapp.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by masakisakamoto on 7/28/17.
 */

public class Article extends RealmObject {
    @PrimaryKey

    public int id;
    public String iconUrl;
    public String imageUrl;
    public String title;
    public String description;
}
