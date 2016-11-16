package com.christina.common.pattern.factory.map;

import android.support.annotation.NonNull;

import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;

public interface TransitionMapFactory<TKey, TValue, TArgument> {
    @NonNull
    Map<TKey, TValue> createMap(@NonNull final TArgument argument);

    @NonNull
    NavigableMap<TKey, TValue> createNavigableMap(@NonNull final TArgument argument);

    @NonNull
    SortedMap<TKey, TValue> createSortedMap(@NonNull final TArgument argument);
}
