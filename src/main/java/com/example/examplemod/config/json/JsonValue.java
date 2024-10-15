package com.example.examplemod.config.json;

import net.minecraft.client.OptionInstance;

public abstract class JsonValue<T> {
    protected T value;
    public final String name;

    public JsonValue(String name, T value) {
        this.value = value;
        this.name = name;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public abstract OptionInstance<T> createScreenValue();
}
