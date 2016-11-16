package com.christina.common.pattern.factory.collection;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;

public interface TransitionCollectionFactory<TComponent, TArgument> {
    @NonNull
    TComponent[] createArray(@NonNull final TArgument argument);

    @NonNull
    Collection<TComponent> createCollection(@NonNull final TArgument argument);

    @NonNull
    List<TComponent> createList(@NonNull final TArgument argument);
}
