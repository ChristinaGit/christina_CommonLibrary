package com.christina.common.thread;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;

import java.util.concurrent.Executor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public class MainThreadExecutor implements Executor {
    @Override
    public void execute(@NonNull final Runnable command) {
        Contracts.requireNonNull(command, "command == null");

        getMainThreadHandler().post(command);
    }

    @Getter(value = AccessLevel.PROTECTED, lazy = true)
    private final Handler _mainThreadHandler = HandlerUtils.getMainThreadHandler();
}