package com.christina.common.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;
import com.christina.common.data.model.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class ContentDao<TModel extends Model>
    implements Dao<TModel> {
    protected ContentDao(@NonNull final String[] fullProjection) {
        Contracts.requireNonNull(fullProjection, "fullProjection == null");

        _fullProjection = fullProjection;
    }

    @NonNull
    protected abstract TModel createModel();

    @NonNull
    protected abstract TModel createModel(@NonNull Cursor cursor);

    @NonNull
    protected abstract ContentValues getContentValues(@NonNull TModel model);

    @NonNull
    protected abstract TModel[] createModelArray(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int size);

    @NonNull
    protected ContentValues[] getContentValues(@NonNull TModel[] models) {
        Contracts.requireNonNull(models, "models == null");

        final int modelsCount = models.length;
        final ContentValues[] contentValues = new ContentValues[modelsCount];
        for (int i = 0; i < modelsCount; i++) {
            contentValues[i] = getContentValues(models[i]);
        }

        return contentValues;
    }

    @NonNull
    protected List<TModel> createModelList(@NonNull Cursor cursor) {
        Contracts.requireNonNull(cursor, "cursor == null");

        final int entryCount = cursor.getCount();
        final List<TModel> models = createModelList(entryCount);
        for (int i = 0; i < entryCount; i++) {
            cursor.moveToPosition(i);
            models.set(i, createModel(cursor));
        }

        return models;
    }

    @NonNull
    protected List<TModel> createModelList(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity) {
        Contracts.requireInRange(capacity, 0, Integer.MAX_VALUE);

        return new ArrayList<>(capacity);
    }

    @NonNull
    protected final String[] getFullProjection() {
        return _fullProjection;
    }

    @NonNull
    private String[] _fullProjection;
}
