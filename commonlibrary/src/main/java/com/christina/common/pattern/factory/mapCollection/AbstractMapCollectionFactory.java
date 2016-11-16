package com.christina.common.pattern.factory.mapCollection;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public abstract class AbstractMapCollectionFactory<TIdentifier, TComponent> {
    @SuppressWarnings("unchecked")
    @NonNull
    public TComponent[] createArray(@IntRange(from = 0, to = Integer.MAX_VALUE) final int size) {
        return (TComponent[]) Array.newInstance(getComponentClass(), size);
    }

    @NonNull
    public Collection<TComponent> createCollection(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new ArrayList<>(capacity);
    }

    @NonNull
    public Collection<TComponent> createCollection() {
        return createCollection(getDefaultCapacity());
    }

    @NonNull
    public List<TComponent> createList(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new ArrayList<>(capacity);
    }

    @NonNull
    public List<TComponent> createList() {
        return createList(getDefaultCapacity());
    }

    @NonNull
    public Map<TIdentifier, TComponent> createMap() {
        return createMap(getDefaultCapacity());
    }

    @NonNull
    public Map<TIdentifier, TComponent> createMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new HashMap<>(capacity);
    }

    @NonNull
    public NavigableMap<TIdentifier, TComponent> createNavigableMap() {
        return createNavigableMap(getDefaultCapacity());
    }

    @NonNull
    public NavigableMap<TIdentifier, TComponent> createNavigableMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new TreeMap<>();
    }

    @NonNull
    public SortedMap<TIdentifier, TComponent> createSortedMap() {
        return createSortedMap(getDefaultCapacity());
    }

    @NonNull
    public SortedMap<TIdentifier, TComponent> createSortedMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new TreeMap<>();
    }

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    protected final Class<TComponent> _componentClass;

    protected AbstractMapCollectionFactory(@NonNull final Class<TComponent> componentClass) {
        Contracts.requireNonNull(componentClass, "componentClass == null");

        _componentClass = componentClass;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected int getDefaultCapacity() {
        return 0;
    }
}
