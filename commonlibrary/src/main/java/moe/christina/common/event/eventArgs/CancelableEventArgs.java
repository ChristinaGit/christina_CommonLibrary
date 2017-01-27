package moe.christina.common.event.eventArgs;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public class CancelableEventArgs extends EventArgs {
    public final void cancelEvent() {
        _eventCanceled = true;
    }

    @Getter
    private boolean _eventCanceled = false;
}
