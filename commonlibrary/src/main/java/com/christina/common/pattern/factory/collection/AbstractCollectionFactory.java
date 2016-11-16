package com.christina.common.pattern.factory.collection;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public abstract class AbstractCollectionFactory<TComponent>
    implements CollectionFactory<TComponent> {
    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public TComponent[] createArray(@IntRange(from = 0, to = Integer.MAX_VALUE) final int size) {
        return (TComponent[]) Array.newInstance(getComponentClass(), size);
    }

    @NonNull
    @Override
    public Collection<TComponent> createCollection(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new ArrayList<>(capacity);
    }

    @NonNull
    @Override
    public Collection<TComponent> createCollection() {
        return createCollection(getDefaultCapacity());
    }

    @NonNull
    @Override
    public List<TComponent> createList(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new ArrayList<>(capacity);
    }

    @NonNull
    @Override
    public List<TComponent> createList() {
        return createList(getDefaultCapacity());
    }

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    protected final Class<TComponent> _componentClass;

    protected AbstractCollectionFactory(@NonNull final Class<TComponent> componentClass) {
        Contracts.requireNonNull(componentClass, "componentClass == null");

        _componentClass = componentClass;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected int getDefaultCapacity() {
        return 0;
    }
}
