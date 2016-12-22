package com.christina.common.data.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.data.dao.factory.ModelCollectionFactory;
import com.christina.common.data.dao.factory.ModelContentExtractor;
import com.christina.common.data.dao.factory.ModelFactory;
import com.christina.common.data.dao.result.CollectionResult;
import com.christina.common.data.dao.selection.SqlDataSelection;
import com.christina.common.data.model.Model;
import com.christina.common.data.projection.Projection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

@Accessors(prefix = "_")
public abstract class ContentProviderDao<TModel extends Model> extends ContentDao<TModel>
    implements SqlDao<TModel> {
    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public int delete(final long id) {
        return delete(getModelUri(id));
    }

    @Nullable
    @Override
    public TModel get(final long id) {
        return selectSingle(getModelUri(id));
    }

    @NonNull
    @Override
    public CollectionResult<TModel> getAll() {
        return select(getModelUri());
    }

    @Override
    public long insert(@NonNull final TModel model) {
        Contracts.requireNonNull(model, "model == null");

        final val modelUri = insert(getModelUri(), getModelContentExtractor().extract(model));

        long id = Model.NO_ID;
        if (modelUri != null) {
            final long extractedId = extractId(modelUri);
            if (extractedId >= 0) {
                id = extractedId;
            }
        }

        model.setId(id);

        return model.getId();
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public int update(@NonNull final TModel model) {
        Contracts.requireNonNull(model, "model == null");
        DaoContracts.requireId(model);

        return update(getModelUri(model.getId()), getModelContentExtractor().extract(model));
    }

    @Override
    public int delete(@NonNull final SqlDataSelection sqlDataSelection) {
        Contracts.requireNonNull(sqlDataSelection, "sqlDataSelection == null");

        return delete(getModelUri(),
                      sqlDataSelection.getWhereClause(),
                      sqlDataSelection.getWhereArguments());
    }

    @Nullable
    @Override
    public TModel get(@NonNull final SqlDataSelection sqlDataSelection) {
        Contracts.requireNonNull(sqlDataSelection, "sqlDataSelection == null");

        return selectSingle(getModelUri(),
                            sqlDataSelection.getWhereClause(),
                            sqlDataSelection.getWhereArguments());
    }

    @NonNull
    @Override
    public CollectionResult<TModel> getAll(@NonNull final SqlDataSelection sqlDataSelection) {
        Contracts.requireNonNull(sqlDataSelection, "sqlDataSelection == null");

        return select(getModelUri(),
                      sqlDataSelection.getWhereClause(),
                      sqlDataSelection.getWhereArguments());
    }

    protected ContentProviderDao(
        @NonNull final ContentResolver contentResolver,
        @NonNull final Projection fullProjection,
        @NonNull final ModelFactory<TModel> modelFactory,
        @NonNull final ModelCollectionFactory<TModel> modelCollectionFactory,
        @NonNull final ModelContentExtractor<TModel> modelContentExtractor) {
        super(Contracts.requireNonNull(fullProjection, "fullProjection == null"),
              Contracts.requireNonNull(modelFactory, "modelFactory == null"),
              Contracts.requireNonNull(modelCollectionFactory, "modelCollectionFactory == null"),
              Contracts.requireNonNull(modelContentExtractor, "modelContentExtractor == null"));
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
    protected final int delete(
        @NonNull final Uri url,
        @Nullable final String where,
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
    protected final Cursor query(
        @NonNull final Uri uri,
        @Nullable final String[] projection,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String sortOrder) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Nullable
    protected final Cursor query(
        @NonNull final Uri uri,
        @Nullable final String[] projection,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().query(uri, projection, selection, selectionArgs, null);
    }

    @Nullable
    protected final Cursor query(
        @NonNull final Uri uri,
        @Nullable final String[] projection,
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
    protected final CollectionResult<TModel> select(
        @NonNull final Uri uri,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String sortOrder) {
        Contracts.requireNonNull(uri, "uri == null");

        return createCollectionResult(query(uri,
                                            getFullProjection().getColumns(),
                                            selection,
                                            selectionArgs,
                                            sortOrder));
    }

    @NonNull
    protected final CollectionResult<TModel> select(
        @NonNull final Uri uri,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(uri, "uri == null");

        return createCollectionResult(query(uri,
                                            getFullProjection().getColumns(),
                                            selection,
                                            selectionArgs));
    }

    @NonNull
    protected final CollectionResult<TModel> select(
        @NonNull final Uri uri, @Nullable final String selection) {
        Contracts.requireNonNull(uri, "uri == null");

        return createCollectionResult(query(uri, getFullProjection().getColumns(), selection));
    }

    @NonNull
    protected final CollectionResult<TModel> select(@NonNull final Uri uri) {
        Contracts.requireNonNull(uri, "uri == null");

        return createCollectionResult(query(uri, getFullProjection().getColumns()));
    }

    @Nullable
    protected final TModel selectSingle(
        @NonNull final Uri uri,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String sortOrder) {
        Contracts.requireNonNull(uri, "uri == null");

        TModel result = null;

        try (final val cursor = query(uri,
                                      getFullProjection().getColumns(),
                                      selection,
                                      selectionArgs,
                                      sortOrder)) {
            if (cursor != null && cursor.moveToFirst()) {
                result = getModelFactory().create(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final TModel selectSingle(
        @NonNull final Uri uri,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(uri, "uri == null");

        TModel result = null;

        try (final val cursor = query(uri,
                                      getFullProjection().getColumns(),
                                      selection,
                                      selectionArgs)) {
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

        try (final val cursor = query(uri, getFullProjection().getColumns(), selection)) {
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

        try (final val cursor = query(uri, getFullProjection().getColumns())) {
            if (cursor != null && cursor.moveToFirst()) {
                result = getModelFactory().create(cursor);
            }
        }

        return result;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(
        @NonNull final Uri uri,
        @Nullable final ContentValues values,
        @Nullable final String where,
        @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(uri, "uri == null");

        return getContentResolver().update(uri, values, where, selectionArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(
        @NonNull final Uri uri,
        @Nullable final ContentValues values,
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

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final ContentResolver _contentResolver;
}
