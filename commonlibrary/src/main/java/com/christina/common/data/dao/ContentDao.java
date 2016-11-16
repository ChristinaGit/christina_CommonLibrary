package com.christina.common.data.dao;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.data.dao.factory.ModelCollectionFactory;
import com.christina.common.data.dao.factory.ModelContentExtractor;
import com.christina.common.data.dao.result.CollectionResult;
import com.christina.common.data.dao.result.CursorCollectionResult;
import com.christina.common.data.model.Model;
import com.christina.common.data.projection.Projection;
import com.christina.common.pattern.factory.TransitionFactory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public abstract class ContentDao<TModel extends Model> implements Dao<TModel> {
    protected ContentDao(@NonNull final Projection fullProjection,
        @NonNull final TransitionFactory<TModel, Cursor> modelFactory,
        @NonNull final ModelCollectionFactory<TModel> modelCollectionFactory,
        @NonNull final ModelContentExtractor<TModel> modelContentExtractor) {
        Contracts.requireNonNull(fullProjection, "fullProjection == null");
        Contracts.requireNonNull(modelFactory, "modelFactory == null");
        Contracts.requireNonNull(modelCollectionFactory, "modelCollectionFactory == null");
        Contracts.requireNonNull(modelContentExtractor, "modelContentExtractor == null");

        _fullProjection = fullProjection;
        _modelFactory = modelFactory;
        _modelCollectionFactory = modelCollectionFactory;
        _modelContentExtractor = modelContentExtractor;
    }

    @NonNull
    protected CollectionResult<TModel> createCollectionResult(@Nullable final Cursor cursor) {
        return new CursorCollectionResult<>(cursor, getModelFactory(), getModelCollectionFactory());
    }

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final Projection _fullProjection;

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final ModelCollectionFactory<TModel> _modelCollectionFactory;

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final ModelContentExtractor<TModel> _modelContentExtractor;

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final TransitionFactory<TModel, Cursor> _modelFactory;
}