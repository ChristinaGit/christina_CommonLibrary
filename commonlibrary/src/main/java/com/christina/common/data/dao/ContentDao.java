package com.christina.common.data.dao;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.data.model.Model;
import com.christina.common.data.model.factory.ContentModelCollectionFactory;
import com.christina.common.data.model.factory.ContentModelFactory;
import com.christina.common.data.model.factory.ModelContentExtractor;

public abstract class ContentDao<TModel extends Model> implements Dao<TModel> {
    protected ContentDao(@NonNull final String[] fullProjection) {
        Contracts.requireNonNull(fullProjection, "fullProjection == null");

        _fullProjection = fullProjection;
    }

    @NonNull
    protected final String[] getFullProjection() {
        return _fullProjection;
    }

    @NonNull
    protected DaoCollectionResult<TModel> createDaoCollectionResult(@Nullable final Cursor cursor) {
        return new DaoCursorCollectionResult<>(cursor, getModelCollectionFactory());
    }

    @NonNull
    protected abstract ContentModelCollectionFactory<TModel> getModelCollectionFactory();

    @NonNull
    protected abstract ModelContentExtractor<TModel> getModelContentExtractor();

    @NonNull
    protected abstract ContentModelFactory<TModel> getModelFactory();

    @NonNull
    private final String[] _fullProjection;
}
