package com.christina.common.data.dao.result;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;

import com.christina.common.contract.Contracts;
import com.christina.common.data.cursor.dataCursor.DataCursor;
import com.christina.common.data.cursor.dataCursor.DataCursorWrapper;
import com.christina.common.data.dao.factory.ModelCollectionFactory;
import com.christina.common.data.model.Model;
import com.christina.common.pattern.factory.TransitionFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;

public final class CursorCollectionResult<TModel extends Model>
    implements CollectionResult<TModel> {
    public CursorCollectionResult(@Nullable final Cursor cursor,
        @NonNull final TransitionFactory<TModel, Cursor> modelFactory,
        @NonNull final ModelCollectionFactory<TModel> collectionModelFactory) {
        Contracts.requireNonNull(modelFactory, "modelFactory == null");
        Contracts.requireNonNull(collectionModelFactory, "collectionModelFactory == null");

        _cursor = cursor;
        _modelFactory = modelFactory;
        _collectionModelFactory = collectionModelFactory;
    }

    @Nullable
    @Override
    public final TModel[] asArray() {
        synchronized (_lock) {
            _checkState();

            final TModel[] result;

            try (final Cursor cursor = _cursor) {
                if (cursor != null) {
                    result = _collectionModelFactory.createArray(cursor);
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
    public Collection<TModel> asCollection() {
        synchronized (_lock) {
            _checkState();

            final Collection<TModel> result;

            try (final Cursor cursor = _cursor) {
                if (cursor != null) {
                    result = _collectionModelFactory.createCollection(cursor);
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
    public Cursor asCursor() {
        synchronized (_lock) {
            _checkState();

            _consumed = true;

            return _cursor;
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
                    result = _collectionModelFactory.createList(cursor);
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
                    result = _collectionModelFactory.createMap(cursor);
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
    public DataCursor<TModel> asDataCursor() {
        synchronized (_lock) {
            _checkState();

            final DataCursor<TModel> result;

            if (_cursor != null) {
                result = new DataCursorWrapper<>(_cursor, _modelFactory);
            } else {
                result = null;
            }

            _consumed = true;

            return result;
        }
    }

    @Nullable
    @Override
    public NavigableMap<Long, TModel> asNavigableMap() {
        synchronized (_lock) {
            _checkState();

            final NavigableMap<Long, TModel> result;

            try (final Cursor cursor = _cursor) {
                if (cursor != null) {
                    result = _collectionModelFactory.createNavigableMap(cursor);
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
    public SortedMap<Long, TModel> asSortedMap() {
        synchronized (_lock) {
            _checkState();

            final SortedMap<Long, TModel> result;

            try (final Cursor cursor = _cursor) {
                if (cursor != null) {
                    result = _collectionModelFactory.createSortedMap(cursor);
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
    public final LongSparseArray<TModel> asSparseArray() {
        synchronized (_lock) {
            _checkState();

            final LongSparseArray<TModel> result;

            try (final Cursor cursor = _cursor) {
                if (cursor != null) {
                    result = _collectionModelFactory.createSparseArray(cursor);
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

    @Override
    public void release() {
        synchronized (_lock) {
            _checkState();

            if (_cursor != null) {
                _cursor.close();
            }

            _consumed = true;
        }
    }

    @NonNull
    private final ModelCollectionFactory<TModel> _collectionModelFactory;

    @Nullable
    private final Cursor _cursor;

    private final Object _lock = new Object();

    @NonNull
    private final TransitionFactory<TModel, Cursor> _modelFactory;

    private boolean _consumed = false;

    private void _checkState() {
        if (_consumed) {
            throw new IllegalStateException("Result is already consumed.");
        }
    }
}
