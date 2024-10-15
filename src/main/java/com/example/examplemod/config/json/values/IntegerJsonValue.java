package com.example.examplemod.config.json.values;

import com.example.examplemod.config.json.JsonValue;
import com.google.gson.JsonPrimitive;
import net.minecraft.client.OptionInstance;

public class IntegerJsonValue extends JsonValue<Integer> {
    public IntegerJsonValue(String name, JsonPrimitive primitive) {
        super(name, primitive.getAsInt());
    }

    @Override
    public OptionInstance<Integer> createScreenValue() {
        return null;
    }
}
