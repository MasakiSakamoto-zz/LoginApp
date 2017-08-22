package com.masakisakamoto.loginapp;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by masakisakamoto on 7/28/17.
 */

public class GsonFactory {

    private static final GsonFactory ourInstance = new GsonFactory();

    public static GsonFactory getInstance() {
        return ourInstance;
    }

    private GsonFactory() {
    }

    public Gson get() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }
}
