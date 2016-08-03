package com.christina.common.logger;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.christina.common.contract.Contracts;

@SuppressLint("LogTagMismatch")
public final class Logger {
    public Logger(final @NonNull String loggerTag) {
        Contracts.requireNonNull(loggerTag);

        _loggerTag = loggerTag;
    }

    public void initialize(@NonNull final LogLevel logLevel) {
        Contracts.requireNonNull(logLevel);

        if (_logLevel != null) {
            throw new IllegalStateException("Logger is already initialized.");
        }

        _logLevel = logLevel;
    }

    @NonNull
    public final String getLoggerTag() {
        return _loggerTag;
    }

    @NonNull
    public final LogLevel getLogLevel() {
        return _logLevel != null ? _logLevel : LogLevel.ERROR;
    }

    public final boolean isLoggable(@NonNull final LogLevel logLevel) {
        Contracts.requireNonNull(logLevel);

        return logLevel.compareTo(getLogLevel()) >= 0;
    }

    public final void v(@Nullable String scopeName, @Nullable String message) {
        v(scopeName, message, null);
    }

    public final void v(@Nullable String scopeName, @Nullable String message,
                        @Nullable Throwable throwable) {

        if (isLoggable(LogLevel.VERBOSE)) {
            Log.v(getLoggerTag(), combineLogMessage(scopeName, message), throwable);
        }
    }

    public final void d(@Nullable String scopeName, @Nullable String message) {
        d(scopeName, message, null);
    }

    public final void d(@Nullable String scopeName, @Nullable String message,
                        @Nullable Throwable throwable) {

        if (isLoggable(LogLevel.DEBUG)) {
            Log.d(getLoggerTag(), combineLogMessage(scopeName, message), throwable);
        }
    }

    public final void i(@Nullable String scopeName, @Nullable String message) {
        i(scopeName, message, null);
    }

    public final void i(@Nullable String scopeName, @Nullable String message,
                        @Nullable Throwable throwable) {

        if (isLoggable(LogLevel.INFO)) {
            Log.i(getLoggerTag(), combineLogMessage(scopeName, message), throwable);
        }
    }

    public final void w(@Nullable String scopeName, @Nullable String message) {
        w(scopeName, message, null);
    }

    public final void w(@Nullable String scopeName, @Nullable Throwable throwable) {
        w(scopeName, throwable != null ? throwable.getMessage() : null, throwable);
    }

    public final void w(@Nullable String scopeName, @Nullable String message,
                        @Nullable Throwable throwable) {

        if (isLoggable(LogLevel.WARNING)) {
            Log.w(getLoggerTag(), combineLogMessage(scopeName, message), throwable);
        }
    }

    public final void e(@Nullable String scopeName, @Nullable String message) {
        e(scopeName, message, null);
    }

    public final void e(@Nullable String scopeName, @Nullable Throwable throwable) {
        e(scopeName, throwable != null ? throwable.getMessage() : null, throwable);
    }

    public final void e(@Nullable String scopeName, @Nullable String message,
                        @Nullable Throwable throwable) {

        if (isLoggable(LogLevel.ERROR)) {
            Log.e(getLoggerTag(), combineLogMessage(scopeName, message), throwable);
        }
    }

    @NonNull
    private final String _loggerTag;

    @Nullable
    private LogLevel _logLevel;

    @Nullable
    private String combineLogMessage(@Nullable String scopeName, @Nullable String message) {
        if (scopeName == null && message == null) {
            return null;
        }

        if (scopeName != null && message != null) {
            return scopeName + ": " + message;
        }

        return scopeName != null ? scopeName : message;
    }
}
