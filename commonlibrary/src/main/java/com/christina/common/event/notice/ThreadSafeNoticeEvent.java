package com.christina.common.event.notice;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

import java.util.ArrayList;
import java.util.Collection;

import lombok.val;

public final class ThreadSafeNoticeEvent extends SynchronizedNoticeEvent {
    private static final int _INITIAL_HANDLERS_COLLECTION_CAPACITY = 2;

    @Override
    protected void performAddHandler(@NonNull final NoticeEventHandler handler) {
        Contracts.requireNonNull(handler, "handler == null");

        if (_handlers == null) {
            _handlers = new ArrayList<>(_INITIAL_HANDLERS_COLLECTION_CAPACITY);
        }

        _handlers.add(handler);
    }

    @Override
    protected boolean performHasHandlers() {
        return _handlers != null && !_handlers.isEmpty();
    }

    @Override
    protected void performRemoveAllHandlers() {
        _handlers = null;
    }

    @Override
    protected void performRemoveHandler(@NonNull final NoticeEventHandler handler) {
        Contracts.requireNonNull(handler, "handler == null");

        if (_handlers != null) {
            _handlers.remove(handler);

            if (_handlers.isEmpty()) {
                _handlers = null;
            }
        }
    }

    @Override
    protected void performRise() {
        if (_handlers != null) {
            for (final val handler : _handlers) {
                handler.onEvent();
            }
        }
    }

    @Nullable
    private Collection<NoticeEventHandler> _handlers;
}
