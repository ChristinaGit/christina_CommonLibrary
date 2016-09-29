package com.christina.common.data.model.factory;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;

import com.christina.common.data.model.Model;

import java.util.List;
import java.util.Map;

public interface ContentModelCollectionFactory<TModel extends Model>
    extends ModelCollectionFactory<TModel> {
    @NonNull
    TModel[] createArray(@NonNull final Cursor cursor);

    @NonNull
    List<TModel> createList(@NonNull final Cursor cursor);

    @NonNull
    Map<Long, TModel> createMap(@NonNull final Cursor cursor);

    @NonNull
    LongSparseArray<TModel> createPrimitiveMap(@NonNull final Cursor cursor);
}
