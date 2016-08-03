package com.christina.common.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface Database {
    long insert(@NonNull String tableName, @NonNull ContentValues values);

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    int delete(@NonNull String table, @Nullable String whereClause, @Nullable String[] whereArgs);

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    int delete(@NonNull String table, @Nullable String whereClause);

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    int delete(@NonNull String table);

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    int update(@NonNull String table, @Nullable ContentValues values, @Nullable String whereClause,
               @Nullable String[] whereArgs);

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    int update(@NonNull String table, @Nullable ContentValues values, @Nullable String whereClause);

    @IntRange(from = 0, to = Integer.MAX_VALUE)
    int update(@NonNull String table, @Nullable ContentValues values);

    @Nullable
    Cursor query(boolean distinct, @NonNull String table, @Nullable String[] columns,
                 @Nullable String selection, @Nullable String[] selectionArgs,
                 @Nullable String groupBy, @Nullable String having, @Nullable String orderBy,
                 @Nullable String limit);

    @Nullable
    Cursor query(boolean distinct, @NonNull String table, @Nullable String[] columns,
                 @Nullable String selection, @Nullable String[] selectionArgs,
                 @Nullable String groupBy, @Nullable String having, @Nullable String orderBy);

    @Nullable
    Cursor query(boolean distinct, @NonNull String table, @Nullable String[] columns,
                 @Nullable String selection, @Nullable String[] selectionArgs,
                 @Nullable String groupBy, @Nullable String having);

    @Nullable
    Cursor query(boolean distinct, @NonNull String table, @Nullable String[] columns,
                 @Nullable String selection, @Nullable String[] selectionArgs,
                 @Nullable String groupBy);

    @Nullable
    Cursor query(boolean distinct, @NonNull String table, @Nullable String[] columns,
                 @Nullable String selection, @Nullable String[] selectionArgs);

    @Nullable
    Cursor query(boolean distinct, @NonNull String table, @Nullable String[] columns,
                 @Nullable String selection);

    @Nullable
    Cursor query(@NonNull String table, @Nullable String[] columns, @Nullable String selection,
                 @Nullable String[] selectionArgs, @Nullable String groupBy,
                 @Nullable String having, @Nullable String orderBy, @Nullable String limit);

    @Nullable
    Cursor query(@NonNull String table, @Nullable String[] columns, @Nullable String selection,
                 @Nullable String[] selectionArgs, @Nullable String groupBy,
                 @Nullable String having, @Nullable String orderBy);

    @Nullable
    Cursor query(@NonNull String table, @Nullable String[] columns, @Nullable String selection,
                 @Nullable String[] selectionArgs, @Nullable String groupBy,
                 @Nullable String having);

    @Nullable
    Cursor query(@NonNull String table, @Nullable String[] columns, @Nullable String selection,
                 @Nullable String[] selectionArgs, @Nullable String groupBy);

    @Nullable
    Cursor query(@NonNull String table, @Nullable String[] columns, @Nullable String selection,
                 @Nullable String[] selectionArgs);

    @Nullable
    Cursor query(@NonNull String table, @Nullable String[] columns, @Nullable String selection);

    @Nullable
    Cursor query(boolean distinct, @NonNull String table, @Nullable String[] columns);

    @Nullable
    Cursor query(boolean distinct, @NonNull String table);

    @Nullable
    Cursor query(@NonNull String table, @Nullable String[] columns);

    @Nullable
    Cursor query(@NonNull String table);

    void beginTransaction();

    void endTransaction();
}
