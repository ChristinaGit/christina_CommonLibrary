package com.christina.common.pattern.factory.map;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;

public interface MapFactory<TKey, TValue> {
    @NonNull
    Map<TKey, TValue> createMap();

    @NonNull
    Map<TKey, TValue> createMap(@IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity);

    @NonNull
    NavigableMap<TKey, TValue> createNavigableMap();

    @NonNull
    NavigableMap<TKey, TValue> createNavigableMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity);

    @NonNull
    SortedMap<TKey, TValue> createSortedMap();

    @NonNull
    SortedMap<TKey, TValue> createSortedMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity);
}
