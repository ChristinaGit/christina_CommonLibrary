package com.christina.common.pattern.factory;

import android.support.annotation.NonNull;

public interface Factory<T> {
    @NonNull
    T create();
}
