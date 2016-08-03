package com.christina.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

public final class AsyncResult<TResult, TError> {
    @NonNull
    public static <TResult, TError> AsyncResult<TResult, TError> result() {
        return result(null);
    }

    @NonNull
    public static <TResult, TError> AsyncResult<TResult, TError> result(
        @Nullable final TResult result) {
        return new AsyncResult<>(result, null);
    }

    @NonNull
    public static <TResult, TError> AsyncResult<TResult, TError> error(
        @NonNull final TError error) {
        Contracts.requireNonNull(error, "error == null");

        return new AsyncResult<>(null, error);
    }

    @Nullable
    public final TResult result;

    @Nullable
    public final TError error;

    private AsyncResult(@Nullable final TResult result, @Nullable final TError error) {
        this.result = result;
        this.error = error;
    }
}
