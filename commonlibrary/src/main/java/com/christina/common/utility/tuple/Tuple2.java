package com.christina.common.utility.tuple;

import android.support.annotation.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class Tuple2<T1, T2> {
    @Nullable
    public T1 first;

    @Nullable
    public T2 second;
}
