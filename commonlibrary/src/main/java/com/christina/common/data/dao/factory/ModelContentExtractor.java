package com.christina.common.data.dao.factory;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.christina.common.data.model.Model;

public interface ModelContentExtractor<TModel extends Model> {
    @NonNull
    ContentValues extract(@NonNull final TModel model);
}
