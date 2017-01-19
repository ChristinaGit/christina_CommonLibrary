package com.christina.common.extension.view.recyclerView.viewHolder;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import lombok.Getter;
import lombok.experimental.Accessors;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.christina.common.contract.Contracts;
import com.christina.common.extension.ViewBinder;

@Accessors(prefix = "_")
public abstract class ExtendedRecyclerViewHolder extends RecyclerView.ViewHolder
    implements ViewBinder {
    @NonNull
    public final Context getContext() {
        return itemView.getContext();
    }

    @CallSuper
    @Override
    public void bindViews() {
        unbindViews();

        _unbinder = ButterKnife.bind(this, itemView);
    }

    @CallSuper
    @Override
    public void bindViews(@NonNull final View source) {
        Contracts.requireNonNull(source, "source == null");

        unbindViews();

        _unbinder = ButterKnife.bind(this, source);
    }

    @CallSuper
    @Override
    public void unbindViews() {
        if (_unbinder != null) {
            _unbinder.unbind();
        }
    }

    protected ExtendedRecyclerViewHolder(@NonNull final View itemView) {
        super(Contracts.requireNonNull(itemView, "itemView == null"));
    }

    @Getter
    private Unbinder _unbinder;
}
