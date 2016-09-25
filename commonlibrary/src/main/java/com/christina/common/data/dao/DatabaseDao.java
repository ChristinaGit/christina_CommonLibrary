package com.christina.common.data.dao;

import android.database.Cursor;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.data.QueryUtils;
import com.christina.common.data.database.Database;
import com.christina.common.data.model.Model;

import java.util.List;

public abstract class DatabaseDao<TModel extends Model> extends ContentDao<TModel> {
    @Override
    @Nullable
    public TModel create() {
        TModel model = createModel();

        final long id = getDatabase().insert(getTableName(), getContentValues(model));

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
        return delete(QueryUtils.whereEquals(getIdColumnName(), String.valueOf(id)));
    }

    @Override
    @Nullable
    public List<TModel> get() {
        return select();
    }

    @Override
    @Nullable
    public TModel get(final long id) {
        return selectSingle(whereIdEquals(id));
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public int update(@NonNull final TModel model) {
        Contracts.requireNonNull(model, "model == null");
        DaoContracts.requireId(model);

        return getDatabase().update(getTableName(), getContentValues(model),
                                    whereIdEquals(model.getId()));
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

    @NonNull
    protected final String appendWhereIdEquals(@Nullable final String where, final long id) {
        return QueryUtils.appendWhereEquals(where, getIdColumnName(), String.valueOf(id));
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int delete(@Nullable final String whereClause, @Nullable final String[] whereArgs) {
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
                                 @Nullable final String selection,
                                 @Nullable final String[] selectionArgs,
                                 @Nullable final String groupBy, @Nullable final String having,
                                 @Nullable final String orderBy, @Nullable final String limit) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs,
                                   groupBy, having, orderBy, limit);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
                                 @Nullable final String selection,
                                 @Nullable final String[] selectionArgs,
                                 @Nullable final String groupBy, @Nullable final String having,
                                 @Nullable final String orderBy) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs,
                                   groupBy, having, orderBy);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
                                 @Nullable final String selection,
                                 @Nullable final String[] selectionArgs,
                                 @Nullable final String groupBy, @Nullable final String having) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs,
                                   groupBy, having);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
                                 @Nullable final String selection,
                                 @Nullable final String[] selectionArgs,
                                 @Nullable final String groupBy) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs,
                                   groupBy);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
                                 @Nullable final String selection,
                                 @Nullable final String[] selectionArgs) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs);
    }

    @Nullable
    protected final Cursor query(final boolean distinct, @Nullable final String[] columns,
                                 @Nullable final String selection) {
        return getDatabase().query(distinct, getTableName(), columns, selection);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns, @Nullable final String selection,
                                 @Nullable final String[] selectionArgs,
                                 @Nullable final String groupBy, @Nullable final String having,
                                 @Nullable final String orderBy, @Nullable final String limit) {
        return getDatabase().query(getTableName(), columns, selection, selectionArgs, groupBy,
                                   having, orderBy, limit);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns, @Nullable final String selection,
                                 @Nullable final String[] selectionArgs,
                                 @Nullable final String groupBy, @Nullable final String having,
                                 @Nullable final String orderBy) {
        return getDatabase().query(getTableName(), columns, selection, selectionArgs, groupBy,
                                   having, orderBy);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns, @Nullable final String selection,
                                 @Nullable final String[] selectionArgs,
                                 @Nullable final String groupBy, @Nullable final String having) {
        return getDatabase().query(getTableName(), columns, selection, selectionArgs, groupBy,
                                   having);
    }

    @Nullable
    protected final Cursor query(@Nullable final String[] columns, @Nullable final String selection,
                                 @Nullable final String[] selectionArgs,
                                 @Nullable final String groupBy) {
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

    @Nullable
    protected final List<TModel> select() {
        List<TModel> result = null;

        try (final Cursor cursor = query()) {
            if (cursor != null) {
                result = createModelList(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final List<TModel> select(final boolean distinct, @Nullable final String selection,
                                        @Nullable final String[] selectionArgs,
                                        @Nullable final String orderBy,
                                        @Nullable final String limit) {
        List<TModel> result = null;

        try (final Cursor cursor = query(distinct, getFullProjection(), selection, selectionArgs,
                                         orderBy, limit)) {
            if (cursor != null) {
                result = createModelList(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final List<TModel> select(final boolean distinct, @Nullable final String selection,
                                        @Nullable final String[] selectionArgs,
                                        @Nullable final String orderBy) {
        List<TModel> result = null;

        try (final Cursor cursor = query(distinct, getFullProjection(), selection, selectionArgs,
                                         orderBy)) {
            if (cursor != null) {
                result = createModelList(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final List<TModel> select(final boolean distinct, @Nullable final String selection,
                                        @Nullable final String[] selectionArgs) {
        List<TModel> result = null;

        try (final Cursor cursor = query(distinct, getFullProjection(), selection, selectionArgs)) {
            if (cursor != null) {
                result = createModelList(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final List<TModel> select(final boolean distinct, @Nullable final String selection) {
        List<TModel> result = null;

        try (final Cursor cursor = query(distinct, getFullProjection(), selection)) {
            if (cursor != null) {
                result = createModelList(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final List<TModel> select(@Nullable final String selection,
                                        @Nullable final String[] selectionArgs,
                                        @Nullable final String orderBy,
                                        @Nullable final String limit) {
        List<TModel> result = null;

        try (final Cursor cursor = query(getFullProjection(), selection, selectionArgs, orderBy,
                                         limit)) {
            if (cursor != null) {
                result = createModelList(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final List<TModel> select(@Nullable final String selection,
                                        @Nullable final String[] selectionArgs,
                                        @Nullable final String orderBy) {
        List<TModel> result = null;

        try (final Cursor cursor = query(getFullProjection(), selection, selectionArgs, orderBy)) {
            if (cursor != null) {
                result = createModelList(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final List<TModel> select(@Nullable final String selection,
                                        @Nullable final String[] selectionArgs) {
        List<TModel> result = null;

        try (final Cursor cursor = query(getFullProjection(), selection, selectionArgs)) {
            if (cursor != null) {
                result = createModelList(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final List<TModel> select(@Nullable final String selection) {
        List<TModel> result = null;

        try (final Cursor cursor = query(getFullProjection(), selection)) {
            if (cursor != null) {
                result = createModelList(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final TModel selectSingle(@Nullable final String selection,
                                        @Nullable final String[] selectionArgs) {
        TModel result = null;

        try (final Cursor cursor = query(getFullProjection(), selection, selectionArgs)) {
            if (cursor != null) {
                result = createModel(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final TModel selectSingle(@Nullable final String selection) {
        TModel result = null;

        try (final Cursor cursor = query(getFullProjection(), selection)) {
            if (cursor != null) {
                result = createModel(cursor);
            }
        }

        return result;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(@NonNull final TModel model, @Nullable final String whereClause,
                               @Nullable final String[] whereArgs) {
        DaoContracts.requireId(model);

        return getDatabase().update(getTableName(), getContentValues(model),
                                    appendWhereIdEquals(whereClause, model.getId()), whereArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(@NonNull final TModel model, @Nullable final String whereClause) {
        DaoContracts.requireId(model);

        return getDatabase().update(getTableName(), getContentValues(model),
                                    appendWhereIdEquals(whereClause, model.getId()));
    }

    @NonNull
    protected final String whereIdEquals(final long id) {
        return QueryUtils.whereEquals(getIdColumnName(), String.valueOf(id));
    }

    @NonNull
    private final Database _database;

    @NonNull
    private final String _idColumnName;

    @NonNull
    private final String _tableName;
}
