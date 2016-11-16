package com.christina.common.pattern.factory;

import android.support.annotation.NonNull;

public interface TransitionFactory<T, TArgument> {
    @NonNull
    T create(@NonNull final TArgument argument);
}
