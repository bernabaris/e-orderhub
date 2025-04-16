package com.github.bernabaris.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public class GsonUtil {
    private GsonUtil() {
    }

    private static Gson gson;
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static Gson getGson(){
        if (gson == null){
            gson = new GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .setPrettyPrinting().setDateFormat(DATE_FORMAT)
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                    .create();
        }
        return gson;
    }
}
