package com.christina.common.pattern.factory.map;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;
import com.christina.common.pattern.factory.map.MapFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AbstractMapFactory<TKey, TValue> implements MapFactory<TKey, TValue> {
    @NonNull
    @Override
    public Map<TKey, TValue> createMap() {
        return createMap(getDefaultCapacity());
    }

    @NonNull
    @Override
    public Map<TKey, TValue> createMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new HashMap<>(capacity);
    }

    @NonNull
    @Override
    public NavigableMap<TKey, TValue> createNavigableMap() {
        return createNavigableMap(getDefaultCapacity());
    }

    @NonNull
    @Override
    public NavigableMap<TKey, TValue> createNavigableMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new TreeMap<>();
    }

    @NonNull
    @Override
    public SortedMap<TKey, TValue> createSortedMap() {
        return createSortedMap(getDefaultCapacity());
    }

    @NonNull
    @Override
    public SortedMap<TKey, TValue> createSortedMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new TreeMap<>();
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected int getDefaultCapacity() {
        return 0;
    }
}
