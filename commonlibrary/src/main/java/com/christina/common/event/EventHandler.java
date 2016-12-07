package com.christina.common.event;

import android.support.annotation.NonNull;

import com.christina.common.event.eventArgs.EventArgs;

public interface EventHandler<TEventArgs extends EventArgs> {
    void onEvent(@NonNull TEventArgs eventArgs);
}
