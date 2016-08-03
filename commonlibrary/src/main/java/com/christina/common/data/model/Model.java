package com.christina.common.data.model;

public abstract class Model {
    public static final long NO_ID = -1;

    public int getModelTypeId() {
        return _modelTypeId;
    }

    public final long getId() {
        return _id;
    }

    public final void setId(final long id) {
        _id = id;
    }

    public final void resetId() {
        setId(NO_ID);
    }

    public final boolean hasId() {
        return getId() >= 0;
    }

    protected Model(final int modelTypeId) {
        _modelTypeId = modelTypeId;
    }

    private final int _modelTypeId;

    private long _id;
}
