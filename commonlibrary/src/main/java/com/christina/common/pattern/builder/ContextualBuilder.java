package com.christina.common.pattern.builder;

import android.support.annotation.NonNull;

public interface ContextualBuilder<T, TContext> {
    @NonNull
    T build(@NonNull TContext context);
}
