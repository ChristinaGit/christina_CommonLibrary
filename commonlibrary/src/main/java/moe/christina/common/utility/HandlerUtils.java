package moe.christina.common.utility;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import moe.christina.common.contract.Contracts;

public final class HandlerUtils {
    @NonNull
    public static Handler getMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    private HandlerUtils() {
        Contracts.unreachable();
    }
}
