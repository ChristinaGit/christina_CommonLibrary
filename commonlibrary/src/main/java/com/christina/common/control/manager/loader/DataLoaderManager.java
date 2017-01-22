package com.christina.common.control.manager.loader;

import android.support.annotation.NonNull;
import android.support.v4.content.Loader;

import com.christina.common.AsyncCallback;
import com.christina.common.AsyncResult;

public interface DataLoaderManager {
    <TResult, TError> void startLoader(
        final int loaderId,
        @NonNull Loader<AsyncResult<TResult, TError>> loader,
        @NonNull AsyncCallback<TResult, TError> callback);

    void stopLoader(final int loaderId);
}
