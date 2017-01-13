package com.christina.common.event.generic;

import android.support.annotation.NonNull;

import com.christina.common.event.eventArgs.EventArgs;

public interface ManagedEvent<TEventArgs extends EventArgs> extends Event<TEventArgs> {
    boolean hasHandlers();

    void removeAllHandlers();

    void rise(@NonNull TEventArgs eventArgs);
}
