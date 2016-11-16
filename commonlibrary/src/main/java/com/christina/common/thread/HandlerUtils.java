package com.christina.common.thread;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;

public final class HandlerUtils {
    @NonNull
    public static Handler getMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    private HandlerUtils() {
        Contracts.unreachable();
    }
}
