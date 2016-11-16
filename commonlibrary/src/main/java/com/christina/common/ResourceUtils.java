package com.christina.common;

import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

public final class ResourceUtils {
    public static void quietClose(@Nullable final AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (final Exception ignored) {
            }
        }
    }

    private ResourceUtils() {
        Contracts.unreachable();
    }
}
