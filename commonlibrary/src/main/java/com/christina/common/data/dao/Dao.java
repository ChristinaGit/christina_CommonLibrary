package com.christina.common.data.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.data.model.Model;

import java.util.List;

public interface Dao<TModel extends Model> {
    @Nullable
    TModel create();

    @Nullable
    TModel get(long id);

    @Nullable
    List<TModel> get();

    int delete(long id);

    int update(@NonNull TModel model);
}
