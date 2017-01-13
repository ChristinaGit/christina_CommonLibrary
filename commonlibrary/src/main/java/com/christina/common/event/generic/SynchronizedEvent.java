package com.christina.common.event.generic;

import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;
import com.christina.common.event.eventArgs.EventArgs;

public abstract class SynchronizedEvent<TEventArgs extends EventArgs>
    implements ManagedEvent<TEventArgs> {
    @Override
    public final void addHandler(@NonNull final EventHandler<TEventArgs> handler) {
        Contracts.requireNonNull(handler, "handler == null");

        synchronized (_lock$handlers) {
            performAddHandler(handler);
        }
    }

    @Override
    public final void removeHandler(@NonNull final EventHandler<TEventArgs> handler) {
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
    public final void rise(@NonNull final TEventArgs eventArgs) {
        Contracts.requireNonNull(eventArgs, "eventArgs == null");

        synchronized (_lock$handlers) {
            performRise(eventArgs);
        }
    }

    protected abstract void performAddHandler(@NonNull final EventHandler<TEventArgs> handler);

    protected abstract boolean performHasHandlers();

    protected abstract void performRemoveAllHandlers();

    protected abstract void performRemoveHandler(@NonNull final EventHandler<TEventArgs> handler);

    protected abstract void performRise(@NonNull final TEventArgs eventArgs);

    private final Object _lock$handlers = new Object();
}
