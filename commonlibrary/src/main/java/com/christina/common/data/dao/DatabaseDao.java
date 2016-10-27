package com.christina.common.data.dao;

import android.database.Cursor;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.data.database.Database;
import com.christina.common.data.model.Model;

import org.apache.commons.lang3.ArrayUtils;

public abstract class DatabaseDao<TModel extends Model> extends ContentDao<TModel> {
    @Override
    @Nullable
    public TModel create() {
        TModel model = getModelFactory().create();

        final long id =
            getDatabase().insert(getTableName(), getModelContentExtractor().extract(model));

        if (id >= 0) {
            model.setId(id);
        } else {
            model = null;
        }

        return model;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public int delete(final long id) {
        return delete(getIdColumnName() + "=?", new String[]{String.valueOf(id)});
    }

    @NonNull
    @Override
    public DaoCollectionResult<TModel> getAll() {
        return select();
    }

    @Override
    @Nullable
    public TModel get(final long id) {
        return selectSingle(getIdColumnName() + "=?", new String[]{String.valueOf(id)});
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public int update(@NonNull final TModel model) {
        Contracts.requireNonNull(model, "model == null");
        DaoContracts.requireId(model);

        return getDatabase().update(getTableName(), getModelContentExtractor().extract(model),
            getIdColumnName() + "=?", new String[]{String.valueOf(model.getId())});
    }

    protected DatabaseDao(final @NonNull Database database, final @NonNull String idColumnName,
        final @NonNull String[] fullProjection, final @NonNull String tableName) {
        super(fullProjection);

        Contracts.requireNonNull(database, "database == null");
        Contracts.requireNonNull(idColumnName, "idColumnName == null");
        Contracts.requireNonNull(tableName, "tableName == null");

        _database = database;
        _idColumnName = idColumnName;
        _tableName = tableName;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int delete(@Nullable final String whereClause,
        @Nullable final String[] whereArgs) {
        return getDatabase().delete(getTableName(), whereClause, whereArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int delete(@Nullable final String whereClause) {
        return getDatabase().delete(getTableName(), whereClause);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int delete() {
        return getDatabase().delete(getTableName());
    }

    @NonNull
    protected final Database getDatabase() {
        return _database;
    }

    @NonNull
    protected final String getIdColumnName() {
        return _idColumnName;
    }

    @NonNull
    protected final String getTableName() {
        return _tableName;
    }

    @Nullable
    protected final Cursor query(final boolean distinct) {
        return getDatabase().query(distinct, getTableName());
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String groupBy, @Nullable final String having,
        @Nullable final String orderBy, @Nullable final String limit) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs,
            groupBy, having, orderBy, limit);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String groupBy, @Nullable final String having,
        @Nullable final String orderBy) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs,
            groupBy, having, orderBy);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String groupBy, @Nullable final String having) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs,
            groupBy, having);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String groupBy) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs,
            groupBy);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
        @Nullable final String selection, @Nullable final String[] selectionArgs) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
        @Nullable final String selection) {
        return getDatabase().query(distinct, getTableName(), columns, selection);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns, @Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String groupBy,
        @Nullable final String having, @Nullable final String orderBy,
        @Nullable final String limit) {
        return getDatabase().query(getTableName(), columns, selection, selectionArgs, groupBy,
            having, orderBy, limit);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns, @Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String groupBy,
        @Nullable final String having, @Nullable final String orderBy) {
        return getDatabase().query(getTableName(), columns, selection, selectionArgs, groupBy,
            having, orderBy);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns, @Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String groupBy,
        @Nullable final String having) {
        return getDatabase().query(getTableName(), columns, selection, selectionArgs, groupBy,
            having);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns, @Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String groupBy) {
        return getDatabase().query(getTableName(), columns, selection, selectionArgs, groupBy);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns, @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        return getDatabase().query(getTableName(), columns, selection, selectionArgs);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns,
        @Nullable final String selection) {
        return getDatabase().query(getTableName(), columns, selection);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns) {
        return getDatabase().query(distinct, getTableName(), columns);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns) {
        return getDatabase().query(getTableName(), columns);
    }

    @Nullable
    protected final Cursor query() {
        return getDatabase().query(getTableName());
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select() {
        return createDaoCollectionResult(query());
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(final boolean distinct,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String orderBy, @Nullable final String limit) {
        return createDaoCollectionResult(
            query(distinct, getFullProjection(), selection, selectionArgs, orderBy, limit));
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(final boolean distinct,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String orderBy) {
        return createDaoCollectionResult(
            query(distinct, getFullProjection(), selection, selectionArgs, orderBy));
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(final boolean distinct,
        @Nullable final String selection, @Nullable final String[] selectionArgs) {
        return createDaoCollectionResult(
            query(distinct, getFullProjection(), selection, selectionArgs));
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(final boolean distinct,
        @Nullable final String selection) {
        return createDaoCollectionResult(query(distinct, getFullProjection(), selection));
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(@Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String orderBy,
        @Nullable final String limit) {
        return createDaoCollectionResult(
            query(getFullProjection(), selection, selectionArgs, orderBy, limit));
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(@Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String orderBy) {
        return createDaoCollectionResult(
            query(getFullProjection(), selection, selectionArgs, orderBy));
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(@Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        return createDaoCollectionResult(query(getFullProjection(), selection, selectionArgs));
    }

    @NonNull
    protected final DaoCollectionResult<TModel> select(@Nullable final String selection) {
        return createDaoCollectionResult(query(getFullProjection(), selection));
    }

    @Nullable
    protected final TModel selectSingle(@Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        TModel result = null;

        try (final Cursor cursor = query(getFullProjection(), selection, selectionArgs)) {
            if (cursor != null) {
                result = getModelFactory().create(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final TModel selectSingle(@Nullable final String selection) {
        TModel result = null;

        try (final Cursor cursor = query(getFullProjection(), selection)) {
            if (cursor != null) {
                result = getModelFactory().create(cursor);
            }
        }

        return result;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(@NonNull final TModel model, @Nullable String whereClause,
        @Nullable String[] whereArgs) {
        DaoContracts.requireId(model);

        whereClause += " AND " + getIdColumnName() + "=?";
        whereArgs = ArrayUtils.add(whereArgs, String.valueOf(model.getId()));

        return getDatabase().update(getTableName(), getModelContentExtractor().extract(model),
            whereClause, whereArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(@NonNull final TModel model, @Nullable String whereClause) {
        DaoContracts.requireId(model);

        whereClause += " AND " + getIdColumnName() + "=?";

        return getDatabase().update(getTableName(), getModelContentExtractor().extract(model),
            whereClause, new String[]{String.valueOf(model.getId())});
    }

    @NonNull
    private final Database _database;

    @NonNull
    private final String _idColumnName;

    @NonNull
    private final String _tableName;
}
