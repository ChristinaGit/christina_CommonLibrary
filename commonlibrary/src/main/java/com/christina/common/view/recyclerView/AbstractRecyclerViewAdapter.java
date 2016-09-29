package com.christina.common.view.recyclerView;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.christina.common.contract.Contracts;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractRecyclerViewAdapter<TItem, TViewHolder extends
    AbstractRecyclerViewHolder>
    extends RecyclerView.Adapter<TViewHolder> {

    public final void addItem(final int location, @NonNull final TItem item) {
        Contracts.requireNonNull(item, "item == null");

        addItem(location, item, true);
    }

    public final void addItem(final int location, @NonNull final TItem item, final boolean notify) {
        Contracts.requireNonNull(item, "item == null");

        final ListItem listItem = onWrapItem(item);
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

        final Collection<ListItem> listItems = wrapItems(items);

        getListItems().addAll(location, listItems);

        if (notify) {
            notifyItemRangeInserted(location, listItems.size());
        }
    }

    public final int getItemPosition(final long id) {
        return IterableUtils.indexOf(getListItems(), new Predicate<ListItem>() {
            @Override
            public boolean evaluate(final ListItem object) {
                return object.getId() == id;
            }
        });
    }

    public final void removeItem(final long id) {
        removeItem(id, true);
    }

    public final void removeItem(final long id, final boolean notify) {
        removeItem(getItemPosition(id), notify);
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

    public final void removeItems() {
        removeItems(true);
    }

    public final void removeItems(final boolean notify) {
        final List<ListItem> listItems = getListItems();

        final int listItemsCount = listItems.size();

        listItems.clear();

        if (notify) {
            notifyItemRangeRemoved(0, listItemsCount);
        }
    }

    public final void setItems(@NonNull final Collection<TItem> items) {
        setItems(items, true);
    }

    public final void setItems(@NonNull final Collection<TItem> items, final boolean notify) {
        final List<ListItem> listItems = getListItems();
        final Collection<ListItem> newListItems = wrapItems(items);

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

    public final void updateItem(@NonNull final TItem item) {
        Contracts.requireNonNull(item, "item == null");

        updateItem(item, true);
    }

    public final void updateItem(@NonNull final TItem item, final boolean notify) {
        Contracts.requireNonNull(item, "item == null");

        final ListItem listItem = onWrapItem(item);

        final int position = getItemPosition(listItem.getId());
        getListItems().set(position, listItem);

        if (notify) {
            notifyItemChanged(position);
        }
    }

    @CallSuper
    @Override
    public void onBindViewHolder(final TViewHolder holder, final int position) {
        onBindViewHolder(holder, getListItem(position), position);
    }

    @Override
    public long getItemId(final int position) {
        return getListItem(position).getId();
    }

    @Override
    public int getItemCount() {
        return getListItems().size();
    }

    protected AbstractRecyclerViewAdapter() {
        setHasStableIds(true);
    }

    @NonNull
    protected final ListItem getListItem(final int position) {
        return getListItems().get(position);
    }

    @NonNull
    protected final List<ListItem> getListItems() {
        return _listItems;
    }

    @NonNull
    protected final Collection<ListItem> wrapItems(@NonNull final Collection<TItem> items) {
        Contracts.requireNonNull(items, "items == null");

        return CollectionUtils.collect(items, _listItemTransformer);
    }

    protected void onBindViewHolder(@NonNull final TViewHolder holder,
        @NonNull final ListItem listItem, final int position) {
    }

    @NonNull
    protected abstract ListItem onWrapItem(@NonNull TItem item);

    @NonNull
    private final Transformer<TItem, ListItem> _listItemTransformer =
        new Transformer<TItem, ListItem>() {
            @Override
            public ListItem transform(final TItem input) {
                return onWrapItem(input);
            }
        };

    @NonNull
    private final List<ListItem> _listItems = new ArrayList<>();
}
