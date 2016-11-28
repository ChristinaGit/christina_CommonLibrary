package com.christina.common.data.dao.factory;

import android.database.Cursor;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;

import com.christina.common.contract.Contracts;
import com.christina.common.data.model.Model;
import com.christina.common.pattern.factory.TransitionFactory;
import com.christina.common.pattern.factory.mapCollection.AbstractMapCollectionFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public class ClassModelCollectionFactory<TModel extends Model>
    extends AbstractMapCollectionFactory<Long, TModel> implements ModelCollectionFactory<TModel> {
    public ClassModelCollectionFactory(
        @NonNull final Class<TModel> modelClass,
        @NonNull final TransitionFactory<TModel, Cursor> modelFactory) {
        super(modelClass);
        Contracts.requireNonNull(modelClass, "modelClass == null");
        Contracts.requireNonNull(modelFactory, "modelFactory == null");

        _modelFactory = modelFactory;
    }

    @NonNull
    @Override
    public TModel[] createArray(@NonNull final Cursor argument) {
        Contracts.requireNonNull(argument, "argument == null");

        final TModel[] result;

        final TransitionFactory<TModel, Cursor> modelFactory = getModelFactory();

        argument.moveToFirst();
        final int count = argument.getCount();
        result = createArray(count);
        for (int i = 0; i < count; i++) {
            final TModel model = modelFactory.create(argument);

            result[i] = model;

            argument.moveToNext();
        }

        return result;
    }

    @NonNull
    @Override
    public Collection<TModel> createCollection(@NonNull final Cursor argument) {
        Contracts.requireNonNull(argument, "argument == null");

        final Collection<TModel> result;

        argument.moveToFirst();
        final int count = argument.getCount();
        result = createCollection(count);

        _fillCollection(result, argument);

        return result;
    }

    @NonNull
    @Override
    public List<TModel> createList(@NonNull final Cursor argument) {
        Contracts.requireNonNull(argument, "argument == null");

        final List<TModel> result;

        argument.moveToFirst();
        final int count = argument.getCount();
        result = createList(count);

        _fillCollection(result, argument);

        return result;
    }

    @NonNull
    @Override
    public Map<Long, TModel> createMap(@NonNull final Cursor argument) {
        Contracts.requireNonNull(argument, "argument == null");

        final Map<Long, TModel> result;

        argument.moveToFirst();
        final int count = argument.getCount();
        result = createMap(count);

        _fillMap(result, argument);

        return result;
    }

    @NonNull
    @Override
    public NavigableMap<Long, TModel> createNavigableMap(@NonNull final Cursor argument) {
        Contracts.requireNonNull(argument, "argument == null");

        final NavigableMap<Long, TModel> result;

        argument.moveToFirst();
        final int count = argument.getCount();
        result = createNavigableMap(count);

        _fillMap(result, argument);

        return result;
    }

    @NonNull
    @Override
    public SortedMap<Long, TModel> createSortedMap(@NonNull final Cursor argument) {
        Contracts.requireNonNull(argument, "argument == null");

        final SortedMap<Long, TModel> result;

        argument.moveToFirst();
        final int count = argument.getCount();
        result = createSortedMap(count);

        _fillMap(result, argument);

        return result;
    }

    @NonNull
    @Override
    public LongSparseArray<TModel> createSparseArray() {
        return createSparseArray(getDefaultCapacity());
    }

    @NonNull
    @Override
    public LongSparseArray<TModel> createSparseArray(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new LongSparseArray<>(capacity);
    }

    @NonNull
    public LongSparseArray<TModel> createSparseArray(@NonNull final Cursor argument) {
        Contracts.requireNonNull(argument, "argument == null");

        final LongSparseArray<TModel> result;

        final TransitionFactory<TModel, Cursor> modelFactory = getModelFactory();

        argument.moveToFirst();
        final int count = argument.getCount();
        result = createSparseArray(count);
        for (int i = 0; i < count; i++) {
            final TModel model = modelFactory.create(argument);

            result.put(model.getId(), model);

            argument.moveToNext();
        }

        return result;
    }

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final TransitionFactory<TModel, Cursor> _modelFactory;

    private void _fillCollection(
        @NonNull final Collection<TModel> collection, @NonNull final Cursor argument) {
        Contracts.requireNonNull(collection, "collection == null");
        Contracts.requireNonNull(argument, "argument == null");

        final TransitionFactory<TModel, Cursor> modelFactory = getModelFactory();

        argument.moveToFirst();
        final int count = argument.getCount();
        for (int i = 0; i < count; i++) {
            final TModel model = modelFactory.create(argument);

            collection.add(model);

            argument.moveToNext();
        }
    }

    private void _fillMap(@NonNull final Map<Long, TModel> map, @NonNull final Cursor argument) {
        Contracts.requireNonNull(map, "map == null");
        Contracts.requireNonNull(argument, "argument == null");

        final TransitionFactory<TModel, Cursor> modelFactory = getModelFactory();

        argument.moveToFirst();
        final int count = argument.getCount();
        for (int i = 0; i < count; i++) {
            final TModel model = modelFactory.create(argument);

            map.put(model.getId(), model);

            argument.moveToNext();
        }
    }
}
