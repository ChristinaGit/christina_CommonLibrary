package com.christina.common.data.dao;

import android.support.annotation.Nullable;

import com.christina.common.contract.ContractException;

public class DaoException
    extends ContractException {
    private static final long serialVersionUID = -3576363460942439931L;

    public DaoException() {
    }

    public DaoException(@Nullable final String detailMessage) {
        super(detailMessage);
    }

    public DaoException(@Nullable final String detailMessage, @Nullable final Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DaoException(@Nullable final Throwable throwable) {
        super(throwable);
    }
}
