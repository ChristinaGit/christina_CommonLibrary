package com.christina.common.data.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.data.model.Model;

public interface Dao<TModel extends Model> {
    @Nullable
    TModel create();

    int delete(long id);

    @NonNull
    DaoCollectionResult<TModel> get();

    @Nullable
    TModel get(long id);

    int update(@NonNull TModel model);
}
