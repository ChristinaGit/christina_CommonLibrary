package com.christina.common.view.recyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.christina.common.contract.Contracts;

public abstract class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    @NonNull
    public final Context getContext() {
        return _context;
    }

    protected BaseRecyclerViewHolder(@NonNull final View itemView) {
        super(itemView);

        Contracts.requireNonNull(itemView, "itemView == null");

        _context = itemView.getContext();
    }

    @NonNull
    private final Context _context;
}
