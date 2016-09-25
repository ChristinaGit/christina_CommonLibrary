package com.christina.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

public final class AsyncResult<TResult, TError> {
    @NonNull
    public static <TResult, TError> AsyncResult<TResult, TError> success() {
        return success(null);
    }

    @NonNull
    public static <TResult, TError> AsyncResult<TResult, TError> success(
        @Nullable final TResult result) {
        return new AsyncResult<>(result, null);
    }

    @NonNull
    public static <TResult, TError> AsyncResult<TResult, TError> error(
        @NonNull final TError error) {
        Contracts.requireNonNull(error, "_error == null");

        return new AsyncResult<>(null, error);
    }

    @Nullable
    public TError getError() {
        return _error;
    }

    @Nullable
    public TResult getResult() {
        return _result;
    }

    @Nullable
    private final TError _error;

    @Nullable
    private final TResult _result;

    private AsyncResult(@Nullable final TResult result, @Nullable final TError error) {
        _result = result;
        _error = error;
    }
}
