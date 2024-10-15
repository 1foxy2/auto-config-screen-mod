package com.example.examplemod.config.json.values;

import com.example.examplemod.config.json.JsonValue;
import com.google.gson.JsonPrimitive;
import net.minecraft.client.OptionInstance;

public class BooleanJsonValue extends JsonValue<Boolean> {
    public BooleanJsonValue(String name, JsonPrimitive primitive) {
        super(name, primitive.getAsBoolean());
    }

    @Override
    public OptionInstance<Boolean> createScreenValue() {
        return OptionInstance.createBoolean(name, value, b -> value = b);
    }
}
