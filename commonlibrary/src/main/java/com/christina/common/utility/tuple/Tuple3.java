package com.christina.common.utility.tuple;

import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(doNotUseGetters = true)
@Accessors(prefix = "_")
public final class Tuple3<T1, T2, T3> {
    /*package-private*/ Tuple3(
        @Nullable final T1 first, @Nullable final T2 second, @Nullable final T3 third) {
        _first = first;
        _second = second;
        _third = third;
    }

    @Getter
    @Nullable
    private final T1 _first;

    @Getter
    @Nullable
    private final T2 _second;

    @Getter
    @Nullable
    private final T3 _third;
}
