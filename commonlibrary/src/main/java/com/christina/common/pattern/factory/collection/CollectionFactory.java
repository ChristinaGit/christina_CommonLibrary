package com.christina.common.pattern.factory.collection;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

public interface CollectionFactory<TComponent> {
    @NonNull
    TComponent[] createArray(@IntRange(from = 0, to = Integer.MAX_VALUE) final int size);

    @NonNull
    Collection<TComponent> createCollection(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity);

    @NonNull
    Collection<TComponent> createCollection();

    @NonNull
    List<TComponent> createList(@IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity);

    @NonNull
    List<TComponent> createList();
}
