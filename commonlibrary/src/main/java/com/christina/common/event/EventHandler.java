package com.christina.common.event;

import android.support.annotation.NonNull;

public interface EventHandler<TEventArgs> {
    void onEvent(@NonNull TEventArgs eventArgs);
}
