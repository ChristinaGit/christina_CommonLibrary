package com.christina.common;

import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;

public final class ConstantBuilder {
    @NonNull
    public static String savedStateKey(@NonNull final Class<?> clazz, @NonNull final String key) {
        Contracts.requireNonNull(clazz, "clazz == null");
        Contracts.requireNonNull(key, "key == null");

        return clazz.getName() + ":" + key;
    }

    private ConstantBuilder() {
        Contracts.unreachable();
    }
}
