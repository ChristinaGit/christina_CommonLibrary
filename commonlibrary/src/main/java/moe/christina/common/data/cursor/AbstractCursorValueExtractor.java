package moe.christina.common.data.cursor;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import moe.christina.common.contract.Contracts;

public abstract class AbstractCursorValueExtractor<TValue> implements CursorValueExtractor<TValue> {
    @Nullable
    @Override
    public TValue extract(@NonNull final Cursor cursor) {
        Contracts.requireNonNull(cursor, "cursor == null");

        return extract(cursor, 0);
    }

    @Nullable
    @Override
    public TValue extract(@NonNull final Cursor cursor, @NonNull final String columnName) {
        Contracts.requireNonNull(cursor, "cursor == null");
        Contracts.requireNonNull(columnName, "columnName == null");

        return extract(cursor, cursor.getColumnIndexOrThrow(columnName));
    }
}
