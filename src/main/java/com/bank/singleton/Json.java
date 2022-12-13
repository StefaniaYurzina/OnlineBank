package com.bank.singleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public enum Json {
    INSTANCE;
    Gson gson;

    public Gson get() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
        }

        return gson;
    }
}
