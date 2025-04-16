package com.github.bernabaris.common.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTypeAdapter extends TypeAdapter<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime offsetDateTime) throws IOException {
        if (offsetDateTime == null){
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(offsetDateTime.toString());
        }
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        if(jsonReader.peek() == JsonToken.NULL){
            jsonReader.nextNull();
            return null;
        } else{
            return LocalDateTime.parse(jsonReader.nextString());
        }
    }
}

