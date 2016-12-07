package com.christina.common.event.eventArgs;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public class CancelableEventArgs extends EventArgs {
    public static final CancelableEventArgs EMPTY = new CancelableEventArgs();

    public final void cancelEvent() {
        _eventCanceled = true;
    }

    @Getter
    private boolean _eventCanceled = false;
}
