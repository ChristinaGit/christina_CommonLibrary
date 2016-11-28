package com.christina.common.contract;

import android.os.Looper;
import android.support.annotation.Nullable;

import org.jetbrains.annotations.Contract;

public final class Contracts {
    @Contract("false, _ -> fail")
    public static void require(final boolean condition, @Nullable final Throwable throwable) {
        if (!condition) {
            if (throwable instanceof RuntimeException) {
                throw (RuntimeException) throwable;
            } else {
                throw new ContractException(throwable);
            }
        }
    }

    @Contract("false, _ -> fail")
    public static void require(final boolean condition, @Nullable final String message) {
        require(condition, new ContractException(message));
    }

    @Contract("false -> fail")
    public static void require(final boolean condition) {
        require(condition, (String) null);
    }

    public static void requireInRange(
        final int value, final int min, final int max, @Nullable final Throwable throwable) {
        require(min <= value && value <= max, throwable);
    }

    public static void requireInRange(
        final int value, final int min, final int max, @Nullable final String message) {
        requireInRange(value, min, max, new ContractException(message));
    }

    public static void requireInRange(final int value, final int min, final int max) {
        requireInRange(value, min, max, (String) null);
    }

    public static void requireInRange(
        final long value, final long min, final long max, @Nullable final Throwable throwable) {
        require(min <= value && value <= max, throwable);
    }

    public static void requireInRange(
        final long value, final long min, final long max, @Nullable final String message) {
        requireInRange(value, min, max, new ContractException(message));
    }

    public static void requireInRange(final long value, final long min, final long max) {
        requireInRange(value, min, max, (String) null);
    }

    @Contract("null, _, _, _ -> fail")
    public static <T> void requireInRange(
        @Nullable final Comparable<T> value,
        @Nullable final T min,
        @Nullable final T max,
        @Nullable final Throwable throwable) {
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
    public static <T> void requireInRange(
        @Nullable final Comparable<T> value,
        @Nullable final T min,
        @Nullable final T max,
        @Nullable final String message) {
        requireInRange(value, min, max, new ContractException(message));
    }

    @Contract("null, _, _ -> fail")
    public static <T> void requireInRange(
        @Nullable final Comparable<T> value, @Nullable final T min, @Nullable final T max) {
        requireInRange(value, min, max, (String) null);
    }

    public static void requireMainThread(@Nullable final Throwable throwable) {
        require(
            Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId(),
            throwable);
    }

    public static void requireMainThread(@Nullable final String message) {
        requireMainThread(new ContractException(message));
    }

    public static void requireMainThread() {
        requireMainThread((String) null);
    }

    @Contract("null, _ -> fail")
    public static void requireNonNull(
        @Nullable final Object object, @Nullable final Throwable throwable) {
        require(object != null, throwable);
    }

    @Contract("null, _ -> fail")
    public static void requireNonNull(
        @Nullable final Object object, @Nullable final String message) {
        requireNonNull(object, new ContractException(message));
    }

    @Contract("null -> fail")
    public static void requireNonNull(@Nullable final Object object) {
        requireNonNull(object, (String) null);
    }

    @Contract("!null, _ -> fail")
    public static void requireNull(
        @Nullable final Object object, @Nullable final Throwable throwable) {
        require(object == null, throwable);
    }

    @Contract("!null, _ -> fail")
    public static void requireNull(@Nullable final Object object, @Nullable final String message) {
        requireNull(object, new ContractException(message));
    }

    @Contract("!null -> fail")
    public static void requireNull(@Nullable final Object object) {
        requireNull(object, (String) null);
    }

    public static void requireWorkerThread(@Nullable final Throwable throwable) {
        require(
            Thread.currentThread().getId() != Looper.getMainLooper().getThread().getId(),
            throwable);
    }

    public static void requireWorkerThread(@Nullable final String message) {
        requireWorkerThread(new ContractException(message));
    }

    public static void requireWorkerThread() {
        requireWorkerThread((String) null);
    }

    @Contract("_ -> fail")
    public static void unreachable(@Nullable final Throwable throwable) {
        require(false, throwable);
    }

    @Contract("_ -> fail")
    public static void unreachable(@Nullable final String message) {
        unreachable(new ContractException(message));
    }

    @Contract("-> fail")
    public static void unreachable() {
        unreachable((String) null);
    }

    private Contracts() {
        Contracts.unreachable();
    }
}
