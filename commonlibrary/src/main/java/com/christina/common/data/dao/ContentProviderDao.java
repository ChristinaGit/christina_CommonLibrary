package com.christina.common.data.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.data.model.Model;

public abstract class ContentProviderDao<TModel extends Model> extends ContentDao<TModel> {
    @Nullable
    @Override
    public TModel create() {
        TModel model = getModelFactory().create();

        final Uri modelUri = insert(getModelUri(), getModelContentExtractor().extract(model));
        final long id;

        if (modelUri != null) {
            id = extractId(modelUri);
            if (id >= 0) {
                model.setId(id);
            } else {
                model = null;
            }
        }

        return model;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public int delete(final long id) {
        return delete(getModelUri(id));
    }

    @NonNull
    @Override
    public DaoCollectionResult<TModel> get() {
        return select(getModelUri());
    }

    @Nullable
    @Override
    public TModel get(final long id) {
        return selectSingle(getModelUri(id));
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public int update(@NonNull final TModel model) {
        Contracts.requireNonNull(model, "model == null");
        DaoContracts.requireId(model);

        return update(getModelUri(model.getId()), getModelContentExtractor().extract(model));
    }

    protected ContentProviderDao(final @NonNull ContentResolver contentResolver,
        final @NonNull String[] fullProjection) {
        super(fullProjection);

        Contracts.requireNonNull(contentResolver, "contentResolver == null");

        _contentResolver = contentResolver;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int bulkInsert(@NonNull final Uri url, @NonNull final ContentValues[] values) {
        Contracts.requireNonNull(url, "url == null");
        Contracts.requireNonNull(values, "values == null");

        return getContentResolver().bulkInsert(url, values);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int delete(@NonNull final Uri url, @Nullable final String where,
        @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(url, "url == null");

        return getContentResolver().delete(url, where, selectionArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int delete(@NonNull final Uri url, @Nullable final String where) {
        Contracts.requireNonNull(url, "url == null");

        return getContentResolver().delete(url, where, null);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int delete(@NonNull final Uri url) {
        Contracts.requireNonNull(url, "url == null");

        return getContentResolver().delete(url, null, null);
    }

    @NonNull
    protected final ContentResolver getContentResolver() {
        return _contentResolver;
    }

    @Nullable
    protected final Uri insert(@NonNull final Uri url, @Nullable final ContentValues values) {
        Contracts.requireNonNull(url, "url == null");

        return getContentResolver().insert(url, values);
    }

    @Nullable
    protected final Uri insert(@NonNull final Uri url) {
        Contracts.requireNonNull(url, "url == null");

        return getContentResolver().insert(url, null);
    }

    @Nullable
    protected final Cursor query(@NonNull final Uri uri, @Nullable final String[] projection,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String sortOrder) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Nullable
    protected final Cursor query(@NonNull final Uri uri, @Nullable final String[] projection,
        @Nullable final String selection, @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().query(uri, projection, selection, selectionArgs, null);
    }

    @Nullable
    protected final Cursor query(@NonNull final Uri uri, @Nullable final String[] projection,
        @Nullable final String selection) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().query(uri, projection, selection, null, null);
    }

    @Nullable
    protected final Cursor query(@NonNull final Uri uri, @Nullable final String[] projection) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().query(uri, projection, null, null, null);
    }

    @Nullable
    protected final Cursor query(@NonNull final Uri uri) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().query(uri, null, null, null, null);
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(@NonNull final Uri uri,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String sortOrder) {
        Contracts.requireNonNull(uri, "uri == null");

        return createDaoCollectionResult(
            query(uri, getFullProjection(), selection, selectionArgs, sortOrder));
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(@NonNull final Uri uri,
        @Nullable final String selection, @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(uri, "uri == null");

        return createDaoCollectionResult(query(uri, getFullProjection(), selection, selectionArgs));
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(@NonNull final Uri uri,
        @Nullable final String selection) {
        Contracts.requireNonNull(uri, "uri == null");

        return createDaoCollectionResult(query(uri, getFullProjection(), selection));
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(@NonNull final Uri uri) {
        Contracts.requireNonNull(uri, "uri == null");

        return createDaoCollectionResult(query(uri, getFullProjection()));
    }

    @Nullable
    protected final TModel selectSingle(@NonNull final Uri uri, @Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String sortOrder) {
        Contracts.requireNonNull(uri, "uri == null");

        TModel result = null;

        try (final Cursor cursor = query(uri, getFullProjection(), selection, selectionArgs,
            sortOrder)) {
            if (cursor != null && cursor.moveToFirst()) {
                result = getModelFactory().create(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final TModel selectSingle(@NonNull final Uri uri, @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(uri, "uri == null");

        TModel result = null;

        try (final Cursor cursor = query(uri, getFullProjection(), selection, selectionArgs)) {
            if (cursor != null && cursor.moveToFirst()) {
                result = getModelFactory().create(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final TModel selectSingle(@NonNull final Uri uri, @Nullable final String selection) {
        Contracts.requireNonNull(uri, "uri == null");

        TModel result = null;

        try (final Cursor cursor = query(uri, getFullProjection(), selection)) {
            if (cursor != null && cursor.moveToFirst()) {
                result = getModelFactory().create(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final TModel selectSingle(@NonNull final Uri uri) {
        Contracts.requireNonNull(uri, "uri == null");

        TModel result = null;

        try (final Cursor cursor = query(uri, getFullProjection())) {
            if (cursor != null && cursor.moveToFirst()) {
                result = getModelFactory().create(cursor);
            }
        }

        return result;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(@NonNull final Uri uri, @Nullable final ContentValues values,
        @Nullable final String where, @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().update(uri, values, where, selectionArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(@NonNull final Uri uri, @Nullable final ContentValues values,
        @Nullable final String where) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().update(uri, values, where, null);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(@NonNull final Uri uri, @Nullable final ContentValues values) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().update(uri, values, null, null);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(@NonNull final Uri uri) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().update(uri, null, null, null);
    }

    protected abstract long extractId(@NonNull Uri modelUri);

    @NonNull
    protected abstract Uri getModelUri();

    @NonNull
    protected abstract Uri getModelUri(long id);

    @NonNull
    private final ContentResolver _contentResolver;
}
