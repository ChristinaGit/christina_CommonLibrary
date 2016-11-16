package com.christina.common.data.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.data.dao.result.CollectionResult;
import com.christina.common.data.model.Model;

public interface Dao<TModel extends Model> {
    int delete(long id);

    @Nullable
    TModel get(long id);

    @NonNull
    CollectionResult<TModel> getAll();

    long insert(@NonNull TModel model);

    int update(@NonNull TModel model);
}
