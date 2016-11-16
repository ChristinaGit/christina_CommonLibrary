package com.christina.common.view.recyclerView.adapter;

import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;
import com.christina.common.view.recyclerView.listItem.StableListItem;
import com.christina.common.view.recyclerView.viewHolder.AbstractRecyclerViewHolder;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;

public abstract class BaseStableRecyclerViewAdapter<TItem, TListItem extends StableListItem,
    TViewHolder extends AbstractRecyclerViewHolder>
    extends BaseRecyclerViewAdapter<TItem, TListItem, TViewHolder> {

    public final int getItemPosition(final long id) {
        return IterableUtils.indexOf(getListItems(), new Predicate<TListItem>() {
            @Override
            public boolean evaluate(final TListItem object) {
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

    public final void updateItem(@NonNull final TItem item) {
        Contracts.requireNonNull(item, "item == null");

        updateItem(item, true);
    }

    public final void updateItem(@NonNull final TItem item, final boolean notify) {
        Contracts.requireNonNull(item, "item == null");

        final TListItem listItem = onWrapItem(item);

        final int position = getItemPosition(listItem.getId());
        getListItems().set(position, listItem);

        if (notify) {
            notifyItemChanged(position);
        }
    }

    @Override
    public long getItemId(final int position) {
        return getListItem(position).getId();
    }

    protected BaseStableRecyclerViewAdapter() {
        setHasStableIds(true);
    }
}
