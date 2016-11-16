package com.christina.common.view.recyclerView.viewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class AbstractRecyclerViewHolder extends RecyclerView.ViewHolder {
    @NonNull
    public final Context getContext() {
        return itemView.getContext();
    }

    protected AbstractRecyclerViewHolder(@NonNull final View itemView) {
        super(itemView);
    }
}
