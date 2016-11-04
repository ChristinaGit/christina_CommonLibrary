package com.christina.common.pattern.builder;

import android.support.annotation.NonNull;

public interface Builder<T> {
    @NonNull
    T build();
}
