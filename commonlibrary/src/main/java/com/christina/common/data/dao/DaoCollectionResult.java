package com.christina.common.data.dao;

import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;

import com.christina.common.data.model.Model;

import java.util.List;
import java.util.Map;

public interface DaoCollectionResult<TModel extends Model> {
    @Nullable
    TModel[] asArray();

    @Nullable
    List<TModel> asList();

    @Nullable
    Map<Long, TModel> asMap();

    @Nullable
    LongSparseArray<TModel> asPrimitiveMap();

    boolean isObtained();
}
