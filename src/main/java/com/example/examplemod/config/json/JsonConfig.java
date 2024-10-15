package com.example.examplemod.config.json;

import com.example.examplemod.config.json.values.BooleanJsonValue;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.logging.LogUtils;

import java.nio.file.Path;
import java.util.*;

public class JsonConfig {
    public final Path filePath;
    public final Set<JsonValue<?>> values = new LinkedHashSet<>();

    public JsonConfig(JsonObject jsonObject, Path path) {
        filePath = path;
        LogUtils.getLogger().warn(jsonObject.getClass().toString());
        jsonObject.entrySet().forEach(entry -> {
            String name = entry.getKey();
            if (entry.getValue() instanceof JsonPrimitive primitive) {
                if (primitive.isBoolean())
                    values.add(new BooleanJsonValue(name, primitive));
                if (primitive.isString())
                    values.add(new BooleanJsonValue(name, primitive));
                if (primitive.isNumber()) {
                    if (primitive.getAsNumber() instanceof Integer)
                        values.add(new BooleanJsonValue(name, primitive));
                }
            }
        });
    }
}
