package moe.christina.common.event;

import android.support.annotation.NonNull;

import moe.christina.common.contract.Contracts;
import moe.christina.common.event.eventArgs.EventArgs;
import moe.christina.common.event.generic.ManagedEvent;
import moe.christina.common.event.generic.ThreadSafeEvent;
import moe.christina.common.event.notice.ManagedNoticeEvent;
import moe.christina.common.event.notice.ThreadSafeNoticeEvent;

public final class Events {
    @NonNull
    public static ManagedNoticeEvent createNoticeEvent() {
        return new ThreadSafeNoticeEvent();
    }

    @NonNull
    public static <TEventArgs extends EventArgs> ManagedEvent<TEventArgs> createEvent() {
        return new ThreadSafeEvent<>();
    }

    @NonNull
    public static ManagedNoticeEvent createThreadSafeNoticeEvent() {
        return new ThreadSafeNoticeEvent();
    }

    @NonNull
    public static <TEventArgs extends EventArgs> ManagedEvent<TEventArgs> createThreadSafeEvent() {
        return new ThreadSafeEvent<>();
    }

    private Events() {
        Contracts.unreachable();
    }
}
