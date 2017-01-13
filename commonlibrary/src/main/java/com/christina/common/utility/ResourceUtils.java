package com.christina.common.utility;

import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

public final class ResourceUtils {
    public static boolean quietClose(@Nullable final AutoCloseable closeable) {
        boolean closed = false;

        if (closeable != null) {
            try {
                closeable.close();
                closed = true;
            } catch (final Exception e) {
                closed = false;
            }
        }

        return closed;
    }

    private ResourceUtils() {
        Contracts.unreachable();
    }
}
