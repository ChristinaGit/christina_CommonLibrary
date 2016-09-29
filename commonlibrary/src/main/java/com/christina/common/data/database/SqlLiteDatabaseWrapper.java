package com.christina.common.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

public abstract class SqlLiteDatabaseWrapper extends SQLiteOpenHelper implements Database {
    @Override
    public void beginTransaction() {
        getWritableDatabase().beginTransaction();
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int delete(@NonNull final String tableName, @Nullable final String whereClause,
        @Nullable final String[] whereArgs) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getWritableDatabase().delete(tableName, whereClause, whereArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int delete(@NonNull final String tableName, @Nullable final String whereClause) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getWritableDatabase().delete(tableName, whereClause, null);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int delete(@NonNull final String tableName) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getWritableDatabase().delete(tableName, null, null);
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
    public final Cursor query(final boolean distinct, @NonNull final String tableName,
        @Nullable final String[] columns, @Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String groupBy,
        @Nullable final String having, @Nullable final String orderBy,
        @Nullable final String limit) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(distinct, tableName, columns, selection, selectionArgs,
            groupBy, having, orderBy, limit);
    }

    @Nullable
    @Override
    public final Cursor query(final boolean distinct, @NonNull final String tableName,
        @Nullable final String[] columns, @Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String groupBy,
        @Nullable final String having, @Nullable final String orderBy) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(distinct, tableName, columns, selection, selectionArgs,
            groupBy, having, orderBy, null);
    }

    @Nullable
    @Override
    public final Cursor query(final boolean distinct, @NonNull final String tableName,
        @Nullable final String[] columns, @Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String groupBy,
        @Nullable final String having) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(distinct, tableName, columns, selection, selectionArgs,
            groupBy, having, null, null);
    }

    @Nullable
    @Override
    public final Cursor query(final boolean distinct, @NonNull final String tableName,
        @Nullable final String[] columns, @Nullable final String selection,
        @Nullable final String[] selectionArgs, @Nullable final String groupBy) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(distinct, tableName, columns, selection, selectionArgs,
            groupBy, null, null, null);
    }

    @Nullable
    @Override
    public final Cursor query(final boolean distinct, @NonNull final String tableName,
        @Nullable final String[] columns, @Nullable final String selection,
        @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(distinct, tableName, columns, selection, selectionArgs,
            null, null, null, null);
    }

    @Nullable
    @Override
    public final Cursor query(final boolean distinct, @NonNull final String tableName,
        @Nullable final String[] columns, @Nullable final String selection) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(distinct, tableName, columns, selection, null, null,
            null, null, null);
    }

    @Nullable
    @Override
    public final Cursor query(@NonNull final String tableName, @Nullable final String[] columns,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String groupBy, @Nullable final String having,
        @Nullable final String orderBy, @Nullable final String limit) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(tableName, columns, selection, selectionArgs, groupBy,
            having, orderBy, limit);
    }

    @Nullable
    @Override
    public final Cursor query(@NonNull final String tableName, @Nullable final String[] columns,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String groupBy, @Nullable final String having,
        @Nullable final String orderBy) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(tableName, columns, selection, selectionArgs, groupBy,
            having, orderBy, null);
    }

    @Nullable
    @Override
    public final Cursor query(@NonNull final String tableName, @Nullable final String[] columns,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String groupBy, @Nullable final String having) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(tableName, columns, selection, selectionArgs, groupBy,
            having, null, null);
    }

    @Nullable
    @Override
    public final Cursor query(@NonNull final String tableName, @Nullable final String[] columns,
        @Nullable final String selection, @Nullable final String[] selectionArgs,
        @Nullable final String groupBy) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(tableName, columns, selection, selectionArgs, groupBy,
            null, null, null);
    }

    @Nullable
    @Override
    public final Cursor query(@NonNull final String tableName, @Nullable final String[] columns,
        @Nullable final String selection, @Nullable final String[] selectionArgs) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(tableName, columns, selection, selectionArgs, null, null,
            null, null);
    }

    @Nullable
    @Override
    public final Cursor query(@NonNull final String tableName, @Nullable final String[] columns,
        @Nullable final String selection) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(tableName, columns, selection, null, null, null, null,
            null);
    }

    @Nullable
    @Override
    public final Cursor query(final boolean distinct, @NonNull final String tableName,
        @Nullable final String[] columns) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(distinct, tableName, columns, null, null, null, null,
            null, null);
    }

    @Nullable
    @Override
    public final Cursor query(final boolean distinct, @NonNull final String tableName) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(distinct, tableName, null, null, null, null, null, null,
            null);
    }

    @Nullable
    @Override
    public final Cursor query(@NonNull final String tableName, @Nullable final String[] columns) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(tableName, columns, null, null, null, null, null);
    }

    @Nullable
    @Override
    public final Cursor query(@NonNull final String tableName) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getReadableDatabase().query(tableName, null, null, null, null, null, null);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int update(@NonNull final String tableName, @Nullable final ContentValues values,
        @Nullable final String whereClause, @Nullable final String[] whereArgs) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getWritableDatabase().update(tableName, values, whereClause, whereArgs);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int update(@NonNull final String tableName, @Nullable final ContentValues values,
        @Nullable final String whereClause) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getWritableDatabase().update(tableName, values, whereClause, null);
    }

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    @Override
    public final int update(@NonNull final String tableName, @Nullable final ContentValues values) {
        Contracts.requireNonNull(tableName, "tableName == null");

        return getWritableDatabase().update(tableName, values, null, null);
    }

    protected SqlLiteDatabaseWrapper(@NonNull final Context context, @NonNull final String name,
        @Nullable final SQLiteDatabase.CursorFactory factory, final int version) {
        super(context, name, factory, version);
    }

    protected SqlLiteDatabaseWrapper(@NonNull final Context context, @NonNull final String name,
        @Nullable final SQLiteDatabase.CursorFactory factory, final int version,
        @Nullable final DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
}
