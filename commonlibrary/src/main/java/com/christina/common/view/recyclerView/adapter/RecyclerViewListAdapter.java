package com.christina.common.view.recyclerView.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.view.recyclerView.viewHolder.ExtendedRecyclerViewHolder;

import java.util.List;

import lombok.experimental.Accessors;
import lombok.val;

@Accessors(prefix = "_")
public abstract class RecyclerViewListAdapter<TItem, TViewHolder extends ExtendedRecyclerViewHolder>
    extends RecyclerViewAdapter<TItem, TViewHolder> {
    @Override
    @NonNull
    public final TItem getItem(final int position) {
        Contracts.requireInRange(position, 0, getItemCount() - 1, new IndexOutOfBoundsException());

        final TItem item;

        final val data = getItems();
        if (data != null) {
            item = data.get(position);
        } else {
            throw new IllegalStateException("No item by position: " + position);
        }

        return item;
    }

    @Override
    public int getItemCount() {
        final int itemCount;

        final val items = getItems();
        if (items != null) {
            itemCount = items.size();
        } else {
            itemCount = 0;
        }

        return itemCount;
    }

    @Nullable
    public abstract List<TItem> getItems();
}
