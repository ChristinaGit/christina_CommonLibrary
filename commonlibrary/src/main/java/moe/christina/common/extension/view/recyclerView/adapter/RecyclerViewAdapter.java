package moe.christina.common.extension.view.recyclerView.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import moe.christina.common.contract.Contracts;
import moe.christina.common.extension.view.recyclerView.viewHolder.ExtendedRecyclerViewHolder;

public abstract class RecyclerViewAdapter<TItem, TViewHolder extends ExtendedRecyclerViewHolder>
    extends RecyclerView.Adapter<TViewHolder> {
    @CallSuper
    @Override
    public void onBindViewHolder(final TViewHolder holder, final int position) {
        if (holder != null) {
            onBindViewHolder(holder, getItem(position), position);
        }
    }

    @NonNull
    public abstract TItem getItem(int position);

    @CallSuper
    protected void onBindViewHolder(
        @NonNull final TViewHolder holder, @NonNull final TItem item, final int position) {
        Contracts.requireNonNull(holder, "holder == null");
        Contracts.requireNonNull(item, "item == null");
    }
}
