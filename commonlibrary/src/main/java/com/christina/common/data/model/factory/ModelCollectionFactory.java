package com.christina.common.data.model.factory;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;

import com.christina.common.data.model.Model;

import java.util.List;
import java.util.Map;

public interface ModelCollectionFactory<TModel extends Model> {
    @NonNull
    TModel[] createArray(@IntRange(from = 0, to = Integer.MAX_VALUE) final int size);

    @NonNull
    List<TModel> createList(@IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity);

    @NonNull
    List<TModel> createList();

    @NonNull
    Map<Long, TModel> createMap();

    @NonNull
    Map<Long, TModel> createMap(@IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity);

    @NonNull
    LongSparseArray<TModel> createPrimitiveMap();

    @NonNull
    LongSparseArray<TModel> createPrimitiveMap(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity);
}
