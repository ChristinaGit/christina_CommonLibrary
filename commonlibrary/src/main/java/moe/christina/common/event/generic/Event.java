package moe.christina.common.event.generic;

import android.support.annotation.NonNull;

import moe.christina.common.event.eventArgs.EventArgs;

public interface Event<TEventArgs extends EventArgs> {
    void addHandler(@NonNull EventHandler<TEventArgs> handler);

    void removeHandler(@NonNull EventHandler<TEventArgs> handler);
}
