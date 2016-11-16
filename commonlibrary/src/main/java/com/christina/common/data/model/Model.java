package com.christina.common.data.model;

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

    @Getter
    @Setter
    private long _id;
}
