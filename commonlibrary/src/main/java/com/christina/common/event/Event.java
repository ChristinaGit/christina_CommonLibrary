package com.christina.common.event;

import android.support.annotation.NonNull;

public interface Event<TEventArgs> {
    void addHandler(@NonNull EventHandler<TEventArgs> handler);

    void removeHandler(@NonNull EventHandler<TEventArgs> handler);
}
