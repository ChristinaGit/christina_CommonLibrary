package com.christina.common.data.dao.factory;

import android.database.Cursor;

import com.christina.common.data.model.Model;
import com.christina.common.pattern.factory.Factory;
import com.christina.common.pattern.factory.TransitionFactory;

public interface ModelFactory<TModel extends Model>
    extends Factory<TModel>, TransitionFactory<TModel, Cursor> {
}
