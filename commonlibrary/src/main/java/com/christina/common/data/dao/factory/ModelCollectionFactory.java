package com.christina.common.data.dao.factory;

import android.database.Cursor;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;

import com.christina.common.data.model.Model;
import com.christina.common.pattern.factory.collection.CollectionFactory;
import com.christina.common.pattern.factory.collection.TransitionCollectionFactory;
import com.christina.common.pattern.factory.map.MapFactory;
import com.christina.common.pattern.factory.map.TransitionMapFactory;

public interface ModelCollectionFactory<TModel extends Model>
    extends TransitionMapFactory<Long, TModel, Cursor>,
            MapFactory<Long, TModel>,
            TransitionCollectionFactory<TModel, Cursor>,
            CollectionFactory<TModel> {
    @NonNull
    LongSparseArray<TModel> createSparseArray();

    @NonNull
    LongSparseArray<TModel> createSparseArray(
        @IntRange(from = 0, to = Integer.MAX_VALUE) final int capacity);

    @NonNull
    LongSparseArray<TModel> createSparseArray(@NonNull final Cursor argument);
}
