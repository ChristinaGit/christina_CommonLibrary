package com.christina.common.event.notice;

import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;

public abstract class SynchronizedNoticeEvent implements ManagedNoticeEvent {
    @Override
    public final void addHandler(@NonNull final NoticeEventHandler handler) {
        Contracts.requireNonNull(handler, "handler == null");

        synchronized (_lock$handlers) {
            performAddHandler(handler);
        }
    }

    @Override
    public final void removeHandler(@NonNull final NoticeEventHandler handler) {
        Contracts.requireNonNull(handler, "handler == null");

        synchronized (_lock$handlers) {
            performRemoveHandler(handler);
        }
    }

    @Override
    public final boolean hasHandlers() {
        synchronized (_lock$handlers) {
            return performHasHandlers();
        }
    }

    @Override
    public final void removeAllHandlers() {
        synchronized (_lock$handlers) {
            performRemoveAllHandlers();
        }
    }

    @Override
    public final void rise() {
        synchronized (_lock$handlers) {
            performRise();
        }
    }

    protected abstract void performAddHandler(@NonNull final NoticeEventHandler handler);

    protected abstract boolean performHasHandlers();

    protected abstract void performRemoveAllHandlers();

    protected abstract void performRemoveHandler(@NonNull final NoticeEventHandler handler);

    protected abstract void performRise();

    private final Object _lock$handlers = new Object();
}
