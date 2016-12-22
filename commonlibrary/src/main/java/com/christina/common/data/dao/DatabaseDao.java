package com.christina.common.data.dao;

import android.database.Cursor;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.data.dao.factory.ModelCollectionFactory;
import com.christina.common.data.dao.factory.ModelContentExtractor;
import com.christina.common.data.dao.factory.ModelFactory;
import com.christina.common.data.dao.result.CollectionResult;
import com.christina.common.data.dao.selection.SqlDataSelection;
import com.christina.common.data.database.Database;
import com.christina.common.data.model.Model;
import com.christina.common.data.projection.Projection;

import org.apache.commons.lang3.ArrayUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

@Accessors(prefix = "_")
public abstract class DatabaseDao<TModel extends Model> extends ContentDao<TModel>
    implements SqlDao<TModel> {
    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public int delete(final long id) {
        return delete(getIdColumnName() + "=?", new String[]{String.valueOf(id)});
    }

    @Override
    @Nullable
    public TModel get(final long id) {
        return selectSingle(getIdColumnName() + "=?", new String[]{String.valueOf(id)});
    }

    @NonNull
    @Override
    public CollectionResult<TModel> getAll() {
        return select();
    }

    @Override
    public long insert(@NonNull final TModel model) {
        Contracts.requireNonNull(model, "model == null");

        long id = getDatabase().insert(getTableName(), getModelContentExtractor().extract(model));
        if (id < 0) {
            id = Model.NO_ID;
        }

        model.setId(id);

        return model.getId();
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public int update(@NonNull final TModel model) {
        Contracts.requireNonNull(model, "model == null");
        DaoContracts.requireId(model);

        return getDatabase().update(getTableName(),
                                    getModelContentExtractor().extract(model),
                                    getIdColumnName() + "=?",
                                    new String[]{String.valueOf(model.getId())});
    }

    @Override
    public int delete(
        @NonNull final SqlDataSelection sqlDataSelection) {
        Contracts.requireNonNull(sqlDataSelection, "sqlDataSelection == null");

        return delete(sqlDataSelection.getWhereClause(), sqlDataSelection.getWhereArguments());
    }

    @Nullable
    @Override
    public TModel get(
        @NonNull final SqlDataSelection sqlDataSelection) {
        Contracts.requireNonNull(sqlDataSelection, "sqlDataSelection == null");

        return selectSingle(sqlDataSelection.getWhereClause(),
                            sqlDataSelection.getWhereArguments());
    }

    @NonNull
    @Override
    public CollectionResult<TModel> getAll(
        @NonNull final SqlDataSelection sqlDataSelection) {
        Contracts.requireNonNull(sqlDataSelection, "sqlDataSelection == null");

        return select(sqlDataSelection.getWhereClause(), sqlDataSelection.getWhereArguments());
    }

    protected DatabaseDao(
        @NonNull final Projection fullProjection,
        @NonNull final Database database,
        @NonNull final String idColumnName,
        @NonNull final String tableName,
        @NonNull final ModelFactory<TModel> modelFactory,
        @NonNull final ModelCollectionFactory<TModel> modelCollectionFactory,
        @NonNull final ModelContentExtractor<TModel> modelContentExtractor) {
        super(Contracts.requireNonNull(fullProjection, "fullProjection == null"),
              Contracts.requireNonNull(modelFactory, "modelFactory == null"),
              Contracts.requireNonNull(modelCollectionFactory, "modelCollectionFactory == null"),
              Contracts.requireNonNull(modelContentExtractor, "modelContentExtractor == null"));
        Contracts.requireNonNull(database, "database == null");
        Contracts.requireNonNull(idColumnName, "idColumnName == null");
        Contracts.requireNonNull(tableName, "tableName == null");

        _database = database;
        _idColumnName = idColumnName;
        _tableName = tableName;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int delete(
        @Nullable final String whereClause, @Nullable final String[] whereArgs) {
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

    @Nullable
    protected final Cursor query(final boolean distinct) {
        return getDatabase().query(distinct, getTableName());
    }

    @Nullable
    protected final Cursor query(
        final boolean distinct,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having,
        @Nullable final String orderBy,
        @Nullable final String limit) {
        return getDatabase().query(distinct,
                                   getTableName(),
                                   columns,
                                   selection,
                                   selectionArgs,
                                   groupBy,
                                   having,
                                   orderBy,
                                   limit);
    }

    @Nullable
    protected final Cursor query(
        final boolean distinct,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having,
        @Nullable final String orderBy) {
        return getDatabase().query(distinct,
                                   getTableName(),
                                   columns,
                                   selection,
                                   selectionArgs,
                                   groupBy,
                                   having,
                                   orderBy);
    }

    @Nullable
    protected final Cursor query(
        final boolean distinct,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having) {
        return getDatabase().query(distinct,
                                   getTableName(),
                                   columns,
                                   selection,
                                   selectionArgs,
                                   groupBy,
                                   having);
    }

    @Nullable
    protected final Cursor query(
        final boolean distinct,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy) {
        return getDatabase().query(distinct,
                                   getTableName(),
                                   columns,
                                   selection,
                                   selectionArgs,
                                   groupBy);
    }

    @Nullable
    protected final Cursor query(
        final boolean distinct,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        return getDatabase().query(distinct, getTableName(), columns, selection, selectionArgs);
    }

    @Nullable
    protected final Cursor query(
        final boolean distinct,
        @Nullable final String[] columns,
        @Nullable final String selection) {
        return getDatabase().query(distinct, getTableName(), columns, selection);
    }

    @Nullable
    protected final Cursor query(
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having,
        @Nullable final String orderBy,
        @Nullable final String limit) {
        return getDatabase().query(getTableName(),
                                   columns,
                                   selection,
                                   selectionArgs,
                                   groupBy,
                                   having,
                                   orderBy,
                                   limit);
    }

    @Nullable
    protected final Cursor query(
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having,
        @Nullable final String orderBy) {
        return getDatabase().query(getTableName(),
                                   columns,
                                   selection,
                                   selectionArgs,
                                   groupBy,
                                   having,
                                   orderBy);
    }

    @Nullable
    protected final Cursor query(
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having) {
        return getDatabase().query(getTableName(),
                                   columns,
                                   selection,
                                   selectionArgs,
                                   groupBy,
                                   having);
    }

    @Nullable
    protected final Cursor query(
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy) {
        return getDatabase().query(getTableName(), columns, selection, selectionArgs, groupBy);
    }

    @Nullable
    protected final Cursor query(
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        return getDatabase().query(getTableName(), columns, selection, selectionArgs);
    }

    @Nullable
    protected final Cursor query(
        @Nullable final String[] columns, @Nullable final String selection) {
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
    protected final CollectionResult<TModel> select() {
        return createCollectionResult(query());
    }

    @NonNull
    protected final CollectionResult<TModel> select(
        final boolean distinct,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String orderBy,
        @Nullable final String limit) {
        return createCollectionResult(query(distinct,
                                            getFullProjection().getColumns(),
                                            selection,
                                            selectionArgs,
                                            orderBy,
                                            limit));
    }

    @NonNull
    protected final CollectionResult<TModel> select(
        final boolean distinct,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String orderBy) {
        return createCollectionResult(query(distinct,
                                            getFullProjection().getColumns(),
                                            selection,
                                            selectionArgs,
                                            orderBy));
    }

    @NonNull
    protected final CollectionResult<TModel> select(
        final boolean distinct,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        return createCollectionResult(query(distinct,
                                            getFullProjection().getColumns(),
                                            selection,
                                            selectionArgs));
    }

    @NonNull
    protected final CollectionResult<TModel> select(
        final boolean distinct, @Nullable final String selection) {
        return createCollectionResult(query(distinct, getFullProjection().getColumns(), selection));
    }

    @NonNull
    protected final CollectionResult<TModel> select(
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String orderBy,
        @Nullable final String limit) {
        return createCollectionResult(query(getFullProjection().getColumns(),
                                            selection,
                                            selectionArgs,
                                            orderBy,
                                            limit));
    }

    @NonNull
    protected final CollectionResult<TModel> select(
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String orderBy) {
        return createCollectionResult(query(getFullProjection().getColumns(),
                                            selection,
                                            selectionArgs,
                                            orderBy));
    }

    @NonNull
    protected final CollectionResult<TModel> select(
        @Nullable final String selection, @Nullable final String[] selectionArgs) {
        return createCollectionResult(query(getFullProjection().getColumns(),
                                            selection,
                                            selectionArgs));
    }

    @NonNull
    protected final CollectionResult<TModel> select(@Nullable final String selection) {
        return createCollectionResult(query(getFullProjection().getColumns(), selection));
    }

    @Nullable
    protected final TModel selectSingle(
        @Nullable final String selection, @Nullable final String[] selectionArgs) {
        TModel result = null;

        try (final val cursor = query(getFullProjection().getColumns(), selection, selectionArgs)) {
            if (cursor != null) {
                result = getModelFactory().create(cursor);
            }
        }

        return result;
    }

    @Nullable
    protected final TModel selectSingle(@Nullable final String selection) {
        TModel result = null;

        try (final val cursor = query(getFullProjection().getColumns(), selection)) {
            if (cursor != null) {
                result = getModelFactory().create(cursor);
            }
        }

        return result;
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(
        @NonNull final TModel model, @Nullable String whereClause, @Nullable String[] whereArgs) {
        DaoContracts.requireId(model);

        whereClause += " AND " + getIdColumnName() + "=?";
        whereArgs = ArrayUtils.add(whereArgs, String.valueOf(model.getId()));

        return getDatabase().update(getTableName(),
                                    getModelContentExtractor().extract(model),
                                    whereClause,
                                    whereArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    protected final int update(@NonNull final TModel model, @Nullable String whereClause) {
        DaoContracts.requireId(model);

        whereClause += " AND " + getIdColumnName() + "=?";

        return getDatabase().update(getTableName(),
                                    getModelContentExtractor().extract(model),
                                    whereClause,
                                    new String[]{String.valueOf(model.getId())});
    }

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final Database _database;

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final String _idColumnName;

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final String _tableName;
}
