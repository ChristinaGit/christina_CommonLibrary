package com.christina.common.data.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.data.dao.result.CollectionResult;
import com.christina.common.data.dao.selection.SqlDataSelection;
import com.christina.common.data.model.Model;

public interface SqlDao<TModel extends Model> extends Dao<TModel> {
    int delete(@NonNull final SqlDataSelection sqlDataSelection);

    @Nullable
    TModel get(@NonNull final SqlDataSelection sqlDataSelection);

    @NonNull
    CollectionResult<TModel> getAll(@NonNull final SqlDataSelection sqlDataSelection);
}
