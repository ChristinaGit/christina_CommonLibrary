package com.christina.common.utility.tuple;

import android.support.annotation.Nullable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class Tuple1<T1> {
    @Nullable
    public T1 first;
}
