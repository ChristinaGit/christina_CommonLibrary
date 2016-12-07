package com.christina.common.data.dao;

import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;
import com.christina.common.data.model.Model;

public final class DaoContracts {
    public static void requireId(@NonNull final Model model) {
        Contracts.requireNonNull(model, "model == null");

        if (!model.hasId()) {
            throw new DaoException("Model has not id.");
        }
    }

    private DaoContracts() {
        Contracts.unreachable();
    }
}
