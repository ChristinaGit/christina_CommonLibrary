package com.christina.common.event;

import android.support.annotation.NonNull;

public interface EventHandler<TEventArgs extends EventArgs> {
    void onEvent(@NonNull TEventArgs eventArgs);
}
