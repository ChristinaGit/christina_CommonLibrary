package com.christina.common.view.recyclerView.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;

import com.christina.common.contract.Contracts;
import com.christina.common.view.BindableViews;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public class BindableRecyclerViewHolder extends AbstractRecyclerViewHolder
    implements BindableViews {

    @Override
    public void bindViews() {
        unbindViews();

        _unbinder = ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViews(@NonNull final View source) {
        Contracts.requireNonNull(source, "source == null");

        unbindViews();

        _unbinder = ButterKnife.bind(this, source);
    }

    @Override
    public void unbindViews() {
        if (_unbinder != null) {
            _unbinder.unbind();
        }
    }

    protected BindableRecyclerViewHolder(@NonNull final View itemView) {
        super(itemView);
    }

    @Getter
    private Unbinder _unbinder;
}
