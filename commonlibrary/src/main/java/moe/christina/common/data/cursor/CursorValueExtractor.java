package moe.christina.common.data.cursor;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface CursorValueExtractor<TValue> {
    @Nullable
    TValue extract(@NonNull Cursor cursor);

    @Nullable
    TValue extract(@NonNull Cursor cursor, int columnIndex);

    @Nullable
    TValue extract(@NonNull Cursor cursor, @NonNull String columnName);
}
