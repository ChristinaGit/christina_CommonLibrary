package com.christina.common;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;

public final class ThreadUtils {
    @NonNull
    public static Handler getMainThreadHandler() {
        return _MainThreadHandlerInstanceHolder.INSTANCE;
    }

    public static void runOnMainThread(@NonNull final Runnable runnable) {
        Contracts.requireNonNull(runnable, "runnable == null");

        getMainThreadHandler().post(runnable);
    }

    private ThreadUtils() {
        Contracts.unreachable();
    }

    private static final class _MainThreadHandlerInstanceHolder {
        @NonNull
        public static final Handler INSTANCE = new Handler(Looper.getMainLooper());
    }
}
