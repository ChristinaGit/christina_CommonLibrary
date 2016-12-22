package com.christina.common.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

public abstract class SQLiteDatabaseWrapper extends SQLiteOpenHelper implements Database {
    @Override
    public void beginTransaction() {
        getWritableDatabase().beginTransaction();
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int delete(
        @NonNull final String table,
        @Nullable final String whereClause,
        @Nullable final String[] whereArgs) {
        Contracts.requireNonNull(table, "table == null");

        return getWritableDatabase().delete(table, whereClause, whereArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int delete(@NonNull final String table, @Nullable final String whereClause) {
        Contracts.requireNonNull(table, "table == null");

        return getWritableDatabase().delete(table, whereClause, null);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int delete(@NonNull final String table) {
        Contracts.requireNonNull(table, "table == null");

        return getWritableDatabase().delete(table, null, null);
    }

    @Override
    public void endTransaction() {
        getWritableDatabase().endTransaction();
    }

    @Override
    public final long insert(@NonNull final String tableName, @NonNull final ContentValues values) {
        Contracts.requireNonNull(tableName, "tableName == null");
        Contracts.requireNonNull(values, "values == null");

        return getWritableDatabase().insert(tableName, null, values);
    }

    @Nullable
    @Override
    public final Cursor query(
        final boolean distinct,
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having,
        @Nullable final String orderBy,
        @Nullable final String limit) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(distinct,
                                           table,
                                           columns,
                                           selection,
                                           selectionArgs,
                                           groupBy,
                                           having,
                                           orderBy,
                                           limit);
    }

    @Nullable
    @Override
    public final Cursor query(
        final boolean distinct,
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having,
        @Nullable final String orderBy) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(distinct,
                                           table,
                                           columns,
                                           selection,
                                           selectionArgs,
                                           groupBy,
                                           having,
                                           orderBy,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(
        final boolean distinct,
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(distinct,
                                           table,
                                           columns,
                                           selection,
                                           selectionArgs,
                                           groupBy,
                                           having,
                                           null,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(
        final boolean distinct,
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(distinct,
                                           table,
                                           columns,
                                           selection,
                                           selectionArgs,
                                           groupBy,
                                           null,
                                           null,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(
        final boolean distinct,
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(distinct,
                                           table,
                                           columns,
                                           selection,
                                           selectionArgs,
                                           null,
                                           null,
                                           null,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(
        final boolean distinct,
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(distinct,
                                           table,
                                           columns,
                                           selection,
                                           null,
                                           null,
                                           null,
                                           null,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having,
        @Nullable final String orderBy,
        @Nullable final String limit) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(table,
                                           columns,
                                           selection,
                                           selectionArgs,
                                           groupBy,
                                           having,
                                           orderBy,
                                           limit);
    }

    @Nullable
    @Override
    public final Cursor query(
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having,
        @Nullable final String orderBy) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(table,
                                           columns,
                                           selection,
                                           selectionArgs,
                                           groupBy,
                                           having,
                                           orderBy,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy,
        @Nullable final String having) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(table,
                                           columns,
                                           selection,
                                           selectionArgs,
                                           groupBy,
                                           having,
                                           null,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs,
        @Nullable final String groupBy) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(table,
                                           columns,
                                           selection,
                                           selectionArgs,
                                           groupBy,
                                           null,
                                           null,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(table,
                                           columns,
                                           selection,
                                           selectionArgs,
                                           null,
                                           null,
                                           null,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(
        @NonNull final String table,
        @Nullable final String[] columns,
        @Nullable final String selection) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(table, columns, selection, null, null, null, null, null);
    }

    @Nullable
    @Override
    public final Cursor query(
        final boolean distinct, @NonNull final String table, @Nullable final String[] columns) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(distinct,
                                           table,
                                           columns,
                                           null,
                                           null,
                                           null,
                                           null,
                                           null,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(final boolean distinct, @NonNull final String table) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(distinct,
                                           table,
                                           null,
                                           null,
                                           null,
                                           null,
                                           null,
                                           null,
                                           null);
    }

    @Nullable
    @Override
    public final Cursor query(@NonNull final String table, @Nullable final String[] columns) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(table, columns, null, null, null, null, null);
    }

    @Nullable
    @Override
    public final Cursor query(@NonNull final String table) {
        Contracts.requireNonNull(table, "table == null");

        return getReadableDatabase().query(table, null, null, null, null, null, null);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int update(
        @NonNull final String table,
        @Nullable final ContentValues values,
        @Nullable final String whereClause,
        @Nullable final String[] whereArgs) {
        Contracts.requireNonNull(table, "table == null");

        return getWritableDatabase().update(table, values, whereClause, whereArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int update(
        @NonNull final String table,
        @Nullable final ContentValues values,
        @Nullable final String whereClause) {
        Contracts.requireNonNull(table, "table == null");

        return getWritableDatabase().update(table, values, whereClause, null);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int update(@NonNull final String table, @Nullable final ContentValues values) {
        Contracts.requireNonNull(table, "table == null");

        return getWritableDatabase().update(table, values, null, null);
    }

    protected SQLiteDatabaseWrapper(
        @NonNull final Context context,
        @NonNull final String name,
        @Nullable final CursorFactory factory,
        final int version) {
        super(Contracts.requireNonNull(context, "context == null"),
              Contracts.requireNonNull(name, "name == null"),
              factory,
              version);
    }

    protected SQLiteDatabaseWrapper(
        @NonNull final Context context,
        @NonNull final String name,
        @Nullable final CursorFactory factory,
        final int version,
        @Nullable final DatabaseErrorHandler errorHandler) {
        super(Contracts.requireNonNull(context, "context == null"),
              Contracts.requireNonNull(name, "name == null"),
              factory,
              version,
              errorHandler);
    }
}
