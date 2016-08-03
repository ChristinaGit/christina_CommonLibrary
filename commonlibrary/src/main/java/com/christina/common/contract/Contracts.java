package com.christina.common.contract;

import android.os.Looper;
import android.support.annotation.Nullable;

import org.jetbrains.annotations.Contract;

public final class Contracts {
    @Contract("false, _ -> fail")
    public static void require(boolean condition, @Nullable Throwable throwable) {
        if (!condition) {
            if (throwable instanceof RuntimeException) {
                throw (RuntimeException) throwable;
            } else {
                throw new ContractException(throwable);
            }
        }
    }

    @Contract("false, _ -> fail")
    public static void require(boolean condition, @Nullable String message) {
        require(condition, new ContractException(message));
    }

    @Contract("false -> fail")
    public static void require(boolean condition) {
        require(condition, (String) null);
    }

    @Contract("_ -> fail")
    public static void unreachable(@Nullable Throwable throwable) {
        require(false, throwable);
    }

    @Contract("_ -> fail")
    public static void unreachable(@Nullable String message) {
        unreachable(new ContractException(message));
    }

    @Contract("-> fail")
    public static void unreachable() {
        unreachable((String) null);
    }

    @Contract("null, _ -> fail")
    public static void requireNonNull(@Nullable Object object, @Nullable Throwable throwable) {
        require(object != null, throwable);
    }

    @Contract("null, _ -> fail")
    public static void requireNonNull(@Nullable Object object, @Nullable String message) {
        requireNonNull(object, new ContractException(message));
    }

    @Contract("null -> fail")
    public static void requireNonNull(@Nullable Object object) {
        requireNonNull(object, (String) null);
    }

    @Contract("!null, _ -> fail")
    public static void requireNull(@Nullable Object object, @Nullable Throwable throwable) {
        require(object == null, throwable);
    }

    @Contract("!null, _ -> fail")
    public static void requireNull(@Nullable Object object, @Nullable String message) {
        requireNull(object, new ContractException(message));
    }

    @Contract("!null -> fail")
    public static void requireNull(@Nullable Object object) {
        requireNull(object, (String) null);
    }

    public static void requireMainThread(@Nullable Throwable throwable) {
        require(Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId(),
                throwable);
    }

    public static void requireMainThread(@Nullable String message) {
        requireMainThread(new Exception(message));
    }

    public static void requireMainThread() {
        requireMainThread((String) null);
    }

    public static void requireWorkerThread(@Nullable Throwable throwable) {
        require(Thread.currentThread().getId() != Looper.getMainLooper().getThread().getId(),
                throwable);
    }

    public static void requireWorkerThread(@Nullable String message) {
        requireWorkerThread(new ContractException(message));
    }

    public static void requireWorkerThread() {
        requireWorkerThread((String) null);
    }

    private Contracts() {
    }
}
