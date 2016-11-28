package com.christina.common.data.dao.result;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;

import com.christina.common.data.cursor.dataCursor.DataCursor;
import com.christina.common.data.model.Model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;

public interface CollectionResult<TModel extends Model> {
    @Nullable
    TModel[] asArray();

    @Nullable
    Collection<TModel> asCollection();

    @Nullable
    Cursor asCursor();

    @Nullable
    DataCursor<TModel> asDataCursor();

    @Nullable
    List<TModel> asList();

    @Nullable
    Map<Long, TModel> asMap();

    @Nullable
    NavigableMap<Long, TModel> asNavigableMap();

    @Nullable
    SortedMap<Long, TModel> asSortedMap();

    @Nullable
    LongSparseArray<TModel> asSparseArray();

    int getCount();

    boolean isConsumed();

    void release();
}
