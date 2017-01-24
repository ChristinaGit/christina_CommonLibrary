package com.christina.common.utility.tuple;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

public final class Tuple {
    @NonNull
    public static <T1> Tuple1<T1> from(@Nullable final T1 first) {
        return new Tuple1<>(first);
    }

    @NonNull
    public static <T1, T2> Tuple2<T1, T2> from(
        @Nullable final T1 first, @Nullable final T2 second) {
        return new Tuple2<>(first, second);
    }

    @NonNull
    public static <T1, T2, T3> Tuple3<T1, T2, T3> from(
        @Nullable final T1 first, @Nullable final T2 second, @Nullable final T3 third) {
        return new Tuple3<>(first, second, third);
    }

    private Tuple() {
        Contracts.unreachable();
    }
}
