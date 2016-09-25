package com.christina.common.event;

public class CancelableEventArgs extends EventArgs {
    public static final CancelableEventArgs EMPTY = new CancelableEventArgs();

    public final void cancelEvent() {
        _canceled = true;
    }

    public final boolean isEventCanceled() {
        return _canceled;
    }

    private boolean _canceled = false;
}
