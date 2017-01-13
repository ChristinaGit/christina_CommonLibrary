package com.christina.common.event.generic;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.event.eventArgs.EventArgs;

import java.util.ArrayList;
import java.util.Collection;

import lombok.val;

public final class ThreadSafeEvent<TEventArgs extends EventArgs>
    extends SynchronizedEvent<TEventArgs> {
    private static final int _INITIAL_HANDLERS_COLLECTION_CAPACITY = 2;

    @Override
    protected void performAddHandler(@NonNull final EventHandler<TEventArgs> handler) {
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
    protected void performRemoveHandler(@NonNull final EventHandler<TEventArgs> handler) {
        Contracts.requireNonNull(handler, "handler == null");

        if (_handlers != null) {
            _handlers.remove(handler);

            if (_handlers.isEmpty()) {
                _handlers = null;
            }
        }
    }

    @Override
    protected void performRise(@NonNull final TEventArgs eventArgs) {
        Contracts.requireNonNull(eventArgs, "eventArgs == null");

        if (_handlers != null) {
            for (final val handler : _handlers) {
                handler.onEvent(eventArgs);
            }
        }
    }

    @Nullable
    private Collection<EventHandler<TEventArgs>> _handlers;
}
