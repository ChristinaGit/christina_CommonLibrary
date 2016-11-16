package com.christina.common.data.cursor.dataCursor;

import android.database.Cursor;
import android.support.annotation.NonNull;

public interface DataCursor<TData> extends Cursor {
    @NonNull
    TData getData();
}
