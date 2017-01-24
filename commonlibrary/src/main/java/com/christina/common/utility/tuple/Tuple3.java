package com.christina.common.utility.tuple;

import android.support.annotation.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class Tuple3<T1, T2, T3> {
    @Nullable
    public T1 first;

    @Nullable
    public T2 second;

    @Nullable
    public T3 third;
}
