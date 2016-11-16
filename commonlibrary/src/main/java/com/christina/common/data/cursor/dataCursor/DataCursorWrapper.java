package com.christina.common.data.cursor.dataCursor;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;
import com.christina.common.pattern.factory.TransitionFactory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public class DataCursorWrapper<TData> extends CursorWrapper implements DataCursor<TData> {
    public DataCursorWrapper(@NonNull final Cursor cursor,
        @NonNull final TransitionFactory<TData, Cursor> modelFactory) {
        super(cursor);
        _modelFactory = modelFactory;

        Contracts.requireNonNull(cursor, "cursor == null");
    }

    @NonNull
    @Override
    public TData getData() {
        return getModelFactory().create(this);
    }

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final TransitionFactory<TData, Cursor> _modelFactory;
}
