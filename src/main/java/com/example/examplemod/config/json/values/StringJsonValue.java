package com.example.examplemod.config.json.values;

import com.example.examplemod.config.json.JsonValue;
import com.google.gson.JsonPrimitive;
import net.minecraft.client.OptionInstance;

public class StringJsonValue extends JsonValue<String> {
    public StringJsonValue(String name, JsonPrimitive primitive) {
        super(name, primitive.getAsString());
    }

    @Override
    public OptionInstance<String> createScreenValue() {
        return null;
    }
}
