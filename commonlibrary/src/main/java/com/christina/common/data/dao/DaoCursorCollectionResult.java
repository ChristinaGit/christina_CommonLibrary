package com.christina.common.data.dao;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;

import com.christina.common.contract.Contracts;
import com.christina.common.data.model.Model;
import com.christina.common.data.model.factory.ContentModelCollectionFactory;

import java.util.List;
import java.util.Map;

public final class DaoCursorCollectionResult<TModel extends Model>
    implements DaoCollectionResult<TModel> {
    public DaoCursorCollectionResult(@Nullable final Cursor cursor,
        @NonNull final ContentModelCollectionFactory<TModel> collectionFactory) {
        Contracts.requireNonNull(collectionFactory, "collectionFactory == null");

        _cursor = cursor;
        _collectionFactory = collectionFactory;
    }

    @Nullable
    @Override
    public final TModel[] asArray() {
        synchronized (_lock) {
            _checkState();

            final TModel[] result;

            try (final Cursor cursor = _cursor) {
                if (cursor != null) {
                    result = _collectionFactory.createArray(cursor);
                } else {
                    result = null;
                }
            }

            _consumed = true;

            return result;
        }
    }

    @Nullable
    @Override
    public final List<TModel> asList() {
        synchronized (_lock) {
            _checkState();

            final List<TModel> result;

            try (final Cursor cursor = _cursor) {
                if (cursor != null) {
                    result = _collectionFactory.createList(cursor);
                } else {
                    result = null;
                }
            }

            _consumed = true;

            return result;
        }
    }

    @Nullable
    @Override
    public final Map<Long, TModel> asMap() {
        synchronized (_lock) {
            _checkState();

            final Map<Long, TModel> result;

            try (final Cursor cursor = _cursor) {
                if (cursor != null) {
                    result = _collectionFactory.createMap(cursor);
                } else {
                    result = null;
                }
            }

            _consumed = true;

            return result;
        }
    }

    @Nullable
    @Override
    public final LongSparseArray<TModel> asPrimitiveMap() {
        synchronized (_lock) {
            _checkState();

            final LongSparseArray<TModel> result;

            try (final Cursor cursor = _cursor) {
                if (cursor != null) {
                    result = _collectionFactory.createPrimitiveMap(cursor);
                } else {
                    result = null;
                }
            }

            _consumed = true;

            return result;
        }
    }

    @Override
    public final int getCount() {
        synchronized (_lock) {
            _checkState();

            final int count;

            if (_cursor != null) {
                count = _cursor.getCount();
            } else {
                count = 0;
            }

            return count;
        }
    }

    public final boolean isConsumed() {
        synchronized (_lock) {
            return _consumed;
        }
    }

    @NonNull
    private final ContentModelCollectionFactory<TModel> _collectionFactory;

    @Nullable
    private final Cursor _cursor;

    private final Object _lock = new Object();

    private boolean _consumed = false;

    private void _checkState() {
        if (_consumed) {
            throw new IllegalStateException("Result is already consumed.");
        }
    }
}
