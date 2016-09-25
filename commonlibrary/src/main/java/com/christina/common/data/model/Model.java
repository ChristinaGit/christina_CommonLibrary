package com.christina.common.data.model;

public abstract class Model {
    public static final long NO_ID = -1;

    public final void clearId() {
        setId(NO_ID);
    }

    public final long getId() {
        return _id;
    }

    public final void setId(final long id) {
        _id = id;
    }

    public final boolean hasId() {
        return getId() >= 0;
    }

    protected Model() {
    }

    private long _id;
}
