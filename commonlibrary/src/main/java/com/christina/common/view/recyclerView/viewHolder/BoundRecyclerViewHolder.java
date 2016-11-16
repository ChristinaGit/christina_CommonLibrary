package com.christina.common.view.recyclerView.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public class BoundRecyclerViewHolder extends AbstractRecyclerViewHolder {
    protected BoundRecyclerViewHolder(@NonNull final View itemView) {
        super(itemView);

        _unbinder = ButterKnife.bind(this, itemView);
    }

    @Getter
    private final Unbinder _unbinder;
}
