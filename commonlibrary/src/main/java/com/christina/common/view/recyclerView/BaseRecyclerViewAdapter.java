package com.christina.common.view.recyclerView;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.christina.common.contract.Contracts;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<TItem, TListItem, TViewHolder extends
    BaseRecyclerViewHolder>
    extends RecyclerView.Adapter<TViewHolder> {
    public final void addItem(final int location, @NonNull final TItem item) {
        Contracts.requireNonNull(item, "item == null");

        addItem(location, item, true);
    }

    public final void addItem(final int location, @NonNull final TItem item, final boolean notify) {
        Contracts.requireNonNull(item, "item == null");

        final TListItem listItem = onWrapItem(item);
        getListItems().add(location, listItem);

        if (notify) {
            notifyItemInserted(location);
        }
    }

    public final void addItem(@NonNull final TItem item) {
        Contracts.requireNonNull(item, "item == null");

        addItem(item, true);
    }

    public final void addItem(@NonNull final TItem item, final boolean notify) {
        Contracts.requireNonNull(item, "item == null");

        addItem(getItemCount(), item, notify);
    }

    public final void addItems(@NonNull final Collection<TItem> items) {
        Contracts.requireNonNull(items, "items == null");

        addItems(items, true);
    }

    public final void addItems(@NonNull final Collection<TItem> items, final boolean notify) {
        Contracts.requireNonNull(items, "items == null");

        addItems(getItemCount(), items, notify);
    }

    public final void addItems(final int location, @NonNull final Collection<TItem> items) {
        Contracts.requireNonNull(items, "items == null");

        addItems(location, items, true);
    }

    public final void addItems(final int location, @NonNull final Collection<TItem> items,
        final boolean notify) {
        Contracts.requireNonNull(items, "items == null");

        final Collection<TListItem> listItems = wrapItems(items);

        getListItems().addAll(location, listItems);

        if (notify) {
            notifyItemRangeInserted(location, listItems.size());
        }
    }

    public final void removeItem(final int position) {
        removeItem(position, true);
    }

    public final void removeItem(final int position, final boolean notify) {
        getListItems().remove(position);

        if (notify) {
            notifyItemRemoved(position);
        }
    }

    public final void removeItems(final boolean notify) {
        final List<TListItem> listItems = getListItems();

        final int listItemsCount = listItems.size();

        listItems.clear();

        if (notify) {
            notifyItemRangeRemoved(0, listItemsCount);
        }
    }

    public final void removeItems() {
        removeItems(true);
    }

    public final void setItems(@NonNull final Collection<TItem> items) {
        setItems(items, true);
    }

    public final void setItems(@NonNull final Collection<TItem> items, final boolean notify) {
        final List<TListItem> listItems = getListItems();
        final Collection<TListItem> newListItems = wrapItems(items);

        final int listItemsCount = listItems.size();
        final int newListItemsCount = newListItems.size();

        final int changedCount = Math.min(listItemsCount, newListItemsCount);
        final int removedCount = Math.max(0, listItemsCount - changedCount);
        final int insertedCount = Math.max(0, newListItemsCount - changedCount);

        listItems.clear();
        listItems.addAll(newListItems);

        if (notify) {
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
    }

    @CallSuper
    @Override
    public void onBindViewHolder(final TViewHolder holder, final int position) {
        onBindViewHolder(holder, getListItem(position), position);
    }

    @Override
    public int getItemCount() {
        return getListItems().size();
    }

    @NonNull
    protected final List<TListItem> getListItems() {
        return _listItems;
    }

    @NonNull
    protected final Collection<TListItem> wrapItems(@NonNull final Collection<TItem> items) {
        Contracts.requireNonNull(items, "items == null");

        return CollectionUtils.collect(items, _listItemTransformer);
    }

    @NonNull
    protected TListItem getListItem(final int position) {
        return getListItems().get(position);
    }

    protected void onBindViewHolder(@NonNull final TViewHolder holder,
        @NonNull final TListItem listItem, final int position) {
    }

    @NonNull
    protected abstract TListItem onWrapItem(@NonNull TItem item);

    @NonNull
    private final Transformer<TItem, TListItem> _listItemTransformer =
        new Transformer<TItem, TListItem>() {
            @Override
            public TListItem transform(final TItem input) {
                return onWrapItem(input);
            }
        };

    @NonNull
    private final List<TListItem> _listItems = new ArrayList<>();
}
