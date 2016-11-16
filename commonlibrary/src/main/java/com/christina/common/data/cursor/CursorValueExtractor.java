package com.christina.common.data.cursor;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface CursorValueExtractor<TValue> {
    @Nullable
    TValue extract(@NonNull final Cursor cursor);

    @Nullable
    TValue extract(@NonNull final Cursor cursor, final int columnIndex);

    @Nullable
    TValue extract(@NonNull final Cursor cursor, @NonNull final String columnName);
}
