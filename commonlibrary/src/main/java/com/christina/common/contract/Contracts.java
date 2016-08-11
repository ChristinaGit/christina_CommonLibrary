package com.christina.common.contract;

import android.os.Looper;
import android.support.annotation.Nullable;

import org.jetbrains.annotations.Contract;

public final class Contracts {
    //region Require

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

    //endregion

    //region Unreachable

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

    //endregion

    //region Require NonNull

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

    //endregion

    //region Require null

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

    //region Require in range

    public static void requireInRange(int value, int min, int max, @Nullable Throwable throwable) {
        require(min <= value && value <= max, throwable);
    }

    public static void requireInRange(int value, int min, int max, @Nullable String message) {
        requireInRange(value, min, max, new ContractException(message));
    }

    public static void requireInRange(int value, int min, int max) {
        requireInRange(value, min, max, (String) null);
    }

    public static void requireInRange(long value, long min, long max,
                                      @Nullable Throwable throwable) {
        require(min <= value && value <= max, throwable);
    }

    public static void requireInRange(long value, long min, long max, @Nullable String message) {
        requireInRange(value, min, max, new ContractException(message));
    }

    public static void requireInRange(long value, long min, long max) {
        requireInRange(value, min, max, (String) null);
    }

    @Contract("null, _, _, _ -> fail")
    public static <T> void requireInRange(@Nullable Comparable<T> value, @Nullable T min,
                                          @Nullable T max, @Nullable Throwable throwable) {
        boolean condition = value != null;
        if (condition && min != null) {
            condition = value.compareTo(min) >= 0;
        }
        if (condition && max != null) {
            condition = value.compareTo(max) <= 0;
        }
        require(condition, throwable);
    }

    @Contract("null, _, _, _ -> fail")
    public static <T> void requireInRange(@Nullable Comparable<T> value, @Nullable T min,
                                          @Nullable T max, @Nullable String message) {
        requireInRange(value, min, max, new ContractException(message));
    }

    @Contract("null, _, _ -> fail")
    public static <T> void requireInRange(@Nullable Comparable<T> value, @Nullable T min,
                                          @Nullable T max) {
        requireInRange(value, min, max, (String) null);
    }

    //endregion Require in range

    //region Require main thread

    public static void requireMainThread(@Nullable Throwable throwable) {
        require(Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId(),
                throwable);
    }

    public static void requireMainThread(@Nullable String message) {
        requireMainThread(new ContractException(message));
    }

    public static void requireMainThread() {
        requireMainThread((String) null);
    }

    //endregion

    //region Require worker thread

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

    //endregion

    private Contracts() {
    }
}
