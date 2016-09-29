package com.christina.common.data.model.factory;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.christina.common.data.model.Model;

public interface ContentModelFactory<TModel extends Model> extends ModelFactory<TModel> {
    @NonNull
    TModel create(@NonNull final Cursor cursor);
}
