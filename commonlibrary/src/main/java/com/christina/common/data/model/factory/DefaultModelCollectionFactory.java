package com.christina.common.data.model.factory;

import android.database.Cursor;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;

import com.christina.common.contract.Contracts;
import com.christina.common.data.model.Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class DefaultModelCollectionFactory<TModel extends Model>
    implements ContentModelCollectionFactory<TModel> {

    @NonNull
    @Override
    public TModel[] createArray(@NonNull final Cursor cursor) {
        Contracts.requireNonNull(cursor, "cursor == null");

        final TModel[] result;

        final ContentModelFactory<TModel> modelFactory = getModelFactory();

        cursor.moveToFirst();
        final int count = cursor.getCount();
        result = createArray(count);
        for (int i = 0; i < count; i++) {
            final TModel model = modelFactory.create(cursor);

            result[i] = model;

            cursor.moveToNext();
        }

        return result;
    }

    @NonNull
    @Override
    public List<TModel> createList(@NonNull final Cursor cursor) {
        Contracts.requireNonNull(cursor, "cursor == null");

        final List<TModel> result;

        final ContentModelFactory<TModel> modelFactory = getModelFactory();

        cursor.moveToFirst();
        final int count = cursor.getCount();
        result = createList(count);
        for (int i = 0; i < count; i++) {
            final TModel model = modelFactory.create(cursor);

            result.add(model);

            cursor.moveToNext();
        }

        return result;
    }

    @NonNull
    @Override
    public Map<Long, TModel> createMap(@NonNull final Cursor cursor) {
        Contracts.requireNonNull(cursor, "cursor == null");

        final Map<Long, TModel> result;

        final ContentModelFactory<TModel> modelFactory = getModelFactory();

        cursor.moveToFirst();
        final int count = cursor.getCount();
        result = createMap(count);
        for (int i = 0; i < count; i++) {
            final TModel model = modelFactory.create(cursor);

            result.put(model.getId(), model);

            cursor.moveToNext();
        }

        return result;
    }

    @NonNull
    @Override
    public LongSparseArray<TModel> createPrimitiveMap(@NonNull final Cursor cursor) {
        Contracts.requireNonNull(cursor, "cursor == null");

        final LongSparseArray<TModel> result;

        final ContentModelFactory<TModel> modelFactory = getModelFactory();

        cursor.moveToFirst();
        final int count = cursor.getCount();
        result = createPrimitiveMap(count);
        for (int i = 0; i < count; i++) {
            final TModel model = modelFactory.create(cursor);

            result.put(model.getId(), model);

            cursor.moveToNext();
        }

        return result;
    }

    @NonNull
    @Override
    public abstract TModel[] createArray(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int size);

    @NonNull
    @Override
    public List<TModel> createList(@IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new ArrayList<>(capacity);
    }

    @NonNull
    @Override
    public List<TModel> createList() {
        return createList(getDefaultCapacity());
    }

    @NonNull
    @Override
    public Map<Long, TModel> createMap() {
        return createMap(getDefaultCapacity());
    }

    @NonNull
    @Override
    public Map<Long, TModel> createMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new LinkedHashMap<>(capacity);
    }

    @NonNull
    @Override
    public LongSparseArray<TModel> createPrimitiveMap() {
        return createPrimitiveMap(getDefaultCapacity());
    }

    @NonNull
    @Override
    public LongSparseArray<TModel> createPrimitiveMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new LongSparseArray<>(capacity);
    }

    protected DefaultModelCollectionFactory(
        @NonNull final ContentModelFactory<TModel> modelFactory) {
        Contracts.requireNonNull(modelFactory, "modelFactory == null");

        _modelFactory = modelFactory;
    }

    @NonNull
    protected final ContentModelFactory<TModel> getModelFactory() {
        return _modelFactory;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected int getDefaultCapacity() {
        return 0;
    }

    @NonNull
    private final ContentModelFactory<TModel> _modelFactory;
}
