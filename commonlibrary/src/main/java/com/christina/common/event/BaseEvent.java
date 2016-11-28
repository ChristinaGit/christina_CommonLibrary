package com.christina.common.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

import java.util.ArrayList;
import java.util.Collection;

import lombok.val;

public final class BaseEvent<TEventArgs> implements Event<TEventArgs> {
    private static final int _INITIAL_HANDLERS_COLLECTION_CAPACITY = 2;

    @Override
    public final void addHandler(@NonNull final EventHandler<TEventArgs> handler) {
        Contracts.requireNonNull(handler, "handler == null");

        synchronized (_lock) {
            if (_handlers == null) {
                _handlers = new ArrayList<>(_INITIAL_HANDLERS_COLLECTION_CAPACITY);
            }

            _handlers.add(handler);
        }
    }

    @Override
    public final void removeHandler(@NonNull final EventHandler<TEventArgs> handler) {
        Contracts.requireNonNull(handler, "handler == null");

        synchronized (_lock) {
            if (_handlers != null) {
                _handlers.remove(handler);

                if (_handlers.isEmpty()) {
                    _handlers = null;
                }
            }
        }
    }

    public final boolean hasHandlers() {
        synchronized (_lock) {
            return _handlers != null && !_handlers.isEmpty();
        }
    }

    public final void removeAllHandlers() {
        synchronized (_lock) {
            _handlers = null;
        }
    }

    public final void rise(@NonNull final TEventArgs eventArgs) {
        Contracts.requireNonNull(eventArgs, "eventArgs == null");

        synchronized (_lock) {
            if (_handlers != null) {
                for (final val handler : _handlers) {
                    handler.onEvent(eventArgs);
                }
            }
        }
    }

    private final Object _lock = new Object();

    @Nullable
    private Collection<EventHandler<TEventArgs>> _handlers;
}
