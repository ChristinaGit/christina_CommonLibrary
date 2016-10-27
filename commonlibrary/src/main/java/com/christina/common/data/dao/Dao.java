package com.christina.common.data.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.data.model.Model;

public interface Dao<TModel extends Model> {
    @Nullable
    TModel create();

    int delete(long id);

    @Nullable
    TModel get(long id);

    @NonNull
    DaoCollectionResult<TModel> getAll();

    int update(@NonNull TModel model);
}
