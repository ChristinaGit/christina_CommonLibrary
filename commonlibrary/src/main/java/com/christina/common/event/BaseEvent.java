package com.christina.common.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

import java.util.ArrayList;
import java.util.Collection;

public final class BaseEvent<TEventArgs extends EventArgs> implements Event<TEventArgs> {
    private static final int INITIAL_HANDLERS_COLLECTION_CAPACITY = 2;

    @Override
    public final void addHandler(@NonNull final EventHandler<TEventArgs> handler) {
        Contracts.requireNonNull(handler, "handler == null");

        synchronized (_lock) {
            if (_handlers == null) {
                _handlers = new ArrayList<>(INITIAL_HANDLERS_COLLECTION_CAPACITY);
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

    public final void onEvent(@NonNull final TEventArgs eventArgs) {
        Contracts.requireNonNull(eventArgs, "eventArgs == null");

        synchronized (_lock) {
            if (_handlers != null) {
                for (final EventHandler<TEventArgs> handler : _handlers) {
                    handler.onEvent(eventArgs);
                }
            }
        }
    }

    private final Object _lock = new Object();

    @Nullable
    private Collection<EventHandler<TEventArgs>> _handlers;
}
