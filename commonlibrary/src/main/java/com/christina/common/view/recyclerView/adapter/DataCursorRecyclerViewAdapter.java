package com.christina.common.view.recyclerView.adapter;

import android.database.DataSetObserver;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.christina.common.ResourceUtils;
import com.christina.common.data.cursor.dataCursor.DataCursor;
import com.christina.common.view.recyclerView.viewHolder.ExtendedRecyclerViewHolder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

@Accessors(prefix = "_")
public abstract class DataCursorRecyclerViewAdapter<TItem, TViewHolder extends
    ExtendedRecyclerViewHolder>
    extends RecyclerView.Adapter<TViewHolder> {

    @Nullable
    public TItem getItem(final int position) {
        final TItem item;

        final val dataCursor = getDataCursor();
        if (isDataValid() && dataCursor != null && dataCursor.moveToPosition(position)) {
            item = dataCursor.getData();
        } else {
            item = null;
        }

        return item;
    }

    @CallSuper
    @Override
    public void onBindViewHolder(final TViewHolder holder, final int position) {
        final val item = getItem(position);
        if (item != null) {
            onBindViewHolder(holder, item, position);
        } else {
            throw new IllegalArgumentException(
                "You are trying bind view holder onto not existing item.");
        }
    }

    @Override
    public int getItemCount() {
        final int itemCount;

        final val dataCursor = getDataCursor();
        if (isDataValid() && dataCursor != null) {
            itemCount = dataCursor.getCount();
        } else {
            itemCount = 0;
        }

        return itemCount;
    }

    @CallSuper
    public void setDataCursor(@Nullable final DataCursor<TItem> dataCursor) {
        setDataCursor(dataCursor, true);
    }

    @CallSuper
    public void setDataCursor(@Nullable final DataCursor<TItem> dataCursor, final boolean notify) {
        final val oldDataCursor = swapDataCursor(dataCursor, notify);

        ResourceUtils.quietClose(oldDataCursor);
    }

    @CallSuper
    @Nullable
    public DataCursor<TItem> swapDataCursor(@Nullable final DataCursor<TItem> dataCursor) {
        return swapDataCursor(dataCursor, true);
    }

    @CallSuper
    @Nullable
    public DataCursor<TItem> swapDataCursor(
        @Nullable final DataCursor<TItem> dataCursor, final boolean notify) {
        final DataCursor<TItem> oldDataCursor;

        if (_dataCursor != dataCursor) {
            oldDataCursor = _dataCursor;

            _dataCursor = dataCursor;

            if (oldDataCursor != null) {
                oldDataCursor.unregisterDataSetObserver(getInternalDataSetObserver());
            }

            if (_dataCursor != null) {
                _dataCursor.registerDataSetObserver(getInternalDataSetObserver());
            }

            _dataValid = _dataCursor != null;

            if (notify) {
                final int oldItemsCount = oldDataCursor != null ? oldDataCursor.getCount() : 0;
                final int itemsCount = dataCursor != null ? dataCursor.getCount() : 0;

                final int changedCount = Math.min(oldItemsCount, itemsCount);
                final int removedCount = Math.max(0, oldItemsCount - changedCount);
                final int insertedCount = Math.max(0, itemsCount - changedCount);

                if (changedCount > 0) {
                    notifyItemRangeChanged(0, changedCount);
                }
                if (removedCount > 0) {
                    notifyItemRangeRemoved(changedCount, removedCount);
                }
                if (insertedCount > 0) {
                    notifyItemRangeInserted(changedCount, insertedCount);
                }
            }
        } else {
            oldDataCursor = null;
        }

        return oldDataCursor;
    }

    @CallSuper
    protected void onDataCursorSetChanged() {
        _dataValid = true;
        notifyDataSetChanged();
    }

    @CallSuper
    protected void onDataCursorSetInvalidated() {
        _dataValid = false;
        notifyDataSetChanged();
    }

    protected abstract void onBindViewHolder(
        @NonNull final TViewHolder holder, @NonNull final TItem item, final int position);

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    @NonNull
    private final _InternalDataSetObserver _internalDataSetObserver =
        new _InternalDataSetObserver();

    @Getter
    @Nullable
    private DataCursor<TItem> _dataCursor;

    @Getter
    private boolean _dataValid = false;

    private final class _InternalDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();

            onDataCursorSetChanged();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();

            onDataCursorSetInvalidated();
        }
    }
}
