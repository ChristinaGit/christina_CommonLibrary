package com.christina.common.data.model;

import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public abstract class Model {
    public static final long NO_ID = -1;

    public final void clearId() {
        setId(NO_ID);
    }

    public final boolean hasId() {
        return getId() >= 0;
    }

    protected Model() {
    }

    protected Model(@NonNull final Model model) {
        Contracts.requireNonNull(model, "model == null");

        _id = model._id;
    }

    @Getter
    @Setter
    private long _id;
}
