package com.christina.common.data.model.factory;

import android.support.annotation.NonNull;

import com.christina.common.data.model.Model;

public interface ModelFactory<TModel extends Model> {
    @NonNull
    TModel create();
}
