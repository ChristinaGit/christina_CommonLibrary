package com.christina.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface AsyncCallback<TResult, TError> {
    void onSuccess(@Nullable TResult result);

    void onError(@NonNull TError error);
}
